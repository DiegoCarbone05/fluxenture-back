package com.fluxenture.core.cd.domain;

import com.fluxenture.core.shared.domain.AuditMetadata;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cd {
    @Nullable
    private String id;
    private int trackingNumber;
    private String emissionDate;
    private String employeeId;
    private String fileId;
    @Nullable
    private List<Tnt> tnt;
    private String obs;
    private Boolean trackingCompleted;
    private AuditMetadata audit;
}
