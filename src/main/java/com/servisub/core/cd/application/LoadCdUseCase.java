package com.servisub.core.cd.application;

import com.servisub.core.cd.domain.Cd;
import com.servisub.core.cd.domain.CdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.webauthn.api.PublicKeyCose;
import org.springframework.stereotype.Service;

@Service
public class LoadCdUseCase {

    @Autowired
    private CdRepository cdRepository;

    public ResponseEntity<?> execute(Cd newCd){
        try{
            return ResponseEntity.ok(cdRepository.save(newCd));
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
