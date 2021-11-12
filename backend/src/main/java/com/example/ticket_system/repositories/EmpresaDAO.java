package com.example.ticket_system.repositories;

import com.example.ticket_system.models.Empresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresaDAO extends JpaRepository<Empresa, Integer> {

    Page<Empresa> findByNomeContains(String searchTerm, Pageable pageable);
    Page<Empresa> findByRazaoContains(String searchTerm, Pageable pageable);
    Page<Empresa> findByEnderecoContains(String searchTerm, Pageable pageable);

    Optional<Empresa> findByNome(String nome);
    Optional<Empresa> findByEmail(String email);
    Optional<Empresa> findByCnpj(String cnpj);
    Optional<Empresa> findByTelefone(String telefone);

}
