package com.example.demo.domain.repositories;


import com.example.demo.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findUsuarioByLogin(String login);
}