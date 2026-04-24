package com.fluxenture.core.employees.application;

import com.fluxenture.core.employees.domain.Employee;
import com.fluxenture.core.employees.domain.EmployeeRepository;
import com.fluxenture.core.employees.domain.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadAllEmployeesUseCase {
    private final EmployeeRepository employeeRepository;
    public LoadAllEmployeesUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public ResponseEntity<ResponseDTO> execute(List<Employee> employees) {
        return employeeRepository.loadAllEmployees(employees);
    }

}
