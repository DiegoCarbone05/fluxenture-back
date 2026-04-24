package com.fluxenture.core.users.infrastructure.output.persistence;

import com.fluxenture.core.shared.domain.AuditMetadata;
import com.fluxenture.core.users.domain.User;
import com.fluxenture.core.users.domain.UserRepository;
import com.fluxenture.core.users.infrastructure.output.persistence.entity.UserEntity;
import com.fluxenture.core.users.infrastructure.output.persistence.mapper.UserMapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


@Repository
public class UserRepositoryImpl implements UserRepository {

    private final MongoTemplate mongoTemplate;

    public UserRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public User save(User user) {
        if (user.getAudit() == null) {
            user.setAudit(AuditMetadata.create("SYSTEM"));
        } else {
            user.getAudit().update("SYSTEM");
        }
        UserEntity entity = UserMapper.toEntity(user);
        mongoTemplate.save(entity);
        return UserMapper.toDomain(entity);
    }

    @Override
    public void delete() {

    }


    @Override
    public void list() {

    }

    @Override
    public User findByMail(String mail) {
        Query query = new Query();
        query.addCriteria(Criteria.where("mail").is(mail));
        UserEntity entity = mongoTemplate.findOne(query, UserEntity.class);
        return UserMapper.toDomain(entity);
    }

    @Override
    public void findById() {

    }
}
