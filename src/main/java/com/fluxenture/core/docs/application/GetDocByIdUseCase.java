package com.fluxenture.core.docs.application;

import com.fluxenture.core.docs.domain.Doc;
import com.fluxenture.core.docs.domain.DocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetDocByIdUseCase {

    @Autowired
    private DocRepository repository;

    public ResponseEntity<Doc> execute(String id) {
        Doc doc = repository.findById(id);
        if (doc != null) {
            return ResponseEntity.ok(doc);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
