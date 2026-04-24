package com.fluxenture.core.employees.infrastructure.input.rest;

import com.fluxenture.core.employees.application.*;
import com.fluxenture.core.employees.domain.Employee;
import com.fluxenture.core.employees.domain.EmployeeDTO;
import com.fluxenture.core.employees.domain.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employees/")
public class EmployeeController {

    private final SaveEmployeeUseCase saveEmployeeUseCase;
    private final DeleteEmployeeUseCase deleteEmployeeUseCase;
    private final GetAllEmployeesUseCase getAllEmployeesUseCase;
    private final LoadAllEmployeesUseCase loadAllEmployeesUseCase;
    private final GetEmployeeByIdUseCase getEmployeeByIdUseCase;
    @Autowired
    private SearchEmployeesUseCase searchEmployeesUseCase;

    public EmployeeController(SaveEmployeeUseCase saveEmployeeUseCase, DeleteEmployeeUseCase deleteEmployeeUseCase, GetAllEmployeesUseCase getAllEmployeesUseCase, LoadAllEmployeesUseCase loadAllEmployeesUseCase, GetEmployeeByIdUseCase getEmployeeByIdUseCase) {
        this.saveEmployeeUseCase = saveEmployeeUseCase;
        this.deleteEmployeeUseCase = deleteEmployeeUseCase;
        this.getAllEmployeesUseCase = getAllEmployeesUseCase;
        this.loadAllEmployeesUseCase = loadAllEmployeesUseCase;
        this.getEmployeeByIdUseCase = getEmployeeByIdUseCase;
    }

    @PostMapping("/")
    public ResponseEntity<Employee> save(@RequestBody Employee employee) {
        return saveEmployeeUseCase.execute(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> delete(@PathVariable String id) {
        return deleteEmployeeUseCase.execute(id);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployees(@RequestParam String name, @RequestParam(name = "size", defaultValue = "10") int size) {
        return searchEmployeesUseCase.execute(name, size);
    }

    @GetMapping("/")
    public ResponseEntity<List<EmployeeDTO>> getAll() {
        return getAllEmployeesUseCase.execute();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable String id) {
        return getEmployeeByIdUseCase.execute(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable String id, @RequestBody Employee employee) {
        return saveEmployeeUseCase.execute(employee);
    }

    @PostMapping("/bulk")
    public ResponseEntity<ResponseDTO> createMultiple(@RequestBody List<Employee> employees) {
        return loadAllEmployeesUseCase.execute(employees);
    }

}
