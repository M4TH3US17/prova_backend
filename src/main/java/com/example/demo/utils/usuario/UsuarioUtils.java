package com.example.demo.utils.usuario;

import com.example.demo.application.dto.MarcaDTO;
import com.example.demo.application.dto.UsuarioDTO;
import com.example.demo.domain.entities.Marca;
import com.example.demo.domain.entities.Usuario;

public class UsuarioUtils {

    public static UsuarioDTO makeDTOEntityUsuario(Usuario request) {
        return UsuarioDTO.builder()
                .username(request.getLogin())
                .build();
    }
}
