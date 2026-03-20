package com.fluxenture.core.storage.service.storage;

import com.fluxenture.core.employees.domain.ResponseDTO;
import com.fluxenture.core.storage.domain.StorageFile;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface StorageService {
    ResponseEntity<ResponseDTO> uploadFile(MultipartFile file, String path);
    String createFolder(String folderName, String parentFolderId);
    List<FileInfo> listFiles(String folderId);
    ResponseEntity<?> deleteFile(String fileId);
    ResponseEntity<ResponseDTO> updateFile(String fileId, byte[] contentBytes, String contentType) throws IOException;
    InputStream downloadFile(String fileId) throws IOException;
    StorageFile downloadFileById(String fileId) throws IOException;
    ResponseEntity<Resource> downloadFileResponse(String fileId);
}
