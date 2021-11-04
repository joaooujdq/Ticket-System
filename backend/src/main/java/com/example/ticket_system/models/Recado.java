package com.example.ticket_system.models;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Recados")
public class Recado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    @Column(name="numStatus")
    private Integer numStatus;
    @Column(name="numPrioridade")
    private Integer numPrioridade;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "prioridade")
    private String prioridade;
    @Column(name = "setor", nullable = false)
    private String setor;
    @Column(name = "mensagem", nullable = false)
    private String mensagem;
    @Column(name = "data", nullable = false)
    private LocalDate dataHora;
    @ManyToOne
    private Empresa empresaObj;
    @ManyToOne
    private Funcionario funcionarioObj;

    public Recado(Integer codigo, String strStatus, String strPrioridade, String setor, String mensagem, Empresa empresaObj, Funcionario funcionarioObj) {
        //Obs: não devo implementar codigo no construtor. Vou corrigir em breve.
        //<editor-fold defaultstate="collapsed" desc="delombok">
        switch (strStatus) {
        case "option1": 
            this.status = "Pendente";
            this.numStatus = 1;
            break;
        case "option2": 
            this.status = "Concluido";
            this.numStatus = 2;
            break;
        default: 
            this.status = "";
            this.numStatus = 3;
            break;
        }
        switch (strPrioridade) {
        case "option3": 
            this.prioridade = "Alta";
            this.numPrioridade = 1;
            break;
        case "option4": 
            this.prioridade = "Média";
            this.numPrioridade = 2;
            break;
        case "option5": 
            this.prioridade = "Baixa";
            this.numPrioridade = 3;
            break;
        default: 
            this.prioridade = "";
            this.numPrioridade = 4;
            break;
        }
        LocalDate agora = LocalDate.now();
        this.dataHora = agora;
        this.codigo = codigo;
        this.status = status;
        this.prioridade = prioridade;
        this.setor = setor;
        this.mensagem = mensagem;
        this.empresaObj = empresaObj;
        this.funcionarioObj = funcionarioObj;
    }
        //</editor-fold>

    @SuppressWarnings("all")
    public Recado() {
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
    public Empresa getEmpresaObj() {
        return this.empresaObj;
    }

    @SuppressWarnings("all")
    public Funcionario getFuncionarioObj() {
        return this.funcionarioObj;
    }

    @SuppressWarnings("all")
    public void setCodigo(final Integer codigo) {
        this.codigo = codigo;
    }

    @SuppressWarnings("all")
    public void setStatus(final String status) {
        this.status = status;
    }

    @SuppressWarnings("all")
    public void setPrioridade(final String prioridade) {
        this.prioridade = prioridade;
    //<editor-fold defaultstate="collapsed" desc="delombok">
    }

    @SuppressWarnings("all")
    public void setSetor(final String setor) {
    //</editor-fold>
        this.setor = setor;
    //<editor-fold defaultstate="collapsed" desc="delombok">
    }

    @SuppressWarnings("all")
    public void setMensagem(final String mensagem) {
    //</editor-fold>
        this.mensagem = mensagem;
    //<editor-fold defaultstate="collapsed" desc="delombok">
    }

    @SuppressWarnings("all")
    public void setDataHora(final LocalDate dataHora) {
        this.dataHora = dataHora;
    }

    @SuppressWarnings("all")
    public void setEmpresaObj(final Empresa empresaObj) {
    //</editor-fold>
        this.empresaObj = empresaObj;
    //<editor-fold defaultstate="collapsed" desc="delombok">
    }

    @SuppressWarnings("all")
    public void setFuncionarioObj(final Funcionario funcionarioObj) {
    //</editor-fold>
        this.funcionarioObj = funcionarioObj;
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

    //<editor-fold defaultstate="collapsed" desc="delombok">
    @Override
    @SuppressWarnings("all")
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Recado)) return false;
        final Recado other = (Recado) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$codigo = this.getCodigo();
        final Object other$codigo = other.getCodigo();
        if (this$codigo == null ? other$codigo != null : !this$codigo.equals(other$codigo)) return false;
        return true;
    }

    @SuppressWarnings("all")
    protected boolean canEqual(final Object other) {
        return other instanceof Recado;
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
}
    //</editor-fold>
