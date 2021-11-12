package com.example.ticket_system.dtos;

import com.example.ticket_system.models.Recado;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.RepresentationModel;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import java.time.LocalDate;

@JsonPropertyOrder({"codigo_rec", "numStatus_rec", "numPrioridade_rec", "status_rec", "prioridade_rec", "setor_rec", "mensagem_rec", "data_rec"})
public class RecadoDTO extends RepresentationModel<RecadoDTO> {

    @JsonProperty("codigo_rec")
    private Integer codigo;

    @JsonProperty("numStatus_rec")
    private Integer numStatus;

    @JsonProperty("numPrioridade_rec")
    private Integer numPrioridade;

    @JsonProperty("status_rec")
    private String status;

    @JsonProperty("prioridade_rec")
    private String prioridade;

    @JsonProperty("setor_rec")
    private String setor;

    @NotNull
    @Size(max = 255)
    @Valid
    @JsonProperty("mensagem_rec")
    private String mensagem;

    @JsonProperty("data_rec")
    private LocalDate dataHora;

    @ConvertGroup(from = Default.class, to = ValidationsGroups.EmpresaId.class)
    @NotNull
    @Valid
    private EmpresaDTO empresaDTO;

    @ConvertGroup(from = Default.class, to = ValidationsGroups.FuncionarioId.class)
    @NotNull
    @Valid
    private FuncionarioDTO funcionarioDTO;

    public RecadoDTO() {
    }

    public RecadoDTO(Recado obj) {
        codigo = obj.getCodigo();
        numStatus = obj.getNumStatus();
        numPrioridade = obj.getNumPrioridade();
        status = obj.getStatus();
        prioridade = obj.getPrioridade();
        setor = obj.getSetor();
        mensagem = obj.getMensagem();
        dataHora = obj.getDataHora();
        empresaDTO = new EmpresaDTO(obj.getEmpresaObj());
        funcionarioDTO = new FuncionarioDTO(obj.getFuncionarioObj());
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getNumStatus() {
        return numStatus;
    }

    public void setNumStatus(Integer numStatus) {
        this.numStatus = numStatus;
    }

    public Integer getNumPrioridade() {
        return numPrioridade;
    }

    public void setNumPrioridade(Integer numPrioridade) {
        this.numPrioridade = numPrioridade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDate getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDate dataHora) {
        this.dataHora = dataHora;
    }

    public EmpresaDTO getEmpresaDTO() {
        return empresaDTO;
    }

    public void setEmpresaDTO(EmpresaDTO empresaDTO) {
        this.empresaDTO = empresaDTO;
    }

    public FuncionarioDTO getFuncionarioDTO() {
        return funcionarioDTO;
    }

    public void setFuncionarioDTO(FuncionarioDTO funcionarioDTO) {
        this.funcionarioDTO = funcionarioDTO;
    }
}
