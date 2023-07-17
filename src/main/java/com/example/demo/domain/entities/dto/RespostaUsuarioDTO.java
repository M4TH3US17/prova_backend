package com.example.demo.domain.entities.dto;

import com.example.demo.domain.entities.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor @Getter
public class RespostaUsuarioDTO implements Serializable {

    private Long   idUsuario;
    private String username;

    public RespostaUsuarioDTO(Usuario usuario) {
        idUsuario = usuario.getId();
        username  = usuario.getUsername();
    }
}
