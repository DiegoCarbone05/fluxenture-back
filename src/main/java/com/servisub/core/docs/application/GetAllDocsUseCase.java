package com.servisub.core.docs.application;

import com.servisub.core.docs.domain.Doc;
import com.servisub.core.docs.domain.DocRepository;
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
