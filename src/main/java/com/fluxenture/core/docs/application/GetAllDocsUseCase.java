package com.fluxenture.core.docs.application;

import com.fluxenture.core.docs.domain.Doc;
import com.fluxenture.core.docs.domain.DocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllDocsUseCase {

    @Autowired
    private DocRepository repository;

    public ResponseEntity<List<Doc>> execute(){
        try
        {
            List<Doc> docs = repository.getAll();
            return ResponseEntity.ok(docs);
        }catch(Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }
}
