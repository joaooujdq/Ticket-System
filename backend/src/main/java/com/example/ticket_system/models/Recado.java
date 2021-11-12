package com.example.ticket_system.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Recados")
public class Recado {

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

    public Recado(){

    }

    public Recado(Integer codigo, String strStatus, String strPrioridade, String setor, String mensagem, Empresa empresaObj, Funcionario funcionarioObj) {
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

    public Empresa getEmpresaObj() {
        return empresaObj;
    }

    public void setEmpresaObj(Empresa empresaObj) {
        this.empresaObj = empresaObj;
    }

    public Funcionario getFuncionarioObj() {
        return funcionarioObj;
    }

    public void setFuncionarioObj(Funcionario funcionarioObj) {
        this.funcionarioObj = funcionarioObj;
    }
}

