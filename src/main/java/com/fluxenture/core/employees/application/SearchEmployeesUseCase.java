package com.fluxenture.core.employees.application;

import com.fluxenture.core.docs.domain.DocRepository;
import com.fluxenture.core.employees.domain.Employee;
import com.fluxenture.core.employees.domain.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchEmployeesUseCase {

    @Autowired
    private EmployeeRepository repository;

    public ResponseEntity<List<Employee>> execute(String name, int size){
        try {
            return ResponseEntity.ok().body(repository.findByName(name,size));

        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
