package com.example.demo.domain.repositories;

import com.example.demo.domain.entities.Carro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    List<Carro> findCarroByAno(Integer ano);
    List<Carro> findCarroByMarca_marcaIgnoreCase(String marca);
    List<Carro> findCarroByModeloIgnoreCase(String modelo);
}
