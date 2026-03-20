package com.fluxenture.core.absent.application;

import com.fluxenture.core.absent.domain.Absent;
import com.fluxenture.core.absent.domain.AbsentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveAbsentUseCase {
    private final AbsentRepository repository;

    public Absent execute(Absent absent) {
        return repository.save(absent);
    }
}
