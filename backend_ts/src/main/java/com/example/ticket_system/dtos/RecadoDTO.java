package com.example.ticket_system.dtos;

import com.example.ticket_system.models.Recado;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)

public class RecadoDTO extends RepresentationModel<RecadoDTO> implements Serializable {
    private static final long serialVersionUID = 1L;

    //as classes DTOS permitem a navegação de dados sem que eles acessem diretamente o banco de dados
    @EqualsAndHashCode.Include
    @JsonProperty("codigo_rec")
    private Integer codigo;

    @JsonProperty("empresa_rec")
    private String empresa;

    @JsonProperty("funcionario_rec")
    private String funcionario;

    @JsonProperty("status_rec")
    private boolean status;

    @JsonProperty("prioridade_rec")
    private int prioridade;

    @JsonProperty("setor_rec")
    private String setor;

    @JsonProperty("mensagem_rec")
    private String mensagem;

    @JsonProperty("telefone_rec")
    private String telefone;

    @JsonProperty("data_rec")
    private String data;

    @JsonProperty("hora_rec")
    private String hora;

    public RecadoDTO(Recado obj){
        this.codigo = obj.getCodigo();
        this.empresa = obj.getEmpresa();
        this.funcionario = obj.getFuncionario();
        this.status = obj.isStatus();
        this.prioridade = obj.getPrioridade();
        this.setor = obj.getSetor();
        this.mensagem = obj.getMensagem();
        this.telefone = obj.getTelefone();
        this.data = obj.getData();
        this.hora = obj.getHora();
    }
}
