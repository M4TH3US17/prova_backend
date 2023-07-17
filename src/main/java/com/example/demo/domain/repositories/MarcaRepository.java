package com.example.demo.domain.repositories;


import com.example.demo.domain.entities.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
}
