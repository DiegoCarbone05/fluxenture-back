package com.servisub.core.users.infrastructure.input.rest;

import com.servisub.core.users.application.GetMeUseCase;
import com.servisub.core.users.application.LoginUseCase;
import com.servisub.core.users.application.ValidateTokenUseCase;
import com.servisub.core.users.domain.LoginDTO;
import io.jsonwebtoken.Claims;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth") // Acordate que en SecurityConfig pusimos que /auth/** es público
public class AuthController {

    private final LoginUseCase loginUseCase;
    private final ValidateTokenUseCase validateTokenUseCase;
    private final GetMeUseCase getMeUseCase;

    public AuthController(LoginUseCase loginUseCase,  ValidateTokenUseCase validateTokenUseCase,   GetMeUseCase getMeUseCase) {
        this.loginUseCase = loginUseCase;
        this.validateTokenUseCase = validateTokenUseCase;
        this.getMeUseCase = getMeUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        return loginUseCase.execute(loginDTO);
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam String token) {
        // Si llegamos acá, es porque el JwtAuthenticationFilter validó el token con éxito
        return validateTokenUseCase.execute(token);
    }

    @GetMapping("/me")
    public ResponseEntity<Claims> getMe(@RequestParam String token) {
        return getMeUseCase.execute(token);
    }
}