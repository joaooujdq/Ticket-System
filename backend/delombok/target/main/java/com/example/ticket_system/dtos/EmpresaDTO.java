package com.example.ticket_system.dtos;

import com.example.ticket_system.models.Empresa;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import com.example.ticket_system.dtos.ValidationsGroups.EmpresaId;

@JsonPropertyOrder({"codigo_emp", "nome_emp", "razao_emp", "cnpj_emp", "email_emp", "endereco_emp", "telefone_emp"})
public class EmpresaDTO extends RepresentationModel<EmpresaDTO> implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull(groups = EmpresaId.class)
    @JsonProperty("codigo_emp")
    private Integer codigo;
    @NotBlank
    @Size(max = 60)
    @JsonProperty("nome_emp")
    private String nome;
    @NotBlank
    @Size(max = 60)
    @JsonProperty("razao_emp")
    private String razao;
    @NotBlank
    @JsonProperty("cnpj_emp")
    private String cnpj;
    @NotBlank
    @Size(max = 255)
    @JsonProperty("email_emp")
    private String email;
    @Size(max = 255)
    @JsonProperty("endereco_emp")
    private String endereco;
    @NotBlank
    @Size(max = 11)
    @Size(min = 10)
    @JsonProperty("telefone_emp")
    private String telefone;

    public EmpresaDTO(Empresa obj) {
        codigo = obj.getCodigo();
        nome = obj.getNome();
        razao = obj.getRazao();
        cnpj = obj.getCnpj();
        email = obj.getEmail();
        endereco = obj.getEndereco();
        telefone = obj.getTelefone();
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

    @JsonProperty("codigo_emp")
    @SuppressWarnings("all")
    public void setCodigo(final Integer codigo) {
        this.codigo = codigo;
    }

    @JsonProperty("nome_emp")
    @SuppressWarnings("all")
    public void setNome(final String nome) {
        this.nome = nome;
    }

    @JsonProperty("razao_emp")
    @SuppressWarnings("all")
    public void setRazao(final String razao) {
        this.razao = razao;
    }

    @JsonProperty("cnpj_emp")
    @SuppressWarnings("all")
    public void setCnpj(final String cnpj) {
        this.cnpj = cnpj;
    }

    @JsonProperty("email_emp")
    @SuppressWarnings("all")
    public void setEmail(final String email) {
        this.email = email;
    }

    @JsonProperty("endereco_emp")
    @SuppressWarnings("all")
    public void setEndereco(final String endereco) {
        this.endereco = endereco;
    }

    @JsonProperty("telefone_emp")
    @SuppressWarnings("all")
    public void setTelefone(final String telefone) {
        this.telefone = telefone;
    }

    @SuppressWarnings("all")
    public EmpresaDTO() {
    }

    @SuppressWarnings("all")
    public EmpresaDTO(final Integer codigo, final String nome, final String razao, final String cnpj, final String email, final String endereco, final String telefone) {
        this.codigo = codigo;
        this.nome = nome;
        this.razao = razao;
        this.cnpj = cnpj;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    @Override
    @SuppressWarnings("all")
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof EmpresaDTO)) return false;
        final EmpresaDTO other = (EmpresaDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$codigo = this.getCodigo();
        final Object other$codigo = other.getCodigo();
        if (this$codigo == null ? other$codigo != null : !this$codigo.equals(other$codigo)) return false;
        return true;
    }

    @SuppressWarnings("all")
    protected boolean canEqual(final Object other) {
        return other instanceof EmpresaDTO;
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
