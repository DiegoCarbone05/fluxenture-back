package com.servisub.core.users.application;

import com.servisub.core.shared.security.jwt.JwtService;
import com.servisub.core.users.domain.dto.UserDTO;
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

    public ResponseEntity<UserDTO> execute(String authHeader) {

        try{
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            String token = authHeader.substring(7);
            Claims claims = jwtService.extractAllClaims(token);

            UserDTO userInfo = new UserDTO();
            userInfo.setUsername(claims.getSubject());
            userInfo.setMail(claims.get("userMail", String.class));
            userInfo.setId(claims.get("userId", String.class));

            return new ResponseEntity<>(userInfo, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
