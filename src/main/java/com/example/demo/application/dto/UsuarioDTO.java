package com.example.demo.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@NoArgsConstructor @AllArgsConstructor @Data @Builder
public class UsuarioDTO implements Serializable {

    @NotBlank(message = "{usuario.login.not.blank}")
    private String username;

/*    @NotBlank(message = "{usuario.senha.not.blank}")
    private String senha;*/
}
