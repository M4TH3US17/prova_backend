package com.example.demo.services;

import com.example.demo.entities.Marca;
import com.example.demo.repositories.MarcaRepository;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class MarcaService {

    private final MarcaRepository repository;

    public Page<Marca> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}