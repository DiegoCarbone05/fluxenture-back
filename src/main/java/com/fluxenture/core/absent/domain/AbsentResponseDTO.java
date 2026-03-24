package com.fluxenture.core.absent.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AbsentResponseDTO {
    private String id;
    private String employeeId;
    private EAbsentType type;
    private boolean justified;
    private String documentId;
    private LocalDate originalStartDate;
    private LocalDate originalEndDate;
    private int impactDaysInMonth;
    private String observations;

    // Constructor que el compilador está pidiendo
    public AbsentResponseDTO(Absent absent, int impactDaysInMonth) {
        this.id = absent.getId();
        this.employeeId = absent.getEmployeeId();
        this.type = absent.getType();
        this.justified = absent.isJustified();
        this.documentId = absent.getDocumentId();
        this.originalStartDate = absent.getStartDate();
        this.originalEndDate = absent.getEndDate();
        this.impactDaysInMonth = impactDaysInMonth;
        this.observations = absent.getObservations();
    }

}
