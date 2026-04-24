package com.fluxenture.core.employees.domain;

import com.fluxenture.core.shared.domain.AuditMetadata;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Nullable
    private String id;
    private long cuil;
    private String name;
    private int employeeID; // Legajo
    private Boolean isOperational = true;
    private ESector sector;
    private EService service;

    // --- Identidad y Estado Civil ---
    private String documentType;
    private String documentNumber;
    private LocalDate birthDate;    // Fecha de Nacimiento
    private String gender;       // Sexo
    private String civilStatus;  // Estado Civil
    private String nationality;  // Nacionalidad

    // --- Ubicación y Contacto ---
    @Nullable
    private String adress;       // Domicilio
    private String city;         // Localidad
    private String province;     // Provincia
    private String country;      // País
    private String zipCode;      // Código Postal

    @Nullable
    private String phone;        // Teléfono fijo
    @Nullable
    private String cellPhone;    // Celular
    private String email;        // Correo Electrónico

    // --- Fechas de Contrato ---
    @Nullable
    private LocalDate entryDate;    // Fecha de alta
    @Nullable
    private LocalDate leaveDate;    // Fecha de baja

    // --- Auditoría ---
    private AuditMetadata audit;
}
