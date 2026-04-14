package com.fluxenture.core.employees.application;

import com.fluxenture.core.employees.domain.Employe;
import com.fluxenture.core.employees.domain.EmployeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetEmployeeByIdUseCase {

    private final EmployeRepository employeRepository;

    public GetEmployeeByIdUseCase(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    public ResponseEntity<Employe> execute(String id) {
        try {
            Employe employe = employeRepository.getById(id);
            if (employe != null) {
                return ResponseEntity.ok(employe);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
