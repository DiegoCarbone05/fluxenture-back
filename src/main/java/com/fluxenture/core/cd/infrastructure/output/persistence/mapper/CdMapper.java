package com.fluxenture.core.cd.infrastructure.output.persistence.mapper;

import com.fluxenture.core.cd.domain.Cd;
import com.fluxenture.core.cd.infrastructure.output.persistence.entity.CdEntity;
import com.fluxenture.core.users.domain.User;
import com.fluxenture.core.users.infrastructure.output.persistence.entity.UserEntity;

public class CdMapper {

    public static CdEntity toEntity(Cd domain) {
        if (domain == null) return null;

        CdEntity entity = new CdEntity();
        entity.setId(domain.getId());
        entity.setTrackingNumber(domain.getTrackingNumber());
        entity.setEmissionDate(domain.getEmissionDate());
        entity.setEmployeeId(domain.getEmployeeId());
        entity.setFileId(domain.getFileId());
        entity.setTnt(domain.getTnt());
        entity.setObs(domain.getObs());
        entity.setTrackingCompleted(domain.getTrackingCompleted());
        entity.setAudit(domain.getAudit());

        return entity;
    }

    public static Cd toDomain(CdEntity entity) {
        if (entity == null) return null;

        return Cd.builder()
                .id(entity.getId())
                .trackingNumber(entity.getTrackingNumber())
                .emissionDate(entity.getEmissionDate())
                .employeeId(entity.getEmployeeId())
                .fileId(entity.getFileId())
                .tnt(entity.getTnt())
                .obs(entity.getObs())
                .trackingCompleted(entity.getTrackingCompleted())
                .audit(entity.getAudit())
                .build();
    }
}
