package com.example.demo.application.controllers;

import com.example.demo.application.dto.MarcaDTO;
import com.example.demo.application.services.MarcaService;
import com.example.demo.infrastructure.request.marcas.RegisterMarcaRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api("Marca") @RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/marcas")
@RequiredArgsConstructor @Slf4j
public class MarcaController {

    private final MarcaService service;

    @SneakyThrows
    @ApiOperation("Retorna uma lista paginada de marcas.")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll(Pageable pageable) {
        log.info("MarcaController :: Iniciando o processo de obtençao de todos os modelos cadastrados no sistema...");
        var response = service.findAll();
        return ResponseEntity.ok().body(response);
    }

    @SneakyThrows
    @ApiOperation("Salva uma nova marca.")
    @PostMapping(value = "/salvar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@Valid @RequestBody RegisterMarcaRequest request) {
        log.info("MarcaController :: Iniciando o processo de salvamento de uma nova marca...");
        var response = service.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
