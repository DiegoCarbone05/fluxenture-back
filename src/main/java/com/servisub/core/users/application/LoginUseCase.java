package com.servisub.core.users.application;

import com.servisub.core.shared.security.config.SecurityConfig;
import com.servisub.core.shared.security.jwt.JwtService;
import com.servisub.core.users.domain.LoginDTO;
import com.servisub.core.users.domain.User;
import com.servisub.core.users.domain.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoginUseCase {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final SecurityConfig securityConfig;

    public LoginUseCase(UserRepository userRepository, JwtService jwtService, SecurityConfig securityConfig) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.securityConfig = securityConfig;

    }

    public ResponseEntity<String> execute(LoginDTO loginDTO){

        User userFinded = userRepository.findByMail(loginDTO.getMail());
        if(userFinded == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        String token = jwtService.createToken(userFinded);

        if(securityConfig.passwordEncoder().matches(loginDTO.getPassword(), userFinded.getPassword())){
            return new ResponseEntity<>(token, HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
