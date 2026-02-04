package com.servisub.core.users.infrastructure.input.rest;

import com.servisub.core.users.application.CreateUserUseCase;
import com.servisub.core.users.application.LoginUseCase;
import com.servisub.core.users.domain.LoginDTO;
import com.servisub.core.users.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
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
