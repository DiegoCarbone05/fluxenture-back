package com.fluxenture.core.absent.infrastructure.output.persistence.entity;

import com.fluxenture.core.absent.domain.EAbsentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "absents")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AbsentEntity {
    @Id
    private String id;
    private String employeeId;
    private EAbsentType type;
    private LocalDate startDate;    private LocalDate endDate;
    private String documentId;
    private String observations;
    private boolean justified;
}
