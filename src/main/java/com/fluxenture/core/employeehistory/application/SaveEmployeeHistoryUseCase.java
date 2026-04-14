package com.fluxenture.core.employeehistory.application;

import com.fluxenture.core.employeehistory.domain.EmployeeHistory;
import com.fluxenture.core.employeehistory.infrastructure.output.persistence.entity.EmployeeHistoryEntity;
import com.fluxenture.core.employeehistory.infrastructure.output.persistence.entity.EmployeeHistoryRepository;
import com.fluxenture.core.employeehistory.infrastructure.output.persistence.mapper.EmployeeHistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveEmployeeHistoryUseCase {

    private final EmployeeHistoryRepository repository;

    public EmployeeHistory execute(EmployeeHistory history) {
        EmployeeHistoryEntity entity = EmployeeHistoryMapper.toEntity(history);
        EmployeeHistoryEntity saved = repository.save(entity);
        return EmployeeHistoryMapper.toDomain(saved);
    }
}
