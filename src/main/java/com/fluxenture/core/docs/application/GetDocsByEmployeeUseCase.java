package com.fluxenture.core.docs.application;

import com.fluxenture.core.docs.domain.Doc;
import com.fluxenture.core.docs.domain.DocRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GetDocsByEmployeeUseCase {
    private final DocRepository docRepository;

    public GetDocsByEmployeeUseCase(DocRepository docRepository) {
        this.docRepository = docRepository;
    }

    public ResponseEntity<List<Doc>> execute(String employeeId) {
        List<Doc> docs = docRepository.findByEmployeeId(employeeId);
        return ResponseEntity.ok(docs);
    }
}
