package com.fluxenture.core.users.domain;


public interface UserRepository {
    User save(User newUser);

    void delete();

    void list();

    User findByMail(String mail);

    void findById();
}
