package com.servisub.core.cd.application;

import com.mongodb.client.result.DeleteResult;
import com.servisub.core.cd.domain.CdRepository;
import com.servisub.core.cd.infrastructure.output.persistence.entity.CdEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteCdUseCase {

    @Autowired
    CdRepository cdRepository;

    public DeleteCdUseCase(){}

    public ResponseEntity<DeleteResult> execute(String id){
        try{

            return ResponseEntity.ok(cdRepository.delete(id));
        }
        catch(Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }
}
