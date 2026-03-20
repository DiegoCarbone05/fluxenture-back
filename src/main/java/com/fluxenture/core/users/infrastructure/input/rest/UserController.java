package com.fluxenture.core.users.infrastructure.input.rest;

import com.fluxenture.core.users.application.CreateUserUseCase;
import com.fluxenture.core.users.application.LoginUseCase;
import com.fluxenture.core.users.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase,  LoginUseCase loginUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping("/add")
    public ResponseEntity<User> add(@RequestBody User user) {
        User createdUser = createUserUseCase.execute(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

}
