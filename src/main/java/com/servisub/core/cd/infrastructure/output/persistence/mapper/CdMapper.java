package com.servisub.core.cd.infrastructure.output.persistence.mapper;

import com.servisub.core.cd.domain.Cd;
import com.servisub.core.cd.infrastructure.output.persistence.entity.CdEntity;
import com.servisub.core.users.domain.User;
import com.servisub.core.users.infrastructure.output.persistence.entity.UserEntity;

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

        return entity;
    }

    public static Cd toDomain(CdEntity entity) {
        if (entity == null) return null;

        return new Cd(
                entity.getId(),
                entity.getTrackingNumber(),
                entity.getEmissionDate(),
                entity.getEmployeeId(),
                entity.getFileId(),
                entity.getTnt(),
                entity.getObs(),
                entity.getTrackingCompleted()
        );
    }
}
