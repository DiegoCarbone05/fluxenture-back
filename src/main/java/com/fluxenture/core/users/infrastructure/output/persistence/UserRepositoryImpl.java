package com.fluxenture.core.users.infrastructure.output.persistence;

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
    public User save(User newUser) {
        UserEntity entity = UserMapper.toEntity(newUser);
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
        User user = UserMapper.toDomain(entity);
        return user;
    }

    @Override
    public void findById() {

    }
}
