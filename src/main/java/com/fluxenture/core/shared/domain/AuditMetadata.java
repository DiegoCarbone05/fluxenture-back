package com.fluxenture.core.shared.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditMetadata {
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    // Para cuando creamos algo nuevo
    public static AuditMetadata create(String username) {
        return AuditMetadata.builder()
                .createdAt(LocalDateTime.now())
                .createdBy(username)
                .updatedAt(LocalDateTime.now())
                .updatedBy(username)
                .build();
    }

    // Para cuando actualizamos algo existente
    public void update(String username) {
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = username;
    }
}
