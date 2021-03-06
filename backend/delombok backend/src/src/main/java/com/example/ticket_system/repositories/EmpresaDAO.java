package com.example.ticket_system.repositories;

import com.example.ticket_system.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmpresaDAO extends JpaRepository<Empresa, Integer> {

    Optional<Empresa> findByNome(String nome);
    Optional<Empresa> findByEmail(String email);
    Optional<Empresa> findByCnpj(String cnpj);
    Optional<Empresa> findByTelefone(String telefone);

}
