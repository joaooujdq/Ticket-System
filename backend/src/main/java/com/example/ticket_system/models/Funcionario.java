package com.example.ticket_system.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Funcionarios")
public class Funcionario implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;


    @Column(name = "nome", nullable = false)
    private String nome;


    @Column(name = "cargo")
    private String cargo;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "senha")
    private String senha;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionarioObj", orphanRemoval = true)
    private List<Recado> recados = new ArrayList<>();

    public Funcionario(Integer codigo, String nome, String cargo, String email, String telefone, String senha) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargo = cargo;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Recado> getRecados() {
        return recados;
    }

    public void setRecados(List<Recado> recados) {
        this.recados = recados;
    }
}