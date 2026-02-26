package com.servisub.core.storage.controller;

import com.servisub.core.employees.domain.ResponseDTO;
import com.servisub.core.storage.domain.CreateFolderDTO;
import com.servisub.core.storage.domain.UploadFileDTO;
import com.servisub.core.storage.service.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

// Controller
@RestController
@RequestMapping("/storage")
public class FileController {

    @Autowired
    private StorageService storageService; // inyecta la interfaz

    @PostMapping("/upload")
    public ResponseEntity<ResponseDTO> upload(@RequestPart("file") MultipartFile file, @RequestPart("folderPath") String path) {
        return storageService.uploadFile(file, path);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return storageService.deleteFile(id);
    }

    @PostMapping("/create-folder")
    public ResponseEntity<String> createFolder(@RequestBody CreateFolderDTO folderDTO) {
        storageService.createFolder(folderDTO.getFolderName(), folderDTO.getParentFolderId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}