package com.fluxenture.core.employeehistory.infrastructure.output.persistence.entity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeHistoryRepository extends MongoRepository<EmployeeHistoryEntity, String> {
    List<EmployeeHistoryEntity> findByEmployeeId(String employeeId);
    List<EmployeeHistoryEntity> findByEmployeeName(String employeeName);
}
