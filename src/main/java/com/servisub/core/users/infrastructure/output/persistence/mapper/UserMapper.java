package com.servisub.core.users.infrastructure.output.persistence.mapper;

import com.servisub.core.users.domain.User;
import com.servisub.core.users.infrastructure.output.persistence.entity.UserEntity;

public class UserMapper {

    public static UserEntity toEntity(User domain) {
        if (domain == null) return null;

        UserEntity entity = new UserEntity();
        entity.setId(domain.getId());
        entity.setUsername(domain.getUsername());
        entity.setPassword(domain.getPassword());
        entity.setMail(domain.getMail());
        return entity;
    }

    public static User toDomain(UserEntity entity) {
        if (entity == null) return null;

        return new User(
                entity.getId(),
                entity.getMail(),
                entity.getUsername(),
                entity.getPassword()
        );
    }

}
