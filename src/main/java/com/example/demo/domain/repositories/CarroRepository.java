package com.example.demo.domain.repositories;

import com.example.demo.domain.entities.Carro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    Page<Carro> findCarroByAno(Integer ano, Pageable pageable);
    Page<Carro> findCarroByMarca_marcaIgnoreCase(String marca, Pageable pageable);
    Page<Carro> findCarroByModeloIgnoreCase(String modelo, Pageable pageable);
}
