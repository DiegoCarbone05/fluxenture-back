package com.servisub.core.employees.infrastructure.output.persistence.mapper;

import com.servisub.core.employees.domain.Employe;
import com.servisub.core.employees.infrastructure.output.persistence.entity.EmployeeEntity;

public class EmployeeMapper {
    public static EmployeeEntity toEntity(Employe domain) {
        if (domain == null) return null;

        EmployeeEntity entity = new EmployeeEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setEmployeeID(domain.getEmployeeID());
        entity.setCuil(domain.getCuil());
        entity.setSector(domain.getSector());
        entity.setIsOperational(domain.getIsOperational());

        // --- Identidad y Estado ---
        entity.setDocumentType(domain.getDocumentType());
        entity.setDocumentNumber(domain.getDocumentNumber());
        entity.setBirthDate(domain.getBirthDate());
        entity.setGender(domain.getGender());
        entity.setCivilStatus(domain.getCivilStatus());
        entity.setNationality(domain.getNationality());

        // --- Ubicación y Contacto ---
        entity.setAdress(domain.getAdress());
        entity.setCity(domain.getCity());
        entity.setProvince(domain.getProvince());
        entity.setCountry(domain.getCountry());
        entity.setZipCode(domain.getZipCode());
        entity.setPhone(domain.getPhone());
        entity.setCellPhone(domain.getCellPhone());
        entity.setEmail(domain.getEmail());

        // --- Fechas ---
        entity.setEntryDate(domain.getEntryDate());
        entity.setLeaveDate(domain.getLeaveDate());

        return entity;
    }

    public static Employe toDomain(EmployeeEntity entity) {
        if (entity == null) return null;

        Employe domain = new Employe();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setEmployeeID(entity.getEmployeeID());
        domain.setCuil(entity.getCuil());
        domain.setSector(entity.getSector());
        domain.setIsOperational(entity.getIsOperational());

        // --- Identidad y Estado ---
        domain.setDocumentType(entity.getDocumentType());
        domain.setDocumentNumber(entity.getDocumentNumber());
        domain.setBirthDate(entity.getBirthDate());
        domain.setGender(entity.getGender());
        domain.setCivilStatus(entity.getCivilStatus());
        domain.setNationality(entity.getNationality());

        // --- Ubicación y Contacto ---
        domain.setAdress(entity.getAdress());
        domain.setCity(entity.getCity());
        domain.setProvince(entity.getProvince());
        domain.setCountry(entity.getCountry());
        domain.setZipCode(entity.getZipCode());
        domain.setPhone(entity.getPhone());
        domain.setCellPhone(entity.getCellPhone());
        domain.setEmail(entity.getEmail());

        // --- Fechas ---
        domain.setEntryDate(entity.getEntryDate());
        domain.setLeaveDate(entity.getLeaveDate());

        return domain;
    }
}
