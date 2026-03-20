package com.fluxenture.core.docs.domain;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doc {
    @Nullable
    private String id;
    private String employeeId;
    private EDocType type;
    private String driveFileId;
    private String uploadDate;
    @Nullable
    private String description;
}
