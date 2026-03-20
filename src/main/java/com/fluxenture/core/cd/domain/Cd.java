package com.fluxenture.core.cd.domain;

import com.fluxenture.core.employees.domain.Employe;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
