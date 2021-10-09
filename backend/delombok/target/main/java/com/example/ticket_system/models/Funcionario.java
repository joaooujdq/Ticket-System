package com.example.ticket_system.models;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Funcionarios")
public class Funcionario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "cargo", nullable = false)
    private String cargo;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "telefone")
    private String telefone;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionarioObj", orphanRemoval = true)
    private List<Recado> recados = new ArrayList<>();

    public Funcionario(Integer codigo, String nome, String cargo, String email, String telefone) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargo = cargo;
        this.email = email;
        this.telefone = telefone;
    }

    @SuppressWarnings("all")
    public Integer getCodigo() {
        return this.codigo;
    }

    @SuppressWarnings("all")
    public String getNome() {
        return this.nome;
    }

    @SuppressWarnings("all")
    public String getCargo() {
        return this.cargo;
    }

    @SuppressWarnings("all")
    public String getEmail() {
        return this.email;
    }

    @SuppressWarnings("all")
    public String getTelefone() {
        return this.telefone;
    }

    @SuppressWarnings("all")
    public List<Recado> getRecados() {
        return this.recados;
    }

    @SuppressWarnings("all")
    public void setCodigo(final Integer codigo) {
        this.codigo = codigo;
    }

    @SuppressWarnings("all")
    public void setNome(final String nome) {
        this.nome = nome;
    }

    @SuppressWarnings("all")
    public void setCargo(final String cargo) {
        this.cargo = cargo;
    }

    @SuppressWarnings("all")
    public void setEmail(final String email) {
        this.email = email;
    }

    @SuppressWarnings("all")
    public void setTelefone(final String telefone) {
        this.telefone = telefone;
    }

    @SuppressWarnings("all")
    public void setRecados(final List<Recado> recados) {
        this.recados = recados;
    }

    @SuppressWarnings("all")
    public Funcionario() {
    }

    @Override
    @SuppressWarnings("all")
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Funcionario)) return false;
        final Funcionario other = (Funcionario) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$codigo = this.getCodigo();
        final Object other$codigo = other.getCodigo();
        if (this$codigo == null ? other$codigo != null : !this$codigo.equals(other$codigo)) return false;
        return true;
    }

    @SuppressWarnings("all")
    protected boolean canEqual(final Object other) {
        return other instanceof Funcionario;
    }

    @Override
    @SuppressWarnings("all")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $codigo = this.getCodigo();
        result = result * PRIME + ($codigo == null ? 43 : $codigo.hashCode());
        return result;
    }
}
