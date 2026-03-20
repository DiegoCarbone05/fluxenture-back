package com.fluxenture.core.absent.infrastructure.input.rest;

import com.fluxenture.core.absent.application.*;
import com.fluxenture.core.absent.domain.Absent;
import com.fluxenture.core.absent.domain.AbsentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/absents")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AbsentController {

    private final SaveAbsentUseCase saveUseCase;
    private final DeleteAbsentUseCase deleteUseCase;
    private final GetAllAbsentsUseCase getAllUseCase;
    private final GetAbsentsByMonthUseCase getByMonthUseCase;
    private final GetAbsentsByEmployeeUseCase getByEmployeeUseCase;

    @PostMapping
    public ResponseEntity<Absent> save(@RequestBody Absent absent) {
        return ResponseEntity.ok(saveUseCase.execute(absent));
    }

    @GetMapping
    public ResponseEntity<List<Absent>> getAll() {
        return ResponseEntity.ok(getAllUseCase.execute());
    }

    @GetMapping("/month/{year}/{month}")
    public ResponseEntity<List<AbsentResponseDTO>> getByMonth(@PathVariable int year, @PathVariable int month) {
        return ResponseEntity.ok(getByMonthUseCase.execute(year, month));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Absent>> getByEmployee(@PathVariable String employeeId) {
        return ResponseEntity.ok(getByEmployeeUseCase.execute(employeeId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        deleteUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
