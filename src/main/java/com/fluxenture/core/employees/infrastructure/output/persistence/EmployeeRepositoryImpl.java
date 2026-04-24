package com.fluxenture.core.employees.infrastructure.output.persistence;

import com.fluxenture.core.docs.domain.Doc;
import com.fluxenture.core.employees.domain.Employee;
import com.fluxenture.core.employees.domain.EmployeeRepository;
import com.fluxenture.core.employees.domain.ResponseDTO;
import com.fluxenture.core.employees.infrastructure.output.persistence.entity.EmployeeEntity;
import com.fluxenture.core.employees.infrastructure.output.persistence.mapper.EmployeeMapper;
import com.fluxenture.core.shared.domain.AuditMetadata;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final MongoTemplate mongoTemplate;

    public EmployeeRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Employee save(Employee employee) {
        if (employee.getAudit() == null) {
            employee.setAudit(AuditMetadata.create("SYSTEM"));
        } else {
            employee.getAudit().update("SYSTEM");
        }
        EmployeeEntity entity = EmployeeMapper.toEntity(employee);
        EmployeeEntity savedEntity = mongoTemplate.save(entity);
        return EmployeeMapper.toDomain(savedEntity);
    }

    @Override
    public ResponseEntity<ResponseDTO> loadAllEmployees(List<Employee> employees) {
        try {
            List<EmployeeEntity> entities = employees.stream()
                    .map(EmployeeMapper::toEntity)
                    .toList();
            mongoTemplate.insertAll(entities);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO("EMPLEADOS SUBIDOS"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public void delete(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, EmployeeEntity.class);
    }

    @Override
    public void update() {
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<EmployeeEntity> entities = mongoTemplate.findAll(EmployeeEntity.class);
        return entities.stream()
                .map(EmployeeMapper::toDomain)
                .toList();
    }

    @Override
    public Employee getById(String id) {
        EmployeeEntity entity = mongoTemplate.findById(id, EmployeeEntity.class);
        return entity != null ? EmployeeMapper.toDomain(entity) : null;
    }

    @Override
    public void getEmployee() {
    }


    @Override
    public List<Employee> findByName(String name, int size) {
        Criteria findName = Criteria.where("name").regex(name, "i"); //Crea un criterio de busqueda
        Query query = new Query(findName).limit(size); //Arma una query con criterio y límite
        List<EmployeeEntity> entities = mongoTemplate.find(query, EmployeeEntity.class); //Ejecuta la consulta
        return entities.stream() //convierte al Dominio
                .map(EmployeeMapper::toDomain)
                .collect(Collectors.toList());
    }
}
