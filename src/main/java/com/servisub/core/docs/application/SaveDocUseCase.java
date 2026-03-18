package com.servisub.core.docs.application;

import com.servisub.core.docs.domain.Doc;
import com.servisub.core.docs.domain.DocRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SaveDocUseCase {
    private final DocRepository docRepository;

    public SaveDocUseCase(DocRepository docRepository) {
        this.docRepository = docRepository;
    }

    public ResponseEntity<Doc> execute(Doc doc) {
        System.out.println("PROCESSING DOCS USCASE - SAVE DOC");
        try {
            docRepository.save(doc);
            return ResponseEntity.ok(doc);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
