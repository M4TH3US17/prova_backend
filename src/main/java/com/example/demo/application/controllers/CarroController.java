package com.example.demo.application.controllers;

import com.example.demo.application.services.CarroService;
import com.example.demo.infrastructure.request.carros.*;
import io.swagger.annotations.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api("Carro") @RestController
@CrossOrigin("*") @Slf4j
@RequestMapping("/api/v1/carros")
public class CarroController {

    @Autowired
    private CarroService service;

    @SneakyThrows
    @ApiOperation("Retorna uma lista paginada de carros.")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll(Pageable pageable) {
        log.info("CarroController :: Iniciando o processo de obtençao de todos os carros cadastrados no sistema...");
        return ResponseEntity.ok().body(service.findAllCars(pageable));
    }
    @SneakyThrows
    @ApiOperation("Retorna uma lista de carros filtrada através do nome da marca.")
    @GetMapping(value="/filtro/marca/{marca}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findCarsByBrand(@PathVariable("marca") String marca, Pageable pageable) {
        log.info("CarroController :: Iniciando o processo de busca de carros filtrados por marca", marca);
        return ResponseEntity.ok().body(service.findCarsByBrand(marca, pageable));
    }

    @SneakyThrows
    @ApiOperation("Retorna uma lista de carros filtrada através do ano.")
    @GetMapping(value="/filtro/ano/{ano}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findCarsByAno(@PathVariable("ano") Integer ano, Pageable pageable) {
        log.info("CarroController :: Iniciando o processo de busca de carros filtrados por ano", ano);
        return ResponseEntity.ok().body(service.findCarsByYear(ano, pageable));
    }

    @SneakyThrows
    @ApiOperation("Retorna uma lista de carros filtrada através do modelo.")
    @GetMapping(value="/filtro/modelo/{modelo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findCarsByModel(@PathVariable("modelo") String modelo, Pageable pageable) {
        log.info("CarroController :: Iniciando o processo de busca de carros filtrados por modelo", modelo);
        var response = service.findCarroByModelo(modelo, pageable);
        return ResponseEntity.ok().body(response);
    }

    @SneakyThrows
    @ApiOperation("Busca um carro por id.")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable Long id) throws Exception {
        log.info("CarroController :: Iniciando o processo de busca de carro por ID {}", id);
        var response = service.findById(id);
        return ResponseEntity.ok().body(response);
    }

    @SneakyThrows
    @ApiOperation("Deleta um carro por id.")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        log.info("CarroController :: Iniciando o processo de desativacao do carro de ID {}", id);
        var response = service.delete(id);
        return ResponseEntity.ok().body(response);
    }

    @SneakyThrows
    @ApiOperation("Salva um carro.")
    @PostMapping(value = "/salvar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@Valid @RequestBody RegisterCarroRequest request) throws Exception {
        log.info("CarroController :: Iniciando o processo de cadastro de um novo carro...");
        var response = service.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @SneakyThrows
    @ApiOperation("Atualiza um carro.")
    @PutMapping(value = "/atualizar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UpdateCarroRequest request) throws Exception {
        log.info("CarroController :: Iniciando o processo de ediçao do carro de ID {}", id);
        var response = service.update(id, request);
        return ResponseEntity.ok().body(response);
    }
}
