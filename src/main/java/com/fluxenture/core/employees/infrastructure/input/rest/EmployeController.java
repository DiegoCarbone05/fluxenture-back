package com.fluxenture.core.employees.infrastructure.input.rest;

import com.fluxenture.core.employees.application.DeleteEmployeeUseCase;
import com.fluxenture.core.employees.application.GetAllEmployeesUseCase;
import com.fluxenture.core.employees.application.GetEmployeeByIdUseCase;
import com.fluxenture.core.employees.application.LoadAllEmployeesUseCase;
import com.fluxenture.core.employees.application.SaveEmployeeUseCase;
import com.fluxenture.core.employees.domain.Employe;
import com.fluxenture.core.employees.domain.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees/")
public class EmployeController {

    private final SaveEmployeeUseCase saveEmployeeUseCase;
    private final DeleteEmployeeUseCase deleteEmployeeUseCase;
    private final GetAllEmployeesUseCase getAllEmployeesUseCase;
    private final LoadAllEmployeesUseCase loadAllEmployeesUseCase;
    private final GetEmployeeByIdUseCase getEmployeeByIdUseCase;

    public EmployeController(SaveEmployeeUseCase saveEmployeeUseCase, DeleteEmployeeUseCase deleteEmployeeUseCase, GetAllEmployeesUseCase getAllEmployeesUseCase, LoadAllEmployeesUseCase loadAllEmployeesUseCase, GetEmployeeByIdUseCase getEmployeeByIdUseCase) {
        this.saveEmployeeUseCase = saveEmployeeUseCase;
        this.deleteEmployeeUseCase = deleteEmployeeUseCase;
        this.getAllEmployeesUseCase = getAllEmployeesUseCase;
        this.loadAllEmployeesUseCase = loadAllEmployeesUseCase;
        this.getEmployeeByIdUseCase = getEmployeeByIdUseCase;
    }



    @PostMapping("/")
    public ResponseEntity<Employe> save(@RequestBody Employe employee) {
        return saveEmployeeUseCase.execute(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employe> delete(@PathVariable String id) {
        return deleteEmployeeUseCase.execute(id);
    }

    @GetMapping("/")
    public ResponseEntity<List<Employe>> getAll() {
        return getAllEmployeesUseCase.execute();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employe> getById(@PathVariable String id) {
        return getEmployeeByIdUseCase.execute(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employe> update(@PathVariable String id, @RequestBody Employe employee) {
        return saveEmployeeUseCase.execute(employee);
    }

    @PostMapping("/bulk")
    public ResponseEntity<ResponseDTO> createMultiple(@RequestBody List<Employe> employees) {
        System.out.println(employees);
        return loadAllEmployeesUseCase.execute(employees);
    }

}
