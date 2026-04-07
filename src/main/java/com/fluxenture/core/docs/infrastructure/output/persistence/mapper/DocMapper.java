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
        entity.setUser(domain.getUser());
        return entity;
    }

    public static Doc toDomain(DocEntity entity) {
        Doc domain = new Doc();
        domain.setId(entity.getId());
        domain.setEmployeeId(entity.getEmployeeId());
        domain.setType(entity.getType());
        domain.setDriveFileId(entity.getDriveFileId());
        domain.setUploadDate(entity.getUploadDate());
        domain.setDescription(entity.getDescription());
        domain.setUser(entity.getUser());
        return domain;
    }
}
