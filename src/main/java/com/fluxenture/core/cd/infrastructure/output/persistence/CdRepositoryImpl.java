package com.fluxenture.core.cd.infrastructure.output.persistence;

import com.mongodb.client.result.DeleteResult;
import com.fluxenture.core.cd.domain.Cd;
import com.fluxenture.core.cd.domain.CdRepository;
import com.fluxenture.core.cd.infrastructure.output.persistence.entity.CdEntity;
import com.fluxenture.core.cd.infrastructure.output.persistence.mapper.CdMapper;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CdRepositoryImpl implements CdRepository {

    private final MongoTemplate mongoTemplate;

    public CdRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public Cd save(Cd newCd) {
        CdEntity entity = CdMapper.toEntity(newCd);
        mongoTemplate.save(entity);
        return newCd;
    }

    @Override
    public DeleteResult delete(String id) {
        Query query = new Query(Criteria.where("_id").is(new ObjectId(id)));
        return mongoTemplate.remove(query, CdEntity.class);
    }

    @Override
    public void update(Cd cdMod) {
        if (cdMod.getId() == null) {return;}
        Query query = new Query(Criteria.where("_id").is(new ObjectId(cdMod.getId())));
        Update  update = new Update();
        update.set("trackingNumber", cdMod.getTrackingNumber());
        update.set("emissionDate", cdMod.getEmissionDate());
        update.set("employeeId", cdMod.getEmployeeId());
        update.set("fileId", cdMod.getFileId());
        update.set("tnt", cdMod.getTnt());
        update.set("obs", cdMod.getObs());
        update.set("trackingCompleted", cdMod.getTrackingCompleted());

        mongoTemplate.updateFirst(query, update, CdEntity.class);

    }

    @Override
    public List<Cd> getAll() {
        List<CdEntity> listCdEntity = mongoTemplate.findAll(CdEntity.class);
        return listCdEntity.stream()
                .map(CdMapper::toDomain) // Mapeamos uno por uno
                .collect(Collectors.toList());
    }

    @Override
    public void getById() {

    }
}
