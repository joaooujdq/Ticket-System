package com.example.ticket_system.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Empresas")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "razao", nullable = false)
    private String razao;

    @Column(name = "cnpj", nullable = false, length = 14)
    private String cnpj;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "endereco", nullable = false)
    private String endereco;

    @Column(name = "telefone")
    private String telefone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresaObj", orphanRemoval = true)
    private List<Recado> recados = new ArrayList<>();

    public Empresa(){

    }

    public Empresa(Integer codigo, String nome, String razao, String cnpj, String email, String endereco, String telefone) {
        this.codigo = codigo;
        this.nome = nome;
        this.razao = razao;
        this.cnpj = cnpj;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
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

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Recado> getRecados() {
        return recados;
    }

    public void setRecados(List<Recado> recados) {
        this.recados = recados;
    }
}

