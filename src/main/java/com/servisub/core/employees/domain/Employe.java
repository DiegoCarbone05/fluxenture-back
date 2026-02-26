package com.servisub.core.employees.domain;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employe {
    @Nullable
    private String id;
    private long cuil; // Cambiado a long para soportar los 11 dígitos del CUIL
    private String name;
    private int employeeID; // Legajo
    private Boolean isOperational = true;
    private ESector sector;

    // --- Identidad y Estado Civil ---
    private String documentType;
    private String documentNumber;
    private String birthDate;    // Fecha de Nacimiento
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
    private String entryDate;    // Fecha de alta
    @Nullable
    private String leaveDate;    // Fecha de baja
}
