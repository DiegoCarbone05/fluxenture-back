package com.fluxenture.core.employeehistory.application;

import com.fluxenture.core.employeehistory.infrastructure.output.persistence.entity.EmployeeHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteEmployeeHistoryUseCase {

    private final EmployeeHistoryRepository repository;

    public void execute(String id) {
        repository.deleteById(id);
    }
}
