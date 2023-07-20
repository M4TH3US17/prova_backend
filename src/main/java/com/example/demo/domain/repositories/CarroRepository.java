package com.example.demo.domain.repositories;

import com.example.demo.domain.entities.Carro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    Page<Carro> findAllByDisabledFalse(Pageable pageable);

    Page<Carro> findCarroByAnoAndDisabledFalse(Integer ano, Pageable pageable);

    Page<Carro> findCarroByMarca_marcaIgnoreCaseAndDisabledFalse(String marca, Pageable pageable);

    Page<Carro> findCarroByModeloIgnoreCaseAndDisabledFalse(String modelo, Pageable pageable);

    Optional<Carro> findByIdAndDisabledFalse(Long id);
}
