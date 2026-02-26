package com.servisub.core.employees.infrastructure.output.persistence;

import com.servisub.core.employees.domain.Employe;
import com.servisub.core.employees.domain.EmployeRepository;
import com.servisub.core.employees.domain.ResponseDTO;
import com.servisub.core.employees.infrastructure.output.persistence.entity.EmployeeEntity;
import com.servisub.core.employees.infrastructure.output.persistence.mapper.EmployeeMapper;
import org.apache.coyote.Response;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeRepositoryImpl implements EmployeRepository {

    private final MongoTemplate mongoTemplate;

    public EmployeRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public void save(Employe employe) {
        EmployeeEntity entity = EmployeeMapper.toEntity(employe);
        mongoTemplate.save(entity);
    }

    @Override
    public ResponseEntity<ResponseDTO> loadAllEmployees(List<Employe> employees) {

        try{
            List<EmployeeEntity> entities = employees.stream()
                    .map(EmployeeMapper::toEntity)
                    .toList();
            mongoTemplate.insertAll(entities);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO("EMPLEADOS SUBIDOS"));
        }catch (Exception e){
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
    public List<Employe> getAllEmployees() {
        List<EmployeeEntity> entities = mongoTemplate.findAll(EmployeeEntity.class);
        // 2. Mapeamos la lista de Entidad a Dominio
        return entities.stream()
                .map(EmployeeMapper::toDomain) // Usamos el método estático de tu mapper
                .toList(); // Esto crea la List<Employee> final
    }


    @Override
    public void getEmploye() {

    }


}
