package com.fluxenture.core.absent.domain;

import java.util.List;
import java.util.Optional;

public interface AbsentRepository {
    Absent save(Absent absent);
    Optional<Absent> findById(String id);
    List<Absent> findAll();
    List<AbsentResponseDTO> findByMonth(int year, int month);
    List<Absent> findByEmployeeId(String employeeId);
    void deleteById(String id);
}
