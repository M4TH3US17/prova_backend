package com.example.demo.entities.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.io.Serializable;

@NoArgsConstructor @AllArgsConstructor @Data
public class UsuarioDTO implements Serializable {

    @NotBlank(message = "{usuario.login.not.blank}")
    @Size(min = 2, max = 30, message = "{usuario.login.size}")
    private String login;

    @NotBlank(message = "{usuario.senha.not.null}")
    private String senha;
}
