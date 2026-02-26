package com.servisub.core.employees.application;

import com.servisub.core.employees.domain.Employe;
import com.servisub.core.employees.domain.EmployeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteEmployeeUseCase {

    private final EmployeRepository employeRepository;

    public DeleteEmployeeUseCase(EmployeRepository employeRepository){
        this.employeRepository=employeRepository;
    }

    public ResponseEntity<Employe> execute(String id){

        try{
            employeRepository.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
