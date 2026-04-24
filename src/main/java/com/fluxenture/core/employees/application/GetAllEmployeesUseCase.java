package com.fluxenture.core.employees.application;

import com.fluxenture.core.employees.domain.Employee;
import com.fluxenture.core.employees.domain.EmployeeDTO;
import com.fluxenture.core.employees.domain.EmployeeRepository;
import com.fluxenture.core.employees.infrastructure.output.persistence.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllEmployeesUseCase {

    private final EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeMapper employeeMapper;

    public GetAllEmployeesUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public ResponseEntity<List<EmployeeDTO>> execute() {
        try{
            List<Employee> allEmployees = employeeRepository.getAllEmployees();
            List<EmployeeDTO> employeeDTOS = allEmployees.stream().map(employeeMapper::mapperAtDto).toList();
            return ResponseEntity.ok(employeeDTOS);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
