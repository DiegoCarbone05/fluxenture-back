package com.fluxenture.core.docs.infrastructure.output.persistence;

import com.fluxenture.core.docs.domain.Doc;
import com.fluxenture.core.docs.domain.DocRepository;
import com.fluxenture.core.docs.infrastructure.output.persistence.entity.DocEntity;
import com.fluxenture.core.docs.infrastructure.output.persistence.mapper.DocMapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DocRepositoryImpl implements DocRepository {
    private final MongoTemplate mongoTemplate;

    public DocRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void save(Doc doc) {
        DocEntity entity = DocMapper.toEntity(doc);
        mongoTemplate.save(entity);
    }

    @Override
    public List<Doc> findByEmployeeId(String employeeId) {
        Query query = new Query(Criteria.where("employeeId").is(employeeId));
        List<DocEntity> entities = mongoTemplate.find(query, DocEntity.class);
        return entities.stream().map(DocMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Doc> getAll() {
        List<DocEntity> entities = mongoTemplate.findAll(DocEntity.class);
        return entities.stream().map(DocMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, DocEntity.class);
    }
}
