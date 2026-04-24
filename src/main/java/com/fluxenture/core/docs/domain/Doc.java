package com.fluxenture.core.docs.domain;

import com.fluxenture.core.shared.domain.AuditMetadata;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doc {
    @Nullable
    private String id;
    private String employeeId;
    private EDocType type;
    private String driveFileId;
    private String uploadDate;
    @Nullable
    private String description;
    private AuditMetadata audit;
}
