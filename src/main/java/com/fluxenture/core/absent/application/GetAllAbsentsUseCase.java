package com.fluxenture.core.absent.application;

import com.fluxenture.core.absent.domain.Absent;
import com.fluxenture.core.absent.domain.AbsentRepository;
import com.fluxenture.core.absent.domain.AbsentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllAbsentsUseCase {
    private final AbsentRepository repository;

    public List<Absent> execute() {
        return repository.findAll();
    }
}
