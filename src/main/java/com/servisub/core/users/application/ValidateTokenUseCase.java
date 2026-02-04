package com.servisub.core.users.application;

import com.servisub.core.shared.security.jwt.JwtService;
import com.servisub.core.users.domain.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ValidateTokenUseCase {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public ValidateTokenUseCase(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;

    }

    public ResponseEntity<String> execute(String token) {
        try{
            boolean tokenStatus = jwtService.isTokenValid(token);
            if(tokenStatus) return new ResponseEntity<>(HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
