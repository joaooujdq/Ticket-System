package com.example.ticket_system.dtos;
import com.example.ticket_system.models.Funcionario;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.RepresentationModel;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.example.ticket_system.dtos.ValidationsGroups.FuncionarioId;
@JsonPropertyOrder({"codigo_func", "nome_func", "cargo_func", "email_func", "telefone_func", "senha_func"})
public class FuncionarioDTO extends RepresentationModel<FuncionarioDTO>{

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

    @JsonProperty("senha_func")
    private String senha;

    public FuncionarioDTO() {
    }

    public FuncionarioDTO(Funcionario obj) {
        this.codigo = obj.getCodigo();
        this.nome = obj.getNome();
        this.cargo = obj.getCargo();
        this.email = obj.getEmail();
        this.telefone = obj.getTelefone();
        this.senha= obj.getSenha();
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
}