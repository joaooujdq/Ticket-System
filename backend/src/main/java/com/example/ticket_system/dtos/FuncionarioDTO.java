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


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"codigo_func", "nome_func", "cargo_func", "email_func", "telefone_func"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class FuncionarioDTO extends RepresentationModel<FuncionarioDTO> implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(groups = FuncionarioId.class)
    @EqualsAndHashCode.Include
    @JsonProperty("codigo_func")
    private Integer codigo;

    @NotBlank
    @Size(max=60)
    @JsonProperty("nome_func")
    private String nome;

    @NotBlank
    @Size(max=60)
    @JsonProperty("cargo_func")
    private String cargo;

    @NotBlank
    @Size(max=255)
    @JsonProperty("email_func")
    private String email;

    @Size(max=11)
    @Size(min=10)
    @JsonProperty("telefone_func")
    private String telefone;



    public FuncionarioDTO(Funcionario obj){
        this.codigo = obj.getCodigo();
        this.nome = obj.getNome();
        this.cargo = obj.getCargo();
        this.email = obj.getEmail();
        this.telefone = obj.getTelefone();
    }
}
