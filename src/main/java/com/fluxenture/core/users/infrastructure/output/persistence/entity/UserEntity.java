package com.fluxenture.core.users.infrastructure.output.persistence.entity;

import com.fluxenture.core.shared.domain.AuditMetadata;
import com.fluxenture.core.users.domain.ERole;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class UserEntity {
    @Id
    private String id;
    private String mail;
    private String username;
    private String password;
    private ERole role;
    private AuditMetadata audit;
}
