package com.fluxenture.core.employeehistory.application;

import com.fluxenture.core.employeehistory.domain.EmployeeHistory;
import com.fluxenture.core.employeehistory.infrastructure.output.persistence.entity.EmployeeHistoryRepository;
import com.fluxenture.core.employeehistory.infrastructure.output.persistence.mapper.EmployeeHistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetEmployeeHistoryByEmployeeIdUseCase {

    private final EmployeeHistoryRepository repository;

    public List<EmployeeHistory> execute(String employeeId) {
        return repository.findByEmployeeId(employeeId).stream()
                .map(EmployeeHistoryMapper::toDomain)
                .collect(Collectors.toList());
    }
}
