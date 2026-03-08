package com.servisub.core.storage.service.storage;

import com.servisub.core.storage.domain.DriveConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class DriveStorageInitializer {

    @Autowired
    private DriveStructureManager driveStructureManager;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        System.out.println(">> [Drive] Verificando integridad de carpetas...");
        try {
            // Llamamos a tu lógica específica de Cartas Documento
            // Esto asegura que /cd_trackings/2026/ exista antes de que alguien entre al front

            // Params (Category, EnableAutoYearFolder, EnableAutoMonthFolder)
            String cdFolderId = driveStructureManager.createFolders(DriveConstants.ROOT_CD, true, false);
            System.out.println(">> [Drive] Estructura de CDs OK. ID: " + cdFolderId);
            String newFeaturesFolderId = driveStructureManager.createFolders(DriveConstants.LPO,  false, false);
            System.out.println(">> [Drive] Estructura de CDs OK. NEW FEATURES: " + newFeaturesFolderId);


            // Si el día de mañana agregás Vacaciones, llamarías acá a:
            // driveService.createFolderVacationsStructure();

        } catch (Exception e) {
            System.err.println(">> [Drive] ERROR CRÍTICO: No se pudo verificar la estructura al iniciar.");
            e.printStackTrace();
        }
    }
}