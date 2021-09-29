package com.example.ticket_system.dtos;

import com.example.ticket_system.models.Recado;
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
    private Integer codigo;

    private String empresa;

    private String funcionario;

    private boolean status;

    private int prioridade;

    private String setor;

    private String mensagem;

    private String telefone;

    private String data;

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
