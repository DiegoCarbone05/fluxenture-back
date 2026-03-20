package com.fluxenture.core.cd.infrastructure.input.rest;

import com.fluxenture.core.cd.application.*;
import com.fluxenture.core.cd.domain.Cd;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Service
@RestController
@RequestMapping("/cds")
public class CdController {

    @Autowired
    public LoadCdUseCase loadCdUseCase;
    @Autowired
    public GetCdForIDUseCase getCdForIDUseCase;
    @Autowired
    public PutCdUseCase putCdUseCase;
    @Autowired
    public DeleteCdUseCase deleteCdUseCase;
    @Autowired
    public GetAllCdsUseCase getAllCdsUseCase;
    @Autowired
    public ExportTntUseCase exportTtnUseCase;

    @PostMapping("/")
    public ResponseEntity<?> loadTnt(@RequestBody Cd newTnt) {
        return loadCdUseCase.execute(newTnt);
    }

    @GetMapping("/")
    public ResponseEntity<List<Cd>> getAll(){
        return getAllCdsUseCase.execute();
    }

    @PutMapping("/")
    public ResponseEntity<?> putTnt(@RequestBody Cd cd){
        return putCdUseCase.execute(cd);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTnt(@PathVariable String id){
        return deleteCdUseCase.execute(id);
    }

    @PostMapping("/export")
    public ResponseEntity<?> exportTnt(@RequestPart("tnt") String tnt, @RequestPart("fileId")  String fileId) throws IOException {
        return exportTtnUseCase.execute(tnt, fileId);
    }


    @GetMapping("/{id}")
    public void getTntForId(@RequestHeader("token") String token, @PathVariable String id){

    }

}
