package com.servisub.core.cd.application;

import com.servisub.core.cd.domain.Cd;
import com.servisub.core.cd.domain.CdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllCdsUseCase {

    @Autowired
    CdRepository cdRepository;

    public ResponseEntity<List<Cd>> execute(){
        try{
            return ResponseEntity.ok(cdRepository.getAll());

        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
