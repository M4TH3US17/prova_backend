package com.example.demo.application.controllers;

import com.example.demo.application.services.UsuarioService;
import com.example.demo.config.security.jwt.RespostaDeLogin;
import com.example.demo.domain.entities.dto.RespostaUsuarioDTO;
import com.example.demo.domain.entities.dto.UsuarioDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api("API Usuario")
@RestController @CrossOrigin("*")
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor @Slf4j
public class UsuarioController {

    private final UsuarioService service;

    @SneakyThrows
    @ApiOperation("Cadastra Usuário.")
    @PostMapping(value = "/salvar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespostaUsuarioDTO> save(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        log.info("UsuarioController :: Iniciando o processo de cadastramento de um novo usuario no sistema...");
        var response = service.save(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @SneakyThrows
    @ApiOperation("Autentica Usuário.")
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespostaDeLogin> login(@RequestBody UsuarioDTO usuarioDTO) {
        log.info("UsuarioController :: Iniciando o processo de autenticacao no sistema...");
        var response = service.login(usuarioDTO);
        return ResponseEntity.ok().body(response);
    }
}
