package com.example.ticket_system.models;

import com.example.ticket_system.dtos.FuncionarioDTO;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cargo;
    private String email;
    private String telefone;

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionarioObj", orphanRemoval = true)
    //private List<Recado> recados = new ArrayList<>();

    public Funcionario(){

    }

    public static Funcionario convert(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioDTO.getNome());
        funcionario.setCargo(funcionarioDTO.getCargo());
        funcionario.setEmail(funcionarioDTO.getEmail());
        funcionario.setTelefone(funcionarioDTO.getTelefone());
        return funcionario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
