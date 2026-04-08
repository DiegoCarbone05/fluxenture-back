package com.fluxenture.core.docs.infrastructure.input.rest;

import com.fluxenture.core.docs.application.*;
import com.fluxenture.core.docs.domain.Doc;
import com.fluxenture.core.storage.service.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docs")
public class DocController {
    @Autowired
    private SaveDocUseCase saveDocUseCase;
    @Autowired
    private GetDocsByEmployeeUseCase getDocsByEmployeeUseCase;
    @Autowired
    private DeleteDocUseCase deleteDocUseCase;
    @Autowired
    private GetAllDocsUseCase getAllDocsUseCase;
    @Autowired
    private StorageService storageService;
    @Autowired
    private GetDocByIdUseCase getDocByIdUseCase;


    @GetMapping("/")
    public ResponseEntity<List<Doc>> getAllDocs() {
        return getAllDocsUseCase.execute();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doc> getDocById(@PathVariable String id) {
        return getDocByIdUseCase.execute(id);
    }

    @PostMapping("/")
    public ResponseEntity<Doc> save(@RequestBody Doc doc) {
        System.out.println(doc.toString());
        return saveDocUseCase.execute(doc);
    }

    @DeleteMapping("/{id}/{fileId}")
    public ResponseEntity<Void> deleteFileAndDoc(@PathVariable("id") String id, @PathVariable("fileId") String fileId) {
        deleteDocUseCase.execute(id);
        storageService.deleteFile(fileId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Doc>> getByEmployeeId(@PathVariable String employeeId) {
        return getDocsByEmployeeUseCase.execute(employeeId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return deleteDocUseCase.execute(id);
    }
}
