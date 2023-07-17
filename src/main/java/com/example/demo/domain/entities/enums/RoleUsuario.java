package com.example.demo.domain.entities.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleUsuario implements GrantedAuthority {

    ROLE_USUARIO("ROLE_USUARIO"),
    ROLE_ADMIN("ROLE_ADMIN");

    private String role;

    RoleUsuario(String role){this.role = role;}

    public String getRole() { return role; }

    @Override
    public String getAuthority() {
        return this.role;
    }
}
