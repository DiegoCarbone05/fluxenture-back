package com.fluxenture.core.employeehistory.infrastructure.output.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "employee_history")
public class EmployeeHistoryEntity {
    @Id
    private String id;
    private String employeeId;
    private String date;
    private String type;
    private String employeeName;
    private String description;
    private String docId;
}
