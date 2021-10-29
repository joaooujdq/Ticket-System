package com.example.ticket_system.models;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Empresas")
public class Empresa implements Serializable {
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

    public Empresa(Integer codigo, String nome, String razao, String cnpj, String email, String endereco, String telefone) {
        this.codigo = codigo;
        this.nome = nome;
        this.razao = razao;
        this.cnpj = cnpj;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    //<editor-fold defaultstate="collapsed" desc="delombok">
    @SuppressWarnings("all")
    public Empresa() {
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="delombok">
    @SuppressWarnings("all")
    public Integer getCodigo() {
        return this.id;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="delombok">
    @SuppressWarnings("all")
    public String getNome() {
        return this.nome;
    }

    @SuppressWarnings("all")
    public String getRazao() {
        return this.razao;
    }

    @SuppressWarnings("all")
    public String getCnpj() {
        return this.cnpj;
    }

    @SuppressWarnings("all")
    public String getEmail() {
        return this.email;
    }

    @SuppressWarnings("all")
    public String getEndereco() {
        return this.endereco;
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
        this.id = codigo;
    }

    @SuppressWarnings("all")
    public void setNome(final String nome) {
        this.nome = nome;
    }

    @SuppressWarnings("all")
    public void setRazao(final String razao) {
        this.razao = razao;
    }

    @SuppressWarnings("all")
    public void setCnpj(final String cnpj) {
        this.cnpj = cnpj;
    }

    @SuppressWarnings("all")
    public void setEmail(final String email) {
        this.email = email;
    }

    @SuppressWarnings("all")
    public void setEndereco(final String endereco) {
        this.endereco = endereco;
    }

    @SuppressWarnings("all")
    public void setTelefone(final String telefone) {
        this.telefone = telefone;
    }

    @SuppressWarnings("all")
    public void setRecados(final List<Recado> recados) {
        this.recados = recados;
    }

    @Override
    @SuppressWarnings("all")
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Empresa)) return false;
        final Empresa other = (Empresa) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$codigo = this.getCodigo();
        final Object other$codigo = other.getCodigo();
        if (this$codigo == null ? other$codigo != null : !this$codigo.equals(other$codigo)) return false;
        return true;
    }

    @SuppressWarnings("all")
    protected boolean canEqual(final Object other) {
        return other instanceof Empresa;
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
    //</editor-fold>
