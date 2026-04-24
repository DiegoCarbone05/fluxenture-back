package com.fluxenture.core.employees.infrastructure.output.persistence.entity;

import com.fluxenture.core.employees.domain.ESector;
import com.fluxenture.core.employees.domain.EService;
import com.fluxenture.core.shared.domain.AuditMetadata;
import jakarta.annotation.Nullable;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "employees")
public class EmployeeEntity {
    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("employee_id")
    @Indexed(unique = true)
    private int employeeID; // Legajo

    @Indexed(unique = true)
    private Long cuil; // Cambiado a Long para evitar desbordamiento (11 dígitos)

    private ESector sector;
    private EService service;
    private Boolean isOperational;

    // --- Datos de Identidad y Estado ---
    private String documentType;
    private String documentNumber;
    private String birthDate;    // Fecha de Nacimiento
    private String gender;       // Sexo
    private String civilStatus;  // Estado Civil
    private String nationality;  // Nacionalidad

    // --- Contacto y Domicilio ---
    @Nullable
    private String adress;
    private String city;
    private String province;
    private String country;
    private String zipCode;

    @Nullable
    private String phone;
    @Nullable
    private String cellPhone;
    private String email;

    // --- Fechas de Contrato ---
    @Nullable
    private String entryDate;
    @Nullable
    private String leaveDate;

    // --- Auditoría ---
    private AuditMetadata audit;
}
