package com.fluxenture.core.employeehistory.infrastructure.input.rest;

import com.fluxenture.core.employeehistory.application.DeleteEmployeeHistoryUseCase;
import com.fluxenture.core.employeehistory.application.GetEmployeeHistoryByEmployeeIdUseCase;
import com.fluxenture.core.employeehistory.application.GetEmployeeHistoryByEmployeeNameUseCase;
import com.fluxenture.core.employeehistory.application.SaveEmployeeHistoryUseCase;
import com.fluxenture.core.employeehistory.domain.EmployeeHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee-history")
@RequiredArgsConstructor
public class EmployeeHistoryController {

    private final SaveEmployeeHistoryUseCase saveUseCase;
    private final DeleteEmployeeHistoryUseCase deleteUseCase;
    private final GetEmployeeHistoryByEmployeeIdUseCase getByEmployeeIdUseCase;
    private final GetEmployeeHistoryByEmployeeNameUseCase getByEmployeeNameUseCase;

    @PostMapping
    public ResponseEntity<EmployeeHistory> save(@RequestBody EmployeeHistory history) {
        return ResponseEntity.ok(saveUseCase.execute(history));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        deleteUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/employee/id/{employeeId}")
    public ResponseEntity<List<EmployeeHistory>> getByEmployeeId(@PathVariable String employeeId) {
        return ResponseEntity.ok(getByEmployeeIdUseCase.execute(employeeId));
    }

    @GetMapping("/employee/name/{employeeName}")
    public ResponseEntity<List<EmployeeHistory>> getByEmployeeName(@PathVariable String employeeName) {
        return ResponseEntity.ok(getByEmployeeNameUseCase.execute(employeeName));
    }
}
