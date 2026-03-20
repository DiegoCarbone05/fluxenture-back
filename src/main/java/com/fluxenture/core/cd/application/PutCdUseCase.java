package com.fluxenture.core.cd.application;

import com.fluxenture.core.cd.domain.Cd;
import com.fluxenture.core.cd.domain.CdRepository;
import com.fluxenture.core.cd.domain.Tnt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PutCdUseCase {

    @Autowired
    CdRepository cdRepository;

    public ResponseEntity<?> execute(Cd cd){
        try{
            cdRepository.update(cd);
            return ResponseEntity.status(200).build();
        }
        catch(Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
