package com.fluxenture.core.cd.domain;

import com.mongodb.client.result.DeleteResult;
import com.fluxenture.core.cd.infrastructure.output.persistence.entity.CdEntity;
import org.checkerframework.checker.units.qual.C;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CdRepository {
     Cd save(Cd newCd);
     DeleteResult delete(String id);
     void update(Cd cd);
     List<Cd> getAll();
     void getById();
}
