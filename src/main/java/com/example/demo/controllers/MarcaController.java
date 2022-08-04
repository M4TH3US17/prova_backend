package com.example.demo.controllers;

import com.example.demo.entities.Marca;
import com.example.demo.entities.dto.MarcaDTO;
import com.example.demo.services.MarcaService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api("Marca") @RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/marcas")
public class MarcaController {

    @Autowired
    private MarcaService service;

    @ApiOperation("Retorna uma lista paginada de marcas.")
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<Marca>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

    @ApiOperation("Salva uma nova marca.")
    @PostMapping(value = "/salvar", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Marca> save(@Valid @RequestBody MarcaDTO marcaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(marcaDTO));
    }
}
