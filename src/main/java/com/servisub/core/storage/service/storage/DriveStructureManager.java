package com.servisub.core.storage.service.storage;

import com.google.api.client.util.Value;
import com.servisub.core.storage.domain.DriveConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class DriveStructureManager {

    @Autowired
    private GoogleDriveService driveService;

    // ID de la carpeta raíz manual que compartiste con el bot
    @Value("${google.drive.root-folder-id}")
    private String rootFolderId;


    public String createFolders(String driveConstants) {
        return createVacationsFolders(driveConstants);
    }

    private String createVacationsFolders(String category) {
        try {
            // 1. Calculamos el año actual
            String currentYear = String.valueOf(LocalDate.now().getYear());

            String yearFolderId = driveService.getOrCreatePath(category + currentYear);
            System.out.println("Estructura "+ category +" verificada para el año: " + currentYear);
            return yearFolderId;

        } catch (IOException e) {
            System.err.println("Error al organizar almacenamiento de "+ category +": " + e.getMessage());
            throw new RuntimeException("Falla en la integridad del Storage de "+ category, e);
        }
    }


}
