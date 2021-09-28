package com.example.ticket_system.repositories;

import com.example.ticket_system.models.Recado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecadoDAO extends JpaRepository<Recado, Integer> {

    public Optional<Recado> findByNameFunc(String nome);
    public Optional<Recado> findByTelefone(String telefone);
}


