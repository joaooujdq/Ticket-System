package com.example.ticket_system.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


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


    @Column(name = "empresa", nullable = false)
    private String empresa;


    @Column(name = "funcionario", nullable = false)
    private String funcionario;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "prioridade")
    private int prioridade;

    @Column(name = "setor", nullable = false)
    private String setor;


    @Column(name = "mensagem", nullable = false)
    private String mensagem;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "data", nullable = false)
    private String data;

    @Column(name = "hora", nullable = false)
    private String hora;

    @OneToMany(mappedBy = "recado") //deve ter o mesmo nome da variavel que é manyToOne
    private List<Empresa> empresas = new ArrayList<>();

    public Recado(Integer codigo, String empresa, String funcionario, boolean status, int prioridade, String setor, String mensagem, String telefone, String data, String hora) {
        this.codigo = codigo;
        this.empresa = empresa;
        this.funcionario = funcionario;
        this.status = status;
        this.prioridade = prioridade;
        this.setor = setor;
        this.mensagem = mensagem;
        this.telefone = telefone;
        this.data = data;
        this.hora = hora;
    }
}

