package com.fluxenture.core.employees.application;

import com.fluxenture.core.employees.domain.Employe;
import com.fluxenture.core.employees.domain.EmployeRepository;
import com.fluxenture.core.employees.domain.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadAllEmployeesUseCase {
    private final EmployeRepository employeRepository;
    public LoadAllEmployeesUseCase(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    public ResponseEntity<ResponseDTO> execute(List<Employe> employees) {
        return employeRepository.loadAllEmployees(employees);
    }

}
