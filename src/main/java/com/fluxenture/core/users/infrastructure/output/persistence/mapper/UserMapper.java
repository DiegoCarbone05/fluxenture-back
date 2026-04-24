package com.fluxenture.core.users.infrastructure.output.persistence.mapper;

import com.fluxenture.core.users.domain.User;
import com.fluxenture.core.users.infrastructure.output.persistence.entity.UserEntity;

public class UserMapper {

    public static UserEntity toEntity(User domain) {
        if (domain == null) return null;

        UserEntity entity = new UserEntity();
        entity.setId(domain.getId());
        entity.setUsername(domain.getUsername());
        entity.setPassword(domain.getPassword());
        entity.setMail(domain.getMail());
        entity.setRole(domain.getRole());
        entity.setAudit(domain.getAudit());
        return entity;
    }

    public static User toDomain(UserEntity entity) {
        if (entity == null) return null;

        return User.builder()
                .id(entity.getId())
                .mail(entity.getMail())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .role(entity.getRole())
                .audit(entity.getAudit())
                .build();
    }

}
