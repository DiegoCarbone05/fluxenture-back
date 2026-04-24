package com.fluxenture.core.users.domain;

import com.fluxenture.core.shared.domain.AuditMetadata;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String mail;
    private String username;
    private String password;
    private ERole role;
    private AuditMetadata audit;
}
