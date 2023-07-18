package com.example.demo.domain.repositories;

import com.example.demo.domain.entities.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    List<Carro> findAllByDisabledFalseOrderByIdDesc();

    List<Carro> findCarroByAnoAndDisabledFalse(Integer ano);

    List<Carro> findCarroByMarca_marcaIgnoreCaseAndDisabledFalse(String marca);

    List<Carro> findCarroByModeloIgnoreCaseAndDisabledFalse(String modelo);

    Optional<Carro> findByIdAndDisabledFalse(Long id);
}
