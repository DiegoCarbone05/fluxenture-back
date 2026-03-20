package com.fluxenture.core.users.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id = null;
    private String mail;
    private String username;
    private String password;
}
