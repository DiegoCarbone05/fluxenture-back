package com.fluxenture.core.absent.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Absent {
    private String id;
    private String employeeId;
    private EAbsentType type;
    private LocalDate startDate;
    private LocalDate endDate;
    private String documentId;    // Google Drive File ID
    private String observations;
    private boolean justified;
}
