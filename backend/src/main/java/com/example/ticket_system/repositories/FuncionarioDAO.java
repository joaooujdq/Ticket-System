package com.example.ticket_system.repositories;

import com.example.ticket_system.models.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FuncionarioDAO extends JpaRepository<Funcionario, Integer> {

    Optional<Funcionario> findByNome(String nome);
    Optional<Funcionario> findByTelefone(String telefone);
    Optional<Funcionario> findByEmail(String email);
     Page<Funcionario> queryByNomeLike(String name);

}
