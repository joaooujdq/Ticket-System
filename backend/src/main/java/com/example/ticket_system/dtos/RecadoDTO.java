package com.example.ticket_system.dtos;

import com.example.ticket_system.models.Recado;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.time.LocalDate;

@JsonPropertyOrder({"codigo_rec", "numStatus_rec", "numPrioridade_rec", "status_rec", "prioridade_rec", "setor_rec", "mensagem_rec", "data_rec"})
public class RecadoDTO extends RepresentationModel<RecadoDTO> implements Serializable {
    private static final long serialVersionUID = 1L;
    //as classes DTOS permitem a navegação de dados sem que eles acessem diretamente o banco de dados
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

    //<editor-fold defaultstate="collapsed" desc="delombok">
    @SuppressWarnings("all")
    public RecadoDTO() {
    }

    @SuppressWarnings("all")
    public RecadoDTO(final Integer codigo, final String status, final String prioridade, final String setor, final String mensagem, final LocalDate dataHora, final EmpresaDTO empresaDTO, final FuncionarioDTO funcionarioDTO) {
        this.codigo = codigo;
        this.status = status;
        this.prioridade = prioridade;
        this.setor = setor;
        this.mensagem = mensagem;
        this.dataHora = dataHora;
        this.empresaDTO = empresaDTO;
        this.funcionarioDTO = funcionarioDTO;
    }

    @SuppressWarnings("all")
    public Integer getCodigo() {
        return this.codigo;
    }

    @SuppressWarnings("all")
    public String getStatus() {
        return this.status;
    }

    @SuppressWarnings("all")
    public String getPrioridade() {
        return this.prioridade;
    }

    @SuppressWarnings("all")
    public String getSetor() {
        return this.setor;
    }

    @SuppressWarnings("all")
    public String getMensagem() {
        return this.mensagem;
    }

    @SuppressWarnings("all")
    public LocalDate getDataHora() {
        return this.dataHora;
    }

    @SuppressWarnings("all")
    public EmpresaDTO getEmpresaDTO() {
        return this.empresaDTO;
    }

    @SuppressWarnings("all")
    public FuncionarioDTO getFuncionarioDTO() {
        return this.funcionarioDTO;
    }

    @JsonProperty("codigo_rec")
    @SuppressWarnings("all")
    public void setCodigo(final Integer codigo) {
        this.codigo = codigo;
    }

    @JsonProperty("status_rec")
    @SuppressWarnings("all")
    public void setStatus(final String status) {
        this.status = status;
    }

    @JsonProperty("prioridade_rec")
    @SuppressWarnings("all")
    public void setPrioridade(final String prioridade) {
        this.prioridade = prioridade;
    }

    @JsonProperty("setor_rec")
    @SuppressWarnings("all")
    public void setSetor(final String setor) {
        this.setor = setor;
    }

    @JsonProperty("mensagem_rec")
    @SuppressWarnings("all")
    public void setMensagem(final String mensagem) {
        this.mensagem = mensagem;
    }

    @JsonProperty("data_rec")
    @SuppressWarnings("all")
    public void setDataHora(final LocalDate dataHora) {
        this.dataHora = dataHora;
    }

    @SuppressWarnings("all")
    public void setEmpresaDTO(final EmpresaDTO empresaDTO) {
        this.empresaDTO = empresaDTO;
    }

    @SuppressWarnings("all")
    public void setFuncionarioDTO(final FuncionarioDTO funcionarioDTO) {
        this.funcionarioDTO = funcionarioDTO;
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

    @Override
    @SuppressWarnings("all")
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof RecadoDTO)) return false;
        final RecadoDTO other = (RecadoDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$codigo = this.getCodigo();
        final Object other$codigo = other.getCodigo();
        if (this$codigo == null ? other$codigo != null : !this$codigo.equals(other$codigo)) return false;
        return true;
    }

    @SuppressWarnings("all")
    protected boolean canEqual(final Object other) {
        return other instanceof RecadoDTO;
    }

    @Override
    @SuppressWarnings("all")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $codigo = this.getCodigo();
        result = result * PRIME + ($codigo == null ? 43 : $codigo.hashCode());
        return result;
    }
    //</editor-fold>
}
