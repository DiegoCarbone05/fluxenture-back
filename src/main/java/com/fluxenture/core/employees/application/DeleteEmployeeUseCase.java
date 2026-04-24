package com.fluxenture.core.employees.application;

import com.fluxenture.core.employees.domain.Employee;
import com.fluxenture.core.employees.domain.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteEmployeeUseCase {

    private final EmployeeRepository employeeRepository;

    public DeleteEmployeeUseCase(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }

    public ResponseEntity<Employee> execute(String id){

        try{
            employeeRepository.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
