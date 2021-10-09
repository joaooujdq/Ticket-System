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

@JsonPropertyOrder({"codigo_func", "nome_func", "cargo_func", "email_func", "telefone_func"})
public class FuncionarioDTO extends RepresentationModel<FuncionarioDTO> implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull(groups = FuncionarioId.class)
    @JsonProperty("codigo_func")
    private Integer codigo;
    @NotBlank
    @Size(max = 60)
    @JsonProperty("nome_func")
    private String nome;
    @NotBlank
    @Size(max = 60)
    @JsonProperty("cargo_func")
    private String cargo;
    @NotBlank
    @Size(max = 255)
    @JsonProperty("email_func")
    private String email;
    @Size(max = 11)
    @Size(min = 10)
    @JsonProperty("telefone_func")
    private String telefone;

    public FuncionarioDTO(Funcionario obj) {
        this.codigo = obj.getCodigo();
        this.nome = obj.getNome();
        this.cargo = obj.getCargo();
        this.email = obj.getEmail();
        this.telefone = obj.getTelefone();
    }

    //<editor-fold defaultstate="collapsed" desc="delombok">
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

    @JsonProperty("codigo_func")
    @SuppressWarnings("all")
    public void setCodigo(final Integer codigo) {
        this.codigo = codigo;
    }

    @JsonProperty("nome_func")
    @SuppressWarnings("all")
    public void setNome(final String nome) {
        this.nome = nome;
    }

    @JsonProperty("cargo_func")
    @SuppressWarnings("all")
    public void setCargo(final String cargo) {
        this.cargo = cargo;
    }

    @JsonProperty("email_func")
    @SuppressWarnings("all")
    public void setEmail(final String email) {
        this.email = email;
    }

    @JsonProperty("telefone_func")
    @SuppressWarnings("all")
    public void setTelefone(final String telefone) {
        this.telefone = telefone;
    }

    @SuppressWarnings("all")
    public FuncionarioDTO() {
    }

    @SuppressWarnings("all")
    public FuncionarioDTO(final Integer codigo, final String nome, final String cargo, final String email, final String telefone) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargo = cargo;
        this.email = email;
        this.telefone = telefone;
    }

    @Override
    @SuppressWarnings("all")
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof FuncionarioDTO)) return false;
        final FuncionarioDTO other = (FuncionarioDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$codigo = this.getCodigo();
        final Object other$codigo = other.getCodigo();
        if (this$codigo == null ? other$codigo != null : !this$codigo.equals(other$codigo)) return false;
        return true;
    }

    @SuppressWarnings("all")
    protected boolean canEqual(final Object other) {
        return other instanceof FuncionarioDTO;
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
    //</editor-fold>
}
