package com.fluxenture.core.users.domain.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String mail;
    private String password;
}
