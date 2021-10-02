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
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@JsonPropertyOrder({"codigo_rec", "empresa_rec", "funcionario_rec", "status_rec", "prioridade_rec",
"setor_rec","mensagem_rec", "data_rec", "hora_rec"})
public class RecadoDTO extends RepresentationModel<RecadoDTO> implements Serializable {
    private static final long serialVersionUID = 1L;

    //as classes DTOS permitem a navegação de dados sem que eles acessem diretamente o banco de dados
    @EqualsAndHashCode.Include
    @JsonProperty("codigo_rec")
    private Integer codigo;

    @NotBlank
    @Size(max=60)
    @JsonProperty("empresa_rec")
    private String empresa;

    @NotBlank
    @Size(max=60)
    @JsonProperty("funcionario_rec")
    private String funcionario;

    @JsonProperty("status_rec")
    private boolean status;

    @JsonProperty("prioridade_rec")
    private int prioridade;

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

    @NotNull
    @Valid
    private EmpresaDTO empresaDTO;

    @NotNull
    @Valid
    private FuncionarioDTO funcionarioDTO;

    public RecadoDTO(Recado obj){
        codigo = obj.getCodigo();
        empresa = obj.getEmpresa();
        funcionario = obj.getFuncionario();
        status = obj.isStatus();
        prioridade = obj.getPrioridade();
        setor = obj.getSetor();
        mensagem = obj.getMensagem();
        data = obj.getData();
        hora = obj.getHora();
        empresaDTO = new EmpresaDTO(obj.getEmpresaObj());
        funcionarioDTO = new FuncionarioDTO(obj.getFuncionarioObj());
    }
}
