package com.fluxenture.core.absent.infrastructure.output.persistence;

import com.fluxenture.core.absent.domain.Absent;
import com.fluxenture.core.absent.domain.AbsentRepository;
import com.fluxenture.core.absent.domain.AbsentResponseDTO;
import com.fluxenture.core.absent.infrastructure.output.persistence.entity.AbsentEntity;
import com.fluxenture.core.absent.infrastructure.output.persistence.mapper.AbsentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

interface MongoAbsentRepository extends MongoRepository<AbsentEntity, String> {
    List<AbsentEntity> findByEmployeeId(String employeeId);
    List<AbsentEntity> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(java.time.LocalDate endOfMonth, java.time.LocalDate startOfMonth);
}

@Repository
@RequiredArgsConstructor
public class AbsentRepositoryImpl implements AbsentRepository {
    private final MongoAbsentRepository mongoRepository;
    private final AbsentMapper mapper;

    @Override
    public Absent save(Absent absent) {
        // Aseguramos que si no hay endDate, sea igual a startDate (ausencia de un solo día)
        if (absent.getEndDate() == null) {
            absent.setEndDate(absent.getStartDate());
        }
        AbsentEntity entity = mapper.toEntity(absent);
        return mapper.toDomain(mongoRepository.save(entity));
    }

    @Override
    public Optional<Absent> findById(String id) {
        return mongoRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Absent> findAll() {
        return mongoRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<AbsentResponseDTO> findByMonth(int year, int month) {
        LocalDate startOfMonth = LocalDate.of(year, month, 1);
        LocalDate endOfMonth = startOfMonth.withDayOfMonth(startOfMonth.lengthOfMonth());

        // 1. Buscamos los registros que solapan
        List<Absent> results = mongoRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(endOfMonth, startOfMonth)
                .stream()
                .map(mapper::toDomain)
                .toList();

        System.out.println(results);

        // 2. Aplicamos el "recorte" para cada uno
        return results.stream().map(absent -> {
            // El inicio real a contar es el más tardío entre el inicio de la falta y el inicio del mes
            LocalDate actualStart = absent.getStartDate().isBefore(startOfMonth) ? startOfMonth : absent.getStartDate();

            // El fin real a contar es el más temprano entre el fin de la falta y el fin del mes
            LocalDate actualEnd = absent.getEndDate().isAfter(endOfMonth) ? endOfMonth : absent.getEndDate();

            // Calculamos los días que caen DENTRO de este mes
            long daysInMonth = java.time.temporal.ChronoUnit.DAYS.between(actualStart, actualEnd) + 1;

            return new AbsentResponseDTO(absent, (int) daysInMonth);
        }).toList();
    }

    @Override
    public List<Absent> findByEmployeeId(String employeeId) {
        return mongoRepository.findByEmployeeId(employeeId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        mongoRepository.deleteById(id);
    }
}
