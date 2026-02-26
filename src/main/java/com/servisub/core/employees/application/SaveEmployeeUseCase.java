package com.servisub.core.employees.application;

import com.servisub.core.employees.domain.Employe;
import com.servisub.core.employees.domain.EmployeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SaveEmployeeUseCase {

    public final EmployeRepository employeeRepository;

    public SaveEmployeeUseCase(EmployeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public ResponseEntity<Employe> execute(Employe employe) {
        try{
            employeeRepository.save(employe);
            return ResponseEntity.ok(employe);
        }catch(Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }
}
