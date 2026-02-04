package com.servisub.core.users.application;

import com.servisub.core.shared.security.jwt.JwtService;
import com.servisub.core.users.domain.User;
import com.servisub.core.users.domain.UserRepository;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetMeUseCase {

    private UserRepository userRepository;
    private JwtService jwtService;

    public GetMeUseCase(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public ResponseEntity<Claims> execute(String token) {
        try{
            return ResponseEntity.ok(jwtService.extractAllClaims(token));

        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
