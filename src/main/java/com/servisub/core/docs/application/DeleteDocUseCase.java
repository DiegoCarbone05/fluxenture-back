package com.servisub.core.docs.application;

import com.servisub.core.docs.domain.DocRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteDocUseCase {
    private final DocRepository docRepository;

    public DeleteDocUseCase(DocRepository docRepository) {
        this.docRepository = docRepository;
    }

    public ResponseEntity<Void> execute(String id) {
        try {
            docRepository.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
