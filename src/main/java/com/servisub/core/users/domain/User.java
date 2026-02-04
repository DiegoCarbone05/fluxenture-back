package com.servisub.core.users.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  // <--- REQUERIDO para que Spring pueda crear el objeto vacío
@AllArgsConstructor
public class User {
    private String id = null;
    private String mail;
    private String username;
    private String password;
}
