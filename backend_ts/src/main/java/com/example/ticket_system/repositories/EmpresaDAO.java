package com.example.ticket_system.repositories;

import com.example.ticket_system.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresaDAO extends JpaRepository<Empresa, Integer> {
    Optional<Empresa> findByEmail(String telefone);
    Optional<Empresa> findByCnpj(String telefone);
    Optional<Empresa> findByTelefone(String telefone);
}
