package com.example.demo.application.services;

import com.example.demo.config.security.UserDetailsServiceImpl;
import com.example.demo.config.security.jwt.JWTService;
import com.example.demo.config.security.jwt.RespostaDeLogin;
import com.example.demo.domain.entities.Usuario;
import com.example.demo.domain.entities.dto.RespostaUsuarioDTO;
import com.example.demo.domain.entities.dto.UsuarioDTO;
import com.example.demo.domain.entities.enums.RoleUsuario;
import com.example.demo.domain.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service @RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final BCryptPasswordEncoder  passwordEncoder;
    private final UserDetailsServiceImpl userDetailsImpl;
    private final JWTService Jwt;

    public RespostaUsuarioDTO save(UsuarioDTO usuarioDTO) {
        String senhaCriptografada = passwordEncoder.encode(usuarioDTO.getSenha());
        Usuario usuario = new Usuario(null, usuarioDTO.getLogin(), senhaCriptografada, RoleUsuario.ROLE_USUARIO);
        return new RespostaUsuarioDTO(repository.save(usuario));
    }

    public RespostaDeLogin login(UsuarioDTO usuarioDTO) {
        UserDetails usuario   = userDetailsImpl.loadUserByUsername(usuarioDTO.getLogin());
        boolean senhaEhValida = passwordEncoder.matches(usuarioDTO.getSenha(), usuario.getPassword());

        try{
            if(senhaEhValida){
                Usuario usuarioDoBanco = repository.findUsuarioByLogin(usuario.getUsername());
                return new RespostaDeLogin(Jwt.generationToken(usuarioDoBanco));
            }
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login ou Senha Inválidos!");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha Inválida!");
    }
}
