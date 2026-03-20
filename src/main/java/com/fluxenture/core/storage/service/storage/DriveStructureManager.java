package com.fluxenture.core.storage.service.storage;

import com.google.api.client.util.Value;
import com.fluxenture.core.storage.domain.DriveConstants;
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


    public String createFolders(String driveConstants, Boolean autoYear, Boolean autoMonth) {
        return folderCreator(driveConstants, autoYear, autoMonth);
    }

    /**
     * Funcion intelgente para crear carpetas teniendo en cuenta años y meses y asegurando que no se dupliquen
     * @param category
     * @param autoYear
     * @param autoMonth
     * @return
     */
    private String folderCreator(String category, Boolean autoYear, Boolean autoMonth) {
        try {
            // 1. Calculamos el año actual
            String currentYear = "";
            String currentMonth = "";

            if (autoYear) {
                currentYear = String.valueOf(LocalDate.now().getYear());
            }
            if (autoMonth) {
                currentMonth = String.valueOf(LocalDate.now().getMonth());
            }


            String yearFolderId = driveService.getOrCreatePath(category + currentYear + currentMonth);
            System.out.println("Estructura "+ category +" verificada para el año: " + currentYear + currentMonth);
            return yearFolderId;

        } catch (IOException e) {
            System.err.println("Error al organizar almacenamiento de "+ category +": " + e.getMessage());
            throw new RuntimeException("Falla en la integridad del Storage de "+ category, e);
        }
    }


}
