package com.fluxenture.core.employees.domain;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeRepository {
    Employee save(Employee employee);
    void update();
    List<Employee> getAllEmployees();
    ResponseEntity<ResponseDTO> loadAllEmployees(List<Employee> employees);
    void delete(String id);
    void getEmployee();
    Employee getById(String id);
    List<Employee> findByName(String name, int size);
}
