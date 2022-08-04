package com.example.demo.controllers;

import com.example.demo.entities.Carro;
import com.example.demo.entities.dto.CarroDTO;
import com.example.demo.services.CarroService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api("Carro") @RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/carros")
public class CarroController {

    @Autowired
    private CarroService service;

    @ApiOperation("Retorna uma lista paginada de carros.")
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<Carro>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(service.findAllCars(pageable));
    }
    @ApiOperation("Retorna uma lista de carros filtrada através do nome da marca.")
    @GetMapping(value="/filtro/marca/{marca}", produces = "application/json")
    public ResponseEntity<Page<Carro>> findCarsByBrand(@PathVariable("marca") String marca, Pageable pageable) {
        return ResponseEntity.ok().body(service.findCarsByBrand(marca, pageable));
    }

    @ApiOperation("Retorna uma lista de carros filtrada através do ano.")
    @GetMapping(value="/filtro/ano/{ano}", produces = "application/json")
    public ResponseEntity<Page<Carro>> findCarsByAno(@PathVariable("ano") Integer ano, Pageable pageable) {
        return ResponseEntity.ok().body(service.findCarsByYear(ano, pageable));
    }

    @ApiOperation("Busca um carro por id.")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Carro> findById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @ApiOperation("Deleta um carro por id.")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Salva um carro.")
    @PostMapping(value = "/salvar", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Carro> save(@Valid @RequestBody CarroDTO carroDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(carroDTO));
    }

    @ApiOperation("Atualiza um carro.")
    @PutMapping(value = "/atualizar/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Carro> update(@PathVariable Long id, @RequestBody CarroDTO carroDTO) throws Exception {
        return ResponseEntity.ok().body(service.update(id, carroDTO));
    }
}
