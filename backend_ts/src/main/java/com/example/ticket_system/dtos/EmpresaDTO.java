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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@JsonPropertyOrder({"codigo_emp", "nome_emp", "razao_emp", "cnpj_emp",
"email_emp", "endereco_emp", "telefone_emp"})
public class EmpresaDTO extends RepresentationModel<EmpresaDTO> implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
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



    public EmpresaDTO(Empresa obj){
        codigo = obj.getCodigo();
        nome = obj.getNome();
        razao = obj.getRazao();
        cnpj = obj.getCnpj();
        email = obj.getEmail();
        endereco = obj.getEndereco();
        telefone = obj.getTelefone();

    }
}
