package com.servisub.core.users.application;

import com.servisub.core.shared.security.config.SecurityConfig;
import com.servisub.core.users.domain.User;
import com.servisub.core.users.domain.UserRepository;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {
    private final UserRepository userRepository;
    private final SecurityConfig securityConfig;

    public CreateUserUseCase(UserRepository userRepository, SecurityConfig securityConfig) {
        this.userRepository = userRepository;
        this.securityConfig = securityConfig;
    }

    public User execute(User newUser) {
        String encodedPassword = securityConfig.passwordEncoder().encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);
        return userRepository.save(newUser);
    }
}
