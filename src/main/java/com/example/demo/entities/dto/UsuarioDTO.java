package com.example.demo.entities.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.io.Serializable;

@NoArgsConstructor @AllArgsConstructor @Data
public class UsuarioDTO implements Serializable {

    @NotBlank(message = "{usuario.login.not.blank}")
    private String login;

    @NotBlank(message = "{usuario.senha.not.blank}")
    private String senha;
}
