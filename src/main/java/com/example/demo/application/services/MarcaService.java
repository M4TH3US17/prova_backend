package com.example.demo.application.services;

import com.example.demo.application.dto.MarcaDTO;
import com.example.demo.domain.entities.Marca;
import com.example.demo.domain.repositories.MarcaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class MarcaService {

    private final MarcaRepository repository;

    public Page<Marca> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Marca save(MarcaDTO marcaDTO) {
        Marca marca = new Marca(null, marcaDTO.getMarca());
        return repository.save(marca);
    }
}