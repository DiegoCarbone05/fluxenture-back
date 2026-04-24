package com.fluxenture.core.employees.application;

import com.fluxenture.core.employees.domain.Employee;
import com.fluxenture.core.employees.domain.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetEmployeeByIdUseCase {

    private final EmployeeRepository employeeRepository;

    public GetEmployeeByIdUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public ResponseEntity<Employee> execute(String id) {
        try {
            Employee employee = employeeRepository.getById(id);
            if (employee != null) {
                return ResponseEntity.ok(employee);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
