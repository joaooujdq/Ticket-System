package com.example.ticket_system.dtos;

import com.example.ticket_system.models.Empresa;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class EmpresaDTO extends RepresentationModel<EmpresaDTO> implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @JsonProperty("codigo_emp")
    private Integer codigo;

    @JsonProperty("nome_emp")
    private String nome;

    @JsonProperty("razao_emp")
    private String razao;

    @JsonProperty("cnpj_emp")
    private String cnpj;

    @JsonProperty("email_emp")
    private String email;

    @JsonProperty("endereco_emp")
    private String endereco;

    @JsonProperty("telefone_emp")
    private String telefone;



    public EmpresaDTO(Empresa obj){
        this.codigo = obj.getCodigo();
        this.nome = obj.getNome();
        this.razao = obj.getRazao();
        this.cnpj = obj.getCnpj();
        this.email = obj.getEmail();
        this.endereco = obj.getEndereco();
        this.telefone = obj.getTelefone();
    }
}
