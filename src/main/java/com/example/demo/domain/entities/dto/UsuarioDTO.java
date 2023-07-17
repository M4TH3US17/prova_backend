package com.example.demo.domain.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@NoArgsConstructor @AllArgsConstructor @Data
public class UsuarioDTO implements Serializable {

    @NotBlank(message = "{usuario.login.not.blank}")
    private String login;

    @NotBlank(message = "{usuario.senha.not.blank}")
    private String senha;
}
