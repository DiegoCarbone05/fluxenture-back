package com.fluxenture.core.employeehistory.infrastructure.output.persistence.mapper;

import com.fluxenture.core.employeehistory.domain.EmployeeHistory;
import com.fluxenture.core.employeehistory.infrastructure.output.persistence.entity.EmployeeHistoryEntity;

public class EmployeeHistoryMapper {

    public static EmployeeHistoryEntity toEntity(EmployeeHistory domain) {
        if (domain == null) return null;

        return EmployeeHistoryEntity.builder()
                .id(domain.getId())
                .employeeId(domain.getEmployeeId())
                .date(domain.getDate())
                .type(domain.getType())
                .employeeName(domain.getEmployeeName())
                .description(domain.getDescription())
                .docId(domain.getDocId())
                .build();
    }

    public static EmployeeHistory toDomain(EmployeeHistoryEntity entity) {
        if (entity == null) return null;

        return EmployeeHistory.builder()
                .id(entity.getId())
                .employeeId(entity.getEmployeeId())
                .date(entity.getDate())
                .type(entity.getType())
                .employeeName(entity.getEmployeeName())
                .description(entity.getDescription())
                .docId(entity.getDocId())
                .build();
    }
}
