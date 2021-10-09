package com.example.ticket_system.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;



@NoArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Recados")
public class Recado implements Serializable {




    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

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

        switch (strStatus){
            case "option1":
                this.status = "Pendente";

                break;

            case "option2":
                this.status = "Concluido";

                break;

            default:
                this.status = "";
                break;
        }

        switch (strPrioridade){
            case "option3":
                this.prioridade = "Alta";
                break;

            case "option4":
                this.prioridade ="Média";

                break;

            case "option5":
                this.prioridade = "Baixa";

                break;

            default:
                this.prioridade = "";
                break;
        }

        LocalDate agora = LocalDate.now();
        this.dataHora = agora;

        this.codigo = codigo;
        this.status= status;
        this.prioridade = prioridade;
        this.setor = setor;
        this.mensagem = mensagem;
        this.empresaObj = empresaObj;
        this.funcionarioObj = funcionarioObj;
    }
}

