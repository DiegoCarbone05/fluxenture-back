package com.fluxenture.core.absent.application;

import com.fluxenture.core.absent.domain.AbsentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteAbsentUseCase {
    private final AbsentRepository repository;

    public void execute(String id) {
        repository.deleteById(id);
    }
}
