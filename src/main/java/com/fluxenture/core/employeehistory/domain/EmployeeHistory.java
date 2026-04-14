package com.fluxenture.core.employeehistory.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeHistory {
    private String id;
    private String employeeId;
    private String date;
    private String type;
    private String employeeName;
    private String description;
    private String docId;
}
