package com.fluxenture.core.absent.infrastructure.output.persistence.mapper;

import com.fluxenture.core.absent.domain.Absent;
import com.fluxenture.core.absent.infrastructure.output.persistence.entity.AbsentEntity;
import org.springframework.stereotype.Component;

@Component
public class AbsentMapper {

    public Absent toDomain(AbsentEntity entity) {
        if (entity == null) return null;
        return Absent.builder()
                .id(entity.getId())
                .employeeId(entity.getEmployeeId())
                .type(entity.getType())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .documentId(entity.getDocumentId())
                .observations(entity.getObservations())
                .justified(entity.isJustified())
                .audit(entity.getAudit())
                .build();
    }

    public AbsentEntity toEntity(Absent domain) {
        if (domain == null) return null;
        return AbsentEntity.builder()
                .id(domain.getId())
                .employeeId(domain.getEmployeeId())
                .type(domain.getType())
                .startDate(domain.getStartDate())
                .endDate(domain.getEndDate())
                .documentId(domain.getDocumentId())
                .observations(domain.getObservations())
                .justified(domain.isJustified())
                .audit(domain.getAudit())
                .build();
    }
}
