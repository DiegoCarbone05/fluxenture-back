package com.fluxenture.core.docs.infrastructure.output.persistence.mapper;

import com.fluxenture.core.docs.domain.Doc;
import com.fluxenture.core.docs.infrastructure.output.persistence.entity.DocEntity;

public class DocMapper {
    public static DocEntity toEntity(Doc domain) {
        DocEntity entity = new DocEntity();
        entity.setId(domain.getId());
        entity.setEmployeeId(domain.getEmployeeId());
        entity.setType(domain.getType());
        entity.setDriveFileId(domain.getDriveFileId());
        entity.setUploadDate(domain.getUploadDate());
        entity.setDescription(domain.getDescription());
        entity.setAudit(domain.getAudit());
        return entity;
    }

    public static Doc toDomain(DocEntity entity) {
        if (entity == null) return null;
        return Doc.builder()
                .id(entity.getId())
                .employeeId(entity.getEmployeeId())
                .type(entity.getType())
                .driveFileId(entity.getDriveFileId())
                .uploadDate(entity.getUploadDate())
                .description(entity.getDescription())
                .audit(entity.getAudit())
                .build();
    }
}
