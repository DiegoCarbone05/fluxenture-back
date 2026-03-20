package com.fluxenture.core.storage.service.storage;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.fluxenture.core.employees.domain.ResponseDTO;
import com.fluxenture.core.storage.domain.StorageFile;
import jakarta.annotation.PostConstruct;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Service
@Primary
public class GoogleDriveService implements StorageService {


    // Objeto que usaremos para interactuar con Google Drive
    @Autowired
    private Drive driveService;
    private String rootFolderId = "1XkGLVkcrPeniKJRKloOmvwgBD1-e65pB";


    @PostConstruct  // ← Spring ejecuta esto después de crear el bean
    public void init() throws Exception { }


    //-------------------------------------------------------------------------------------FOLDERS STRUCTURES METHODS

    public String getOrCreatePath(String fullPath) throws IOException {
        // 1. Empezamos desde la carpeta raíz de Fluxenture (la que compartiste con el bot)
        String currentParentId = rootFolderId;

        // 2. Rompemos el String por las barras "/" para procesar carpeta por carpeta
        String[] folders = fullPath.split("/");

        for (String folderName : folders) {
            if (folderName.isEmpty()) continue;

            // 3. Llamamos al método que busca si existe o la crea
            // Esto evita que se duplique "cd_trackings" o el "2026"
            currentParentId = getOrCreateFolder(folderName, currentParentId);
        }

        // 4. Al final de todo el recorrido, te devuelve el ID de la última carpeta
        return currentParentId;
    }

    //Verificador
    public String getOrCreateFolder(String folderName, String parentId) throws IOException {
        // Consulta para buscar carpeta por nombre y padre que no esté borrada
        String query = String.format(
                "name = '%s' and '%s' in parents and mimeType = 'application/vnd.google-apps.folder' and trashed = false",
                folderName, parentId
        );

        FileList result = driveService.files().list()
                .setQ(query)
                .setFields("files(id, name)")
                .execute();

        if (!result.getFiles().isEmpty()) {
            // Ya existe, devolvemos el ID del primero que encuentre
            return result.getFiles().getFirst().getId();
        } else {
            // No existe, la creamos de cero
            return createFolder(folderName, parentId);
        }
    }


    //-------------------------------------------------------------------------------------END


    @Override
    public ResponseEntity<ResponseDTO> uploadFile(MultipartFile file, String path) {

        try{
            String filderId = getOrCreatePath(path);

            // 1. Definir los metadatos del archivo (Nombre y Carpeta destino)
            File fileMetadata = new File();
            fileMetadata.setName(file.getOriginalFilename());
            fileMetadata.setParents(Collections.singletonList(filderId));

            // 2. Preparar el contenido del archivo
            InputStreamContent mediaContent = new InputStreamContent(
                    file.getContentType(),
                    file.getInputStream()
            );

            // 3. Ejecutar la subida usando el DriveService que ya tenés inyectado
            File gFile = driveService.files().create(fileMetadata, mediaContent)
                    .setSupportsAllDrives(true)
                    .setFields("id") // Pedimos que nos devuelva el ID
                    .execute();

            // 4. Retornamos los datos para guardarlos en el objeto Tnt en MongoDB
            return ResponseEntity.ok(new ResponseDTO(gFile.getId()));
        }
        catch (Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( new ResponseDTO(e.getMessage()));
        }


    }


    @Override
    public String createFolder(String folderName, String parentFolderId) {
        try {
            File fileMetadata = new File();
            fileMetadata.setName(folderName);
            fileMetadata.setMimeType("application/vnd.google-apps.folder");

            // PASO 2: Definir DÓNDE se creará la carpeta
            if (parentFolderId != null && !parentFolderId.isEmpty()) {
                fileMetadata.setParents(Collections.singletonList(parentFolderId));
            }

            File folder = driveService.files()
                    .create(fileMetadata)
                    .setFields("id, name, parents, webViewLink") // Agregué webViewLink aquí
                    .execute();

            // PASO 4: Obtener información (Cuidado con los nombres de variables)
            String newFolderId = folder.getId();
            String nameCreated = folder.getName();
            String webLink = folder.getWebViewLink();

            System.out.println("Carpeta creada con éxito:");
            System.out.println("- ID: " + newFolderId);
            System.out.println("- Nombre: " + nameCreated);
            System.out.println("- Link: " + webLink);

            return newFolderId;

        } catch (IOException e) {
            // Loggear el error es vital en entornos Spring
            System.err.println("Error al crear carpeta en Drive: " + e.getMessage());
            throw new RuntimeException("No se pudo crear la carpeta en Google Drive", e);
        }
    }

    @Override
    public List<FileInfo> listFiles(String folderId) {
        return List.of();
    }

    @Override
    public ResponseEntity<?> deleteFile(String fileId) {
        try{
            return ResponseEntity.ok(driveService.files().delete(fileId).execute());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( new ResponseDTO(e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> updateFile(String fileId, byte[] contentBytes, String contentType) throws IOException {
        File metadata = new File();
        // 2. Envolvemos los bytes en el formato que Google entiende
        ByteArrayContent mediaContent = new ByteArrayContent(contentType, contentBytes);
        File file = driveService.files().update(fileId, metadata, mediaContent).setFields("id, name, mimeType").execute();
        System.out.println("[Drive] Update exitoso: " + file.getName() + " (ID: " + file.getId() + ")");
        return ResponseEntity.ok(new ResponseDTO(file.getId()));
    }

    @Override
    public InputStream downloadFile(String fileId) throws IOException {
        System.out.println("Bajando archivo de Drive ID: " + fileId);
        return driveService.files().get(fileId).executeMediaAsInputStream();
    }

    @Override
    public StorageFile downloadFileById(String fileId) throws IOException {
        System.out.println("Bajando archivo de Drive ID (con metadata): " + fileId);
        File driveFile = driveService.files().get(fileId).setFields("name, mimeType").execute();
        InputStream inputStream = driveService.files().get(fileId).executeMediaAsInputStream();

        return StorageFile.builder()
                .inputStream(inputStream)
                .name(driveFile.getName())
                .mimeType(driveFile.getMimeType())
                .build();
    }

    @Override
    public ResponseEntity<Resource> downloadFileResponse(String fileId) {
        try {
            StorageFile storageFile = downloadFileById(fileId);
            InputStreamResource resource = new InputStreamResource(storageFile.getInputStream());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + storageFile.getName() + "\"")
                    .contentType(MediaType.parseMediaType(storageFile.getMimeType()))
                    .body(resource);
        } catch (IOException e) {
            System.err.println("Error al descargar archivo de Drive: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}





















