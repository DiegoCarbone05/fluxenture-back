package com.fluxenture.core.absent.application;

import com.fluxenture.core.absent.domain.Absent;
import com.fluxenture.core.absent.domain.AbsentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAbsentsByEmployeeUseCase {
    private final AbsentRepository repository;

    public List<Absent> execute(String employeeId) {
        return repository.findByEmployeeId(employeeId);
    }
}
