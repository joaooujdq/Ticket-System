package com.example.ticket_system.dtos;

import com.example.ticket_system.models.Funcionario;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import com.example.ticket_system.dtos.ValidationsGroups.FuncionarioId;

public class FuncionarioDTO {
    @NotBlank
    private String nome;
<<<<<<< HEAD
=======
    @NotBlank
    @Size(max = 60)
    @JsonProperty("cargo_func")
>>>>>>> parent of aa41400 (criando as primeiras migrations para adicionar o postgresql)
    private String cargo;
    @NotBlank
    private String email;
    private String telefone;

    public FuncionarioDTO(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
