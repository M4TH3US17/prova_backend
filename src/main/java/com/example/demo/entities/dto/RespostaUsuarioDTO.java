package com.example.demo.entities.dto;

import com.example.demo.entities.Usuario;
import lombok.*;

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
