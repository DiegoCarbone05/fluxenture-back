package com.fluxenture.core.employees.application;

import com.fluxenture.core.employees.domain.Employee;
import com.fluxenture.core.employees.domain.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SaveEmployeeUseCase {

    public final EmployeeRepository employeeRepository;

    public SaveEmployeeUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public ResponseEntity<Employee> execute(Employee employee) {
        try{
            Employee savedEmployee = employeeRepository.save(employee);
            return ResponseEntity.ok(savedEmployee);
        }catch(Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }
}
