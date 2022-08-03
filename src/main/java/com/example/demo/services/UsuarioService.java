package com.example.demo.services;

import com.example.demo.entities.Usuario;
import com.example.demo.entities.dto.*;
import com.example.demo.entities.enums.RoleUsuario;
import com.example.demo.repositories.UsuarioRepository;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    public RespostaUsuarioDTO save(UsuarioDTO usuarioDTO) {
        String senhaCriptografada = passwordEncoder.encode(usuarioDTO.getSenha());
        Usuario usuario = new Usuario(null, usuarioDTO.getLogin(), senhaCriptografada, RoleUsuario.ROLE_USUARIO);
        return new RespostaUsuarioDTO(repository.save(usuario));
    }
}
