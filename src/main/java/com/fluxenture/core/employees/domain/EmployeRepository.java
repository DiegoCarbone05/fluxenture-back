package com.fluxenture.core.employees.domain;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeRepository {
    void save(Employe employe);
    void update();
    List<Employe> getAllEmployees();
    ResponseEntity<ResponseDTO> loadAllEmployees(List<Employe> employees);
    void delete(String id);
    void getEmploye();
}
