package com.fluxenture.core.users.infrastructure.input.rest;

import com.fluxenture.core.users.application.GetMeUseCase;
import com.fluxenture.core.users.application.LoginUseCase;
import com.fluxenture.core.users.domain.dto.LoginDTO;
import com.fluxenture.core.users.domain.TokenResponse;
import com.fluxenture.core.users.domain.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth") // Acordate que en SecurityConfig pusimos que /auth/** es público
public class AuthController {

    private final LoginUseCase loginUseCase;
    private final GetMeUseCase getMeUseCase;

    public AuthController(LoginUseCase loginUseCase, GetMeUseCase getMeUseCase) {
        this.loginUseCase = loginUseCase;
        this.getMeUseCase = getMeUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginDTO loginDTO) {
        return loginUseCase.execute(loginDTO);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getMe(@RequestHeader("Authorization") String token) {
        return getMeUseCase.execute(token);
    }
}
