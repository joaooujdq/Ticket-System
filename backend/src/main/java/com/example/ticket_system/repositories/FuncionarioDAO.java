package com.example.ticket_system.repositories;

import com.example.ticket_system.models.Funcionario;
<<<<<<< HEAD
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
=======
>>>>>>> parent of aa41400 (criando as primeiras migrations para adicionar o postgresql)
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FuncionarioDAO extends JpaRepository<Funcionario, Long> {

<<<<<<< HEAD
    Funcionario findByNome(String nome);
    Funcionario findByTelefone(String telefone);
    Funcionario findByEmail(String email);

    @Query(value = "SELECT P FROM funcionarios WHERE nome LIKE %:searchterm% ")
    Page<Funcionario> queryByNomeLike(@Param("searchTerm") String searchTerm, Pageable pageable);
=======
    Optional<Funcionario> findByNome(String nome);
    Optional<Funcionario> findByTelefone(String telefone);
    Optional<Funcionario> findByEmail(String email);
>>>>>>> parent of aa41400 (criando as primeiras migrations para adicionar o postgresql)

}
