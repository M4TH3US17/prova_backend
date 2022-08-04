package com.example.demo.controllers;

import com.example.demo.entities.dto.*;
import com.example.demo.security.jwt.RespostaDeLogin;
import com.example.demo.services.UsuarioService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Api("API Usuario")
@RestController @CrossOrigin("*")
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @ApiOperation("Cadastra Usuário.")
    @PostMapping(value = "/salvar", consumes = "application/json", produces = "application/json")
    public ResponseEntity<RespostaUsuarioDTO> save(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(usuarioDTO));
    }

    @ApiOperation("Autentica Usuário.")
    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<RespostaDeLogin> login(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok().body(service.login(usuarioDTO));
    }
}
