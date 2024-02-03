package com.example.demo.application.services;

import com.example.demo.application.dto.UsuarioDTO;
import com.example.demo.config.security.UserDetailsServiceImpl;
import com.example.demo.config.security.jwt.JWTService;
import com.example.demo.config.security.jwt.RespostaDeLogin;
import com.example.demo.domain.entities.Usuario;
import com.example.demo.domain.entities.enums.RoleUsuario;
import com.example.demo.domain.repositories.UsuarioRepository;
import com.example.demo.infrastructure.request.usuarios.AutenticateRequest;
import com.example.demo.infrastructure.request.usuarios.RegisterUsuarioRequest;
import com.example.demo.infrastructure.response.usuarios.UsuarioResponse;
import com.example.demo.utils.usuario.UsuarioUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service @RequiredArgsConstructor @Slf4j
public class UsuarioService {

    private final UsuarioRepository repository;
    private final BCryptPasswordEncoder  passwordEncoder;
    private final UserDetailsServiceImpl userDetailsImpl;
    private final JWTService Jwt;

    public UsuarioResponse save(RegisterUsuarioRequest request) {
        try {
            log.info("UsuarioService :: Criptogranfado senha...");
            String senhaCriptografada = passwordEncoder.encode(request.senha());
            log.info("UsuarioService :: Salvando novo usuario...");
            Usuario usuario = new Usuario(null, request.username(), senhaCriptografada, RoleUsuario.ROLE_USUARIO);
            UsuarioDTO usuarioDTO = UsuarioUtils.makeDTOEntityUsuario(repository.save(usuario));
            log.info("UsuarioService :: Usuario salvo com sucesso!");

            return UsuarioResponse.builder()
                    .code(HttpStatus.OK.value())
                    .message("Usuario salvo com sucesso!")
                    .data(usuarioDTO)
                    .build();
        } catch(Exception error) {
            log.error("ERROR: " + error);
            return UsuarioResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Houve um erro interno no servidor!")
                    .build();
        }
        }

    public RespostaDeLogin login(AutenticateRequest request) {
        UserDetails usuario   = userDetailsImpl.loadUserByUsername(request.getUsername());
        System.out.println(request.getSenha() + " " + usuario.getPassword());
        boolean senhaEhValida = passwordEncoder.matches(request.getSenha(), usuario.getPassword());

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
