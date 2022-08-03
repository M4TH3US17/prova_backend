package com.example.demo.repositories;

import com.example.demo.entities.Carro;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    Page<Carro> findCarroByAno(Integer ano, Pageable pageable);
    Page<Carro> findCarroByMarca_marcaIgnoreCase(String marca, Pageable pageable);
}
