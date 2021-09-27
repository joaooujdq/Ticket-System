package com.example.ticket_system.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Recados")
public class Recado implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(name = "nome_emp", nullable = false)
    private String empresa;

    @Column(name = "nome_func", nullable = false)
    private String funcionario;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "prioridade")
    private int prioridade;

    @Column(name = "setor", nullable = false)
    private String setor;

    @Column(name = "data", nullable = false)
    private String data;

    @Column(name = "hora", nullable = false)
    private String hora;
}

