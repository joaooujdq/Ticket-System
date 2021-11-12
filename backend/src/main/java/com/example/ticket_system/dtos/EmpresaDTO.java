package com.example.ticket_system.dtos;

import com.example.ticket_system.models.Empresa;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.RepresentationModel;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.example.ticket_system.dtos.ValidationsGroups.EmpresaId;

@JsonPropertyOrder({"codigo_emp", "nome_emp", "razao_emp", "cnpj_emp", "email_emp", "endereco_emp", "telefone_emp"})
public class EmpresaDTO extends RepresentationModel<EmpresaDTO>  {

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

    public EmpresaDTO(){

    }

    public EmpresaDTO(Empresa obj) {
        codigo = obj.getCodigo();
        nome = obj.getNome();
        razao = obj.getRazao();
        cnpj = obj.getCnpj();
        email = obj.getEmail();
        endereco = obj.getEndereco();
        telefone = obj.getTelefone();
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
}
