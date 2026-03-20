package com.fluxenture.core.storage.controller;

import com.fluxenture.core.employees.domain.ResponseDTO;
import com.fluxenture.core.storage.domain.CreateFolderDTO;
import com.fluxenture.core.storage.domain.StorageFile;
import com.fluxenture.core.storage.domain.UploadFileDTO;
import com.fluxenture.core.storage.service.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> download(@PathVariable String fileId) {
        return storageService.downloadFileResponse(fileId);
    }

    @PostMapping("/create-folder")
    public ResponseEntity<String> createFolder(@RequestBody CreateFolderDTO folderDTO) {
        storageService.createFolder(folderDTO.getFolderName(), folderDTO.getParentFolderId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
