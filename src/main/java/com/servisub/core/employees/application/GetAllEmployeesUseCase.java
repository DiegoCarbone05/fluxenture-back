package com.servisub.core.employees.application;

import com.servisub.core.employees.domain.Employe;
import com.servisub.core.employees.domain.EmployeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllEmployeesUseCase {

    private final EmployeRepository employeRepository;

    public GetAllEmployeesUseCase(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    public ResponseEntity<List<Employe>> execute() {
        try{
            List<Employe> allEmployees = employeRepository.getAllEmployees();
            return ResponseEntity.ok(allEmployees);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
