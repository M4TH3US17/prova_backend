package com.example.demo.entities.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor @AllArgsConstructor @Data
public class UsuarioDTO implements Serializable {

    private String login;
    private String senha;
}
