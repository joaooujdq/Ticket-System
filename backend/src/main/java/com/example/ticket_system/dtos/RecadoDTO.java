package com.example.ticket_system.dtos;

import com.example.ticket_system.models.Recado;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.io.Serializable;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@JsonPropertyOrder({"codigo_rec", "status_rec", "prioridade_rec",
"setor_rec","mensagem_rec", "data_rec", "hora_rec"})
public class RecadoDTO extends RepresentationModel<RecadoDTO> implements Serializable {
    private static final long serialVersionUID = 1L;

    //as classes DTOS permitem a navegação de dados sem que eles acessem diretamente o banco de dados

    @EqualsAndHashCode.Include
    @JsonProperty("codigo_rec")
    private Integer codigo;

    @JsonProperty("status_rec")
    private String status;

    @JsonProperty("prioridade_rec")
    private String prioridade;

    @JsonProperty("setor_rec")
    private String setor;

    @NotBlank
    @Size(max=255)
    @JsonProperty("mensagem_rec")
    private String mensagem;

    @JsonProperty("data_rec")
    private String data;

    @JsonProperty("hora_rec")
    private String hora;

    @ConvertGroup(from = Default.class, to = ValidationsGroups.EmpresaId.class)
    @NotNull
    @Valid
    private EmpresaDTO empresaDTO;

    @ConvertGroup(from = Default.class, to = ValidationsGroups.FuncionarioId.class)
    @NotNull
    @Valid
    private FuncionarioDTO funcionarioDTO;

    public RecadoDTO(Recado obj){
        codigo = obj.getCodigo();
        status = obj.getStatus();
        prioridade = obj.getPrioridade();
        setor = obj.getSetor();
        mensagem = obj.getMensagem();
        data = obj.getData();
        hora = obj.getHora();
        empresaDTO = new EmpresaDTO(obj.getEmpresaObj());
        funcionarioDTO = new FuncionarioDTO(obj.getFuncionarioObj());
    }
}
