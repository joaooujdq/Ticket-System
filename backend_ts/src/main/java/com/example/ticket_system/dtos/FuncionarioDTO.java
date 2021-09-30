package com.example.ticket_system.dtos;

import com.example.ticket_system.models.Funcionario;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder()
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class FuncionarioDTO extends RepresentationModel<FuncionarioDTO> implements Serializable {
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @JsonProperty("codigo_func")
    private Integer codigo;

    @JsonProperty("nome_func")
    private String nome;

    @JsonProperty("cargo_func")
    private String cargo;

    @JsonProperty("email_func")
    private String email;

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
