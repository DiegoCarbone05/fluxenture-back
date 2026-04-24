package com.fluxenture.core.employees.infrastructure.output.persistence.mapper;

import com.fluxenture.core.employees.domain.Employee;
import com.fluxenture.core.employees.domain.EmployeeDTO;
import com.fluxenture.core.employees.infrastructure.output.persistence.entity.EmployeeEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class EmployeeMapper {
    // Formateador para el patrón DD/MM/YYYY
    private static final DateTimeFormatter DD_MM_YYYY_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy");
    // Formateador para el patrón YYYY-MM-DD (formato ISO por defecto)
    private static final DateTimeFormatter YYYY_MM_DD_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    public EmployeeDTO mapperAtDto(Employee employee) {
        return new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getEmployeeID()
        );
    }

    public static EmployeeEntity toEntity(Employee domain) {
        if (domain == null) return null;

        EmployeeEntity entity = new EmployeeEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setEmployeeID(domain.getEmployeeID());
        entity.setCuil(domain.getCuil());
        entity.setSector(domain.getSector());
        entity.setService(domain.getService());
        entity.setIsOperational(domain.getIsOperational());

        // --- Identidad y Estado ---
        entity.setDocumentType(domain.getDocumentType());
        entity.setDocumentNumber(domain.getDocumentNumber());
        // Las fechas se guardan como String en la entidad
        entity.setBirthDate(domain.getBirthDate() != null ? domain.getBirthDate().toString() : null);
        entity.setGender(domain.getGender());
        entity.setCivilStatus(domain.getCivilStatus());
        entity.setNationality(domain.getNationality());

        // --- Ubicación y Contacto ---
        entity.setAdress(domain.getAdress());
        entity.setCity(entity.getCity());
        entity.setProvince(entity.getProvince());
        entity.setCountry(entity.getCountry());
        entity.setZipCode(entity.getZipCode());
        entity.setPhone(entity.getPhone());
        entity.setCellPhone(entity.getCellPhone());
        entity.setEmail(entity.getEmail());

        // --- Fechas ---
        entity.setEntryDate(domain.getEntryDate() != null ? domain.getEntryDate().toString() : null);
        entity.setLeaveDate(domain.getLeaveDate() != null ? domain.getLeaveDate().toString() : null);

        // --- Auditoría ---
        entity.setAudit(domain.getAudit());

        return entity;
    }

    public static Employee toDomain(EmployeeEntity entity) {
        if (entity == null) return null;

        return Employee.builder()
                .id(entity.getId())
                .name(entity.getName())
                .employeeID(entity.getEmployeeID())
                .cuil(entity.getCuil())
                .sector(entity.getSector())
                .service(entity.getService())
                .isOperational(entity.getIsOperational())
                .documentType(entity.getDocumentType())
                .documentNumber(entity.getDocumentNumber())
                .birthDate(parseDateSafely(entity.getBirthDate()))
                .gender(entity.getGender())
                .civilStatus(entity.getCivilStatus())
                .nationality(entity.getNationality())
                .adress(entity.getAdress())
                .city(entity.getCity())
                .province(entity.getProvince())
                .country(entity.getCountry())
                .zipCode(entity.getZipCode())
                .phone(entity.getPhone())
                .cellPhone(entity.getCellPhone())
                .email(entity.getEmail())
                .entryDate(parseDateSafely(entity.getEntryDate()))
                .leaveDate(parseDateSafely(entity.getLeaveDate()))
                .audit(entity.getAudit())
                .build();
    }

    private static LocalDate parseDateSafely(String dateString) {
        if (dateString == null || dateString.isEmpty()) {
            return null;
        }
        try {
            // Intentamos primero con el formato DD/MM/YYYY
            return LocalDate.parse(dateString, DD_MM_YYYY_FORMATTER);
        } catch (DateTimeParseException e1) {
            try {
                // Si falla, intentamos con el formato YYYY-MM-DD (ISO_LOCAL_DATE)
                return LocalDate.parse(dateString, YYYY_MM_DD_FORMATTER);
            } catch (DateTimeParseException e2) {
                // Si ambos formatos fallan, registramos el error y devolvemos null
                System.err.println("Error parsing date string: " + dateString + " - Could not parse with DD/MM/YYYY or YYYY-MM-DD. " + e2.getMessage());
                return null;
            }
        }
    }
}
