package com.fluxenture.core.docs.domain;

import java.util.List;

public interface DocRepository {
    void save(Doc doc);
    List<Doc> findByEmployeeId(String employeeId);
    List<Doc> getAll();
    void delete(String id);
}
