package com.fluxenture.core.docs.infrastructure.input.rest;

import com.fluxenture.core.docs.application.DeleteDocUseCase;
import com.fluxenture.core.docs.application.GetAllDocsUseCase;
import com.fluxenture.core.docs.application.GetDocsByEmployeeUseCase;
import com.fluxenture.core.docs.application.SaveDocUseCase;
import com.fluxenture.core.docs.domain.Doc;
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
    private GetAllDocsUseCase getAllDocsUseCase     ;


    @GetMapping("/")
    public ResponseEntity<List<Doc>> getAllDocs() {
        return getAllDocsUseCase.execute();
    }

    @PostMapping("/")
    public ResponseEntity<Doc> save(@RequestBody Doc doc) {
        System.out.println(doc.toString());
        return saveDocUseCase.execute(doc);
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
