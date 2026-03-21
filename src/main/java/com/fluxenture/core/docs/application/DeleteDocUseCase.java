package com.fluxenture.core.docs.application;

import com.fluxenture.core.docs.domain.DocRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteDocUseCase {
    private final DocRepository docRepository;

    public DeleteDocUseCase(DocRepository docRepository) {
        this.docRepository = docRepository;
    }

    public ResponseEntity<Void> execute(String id) {
        docRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
