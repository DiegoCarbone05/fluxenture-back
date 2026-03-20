package com.fluxenture.core.absent.application;

import com.fluxenture.core.absent.domain.Absent;
import com.fluxenture.core.absent.domain.AbsentRepository;
import com.fluxenture.core.absent.domain.AbsentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetAbsentsByMonthUseCase {
    private final AbsentRepository repository;

    public List<AbsentResponseDTO> execute(int year, int month) {
        return repository.findByMonth(year, month);
    }
}
