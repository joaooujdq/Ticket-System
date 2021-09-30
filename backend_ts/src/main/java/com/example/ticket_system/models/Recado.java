package com.example.ticket_system.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @NotBlank
    @Size(max=60)
    @Column(name = "empresa_rec", nullable = false)
    private String empresa;

    @NotBlank
    @Size(max=60)
    @Column(name = "funcionario_rec", nullable = false)
    private String funcionario;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "prioridade")
    private int prioridade;

    @Column(name = "setor", nullable = false)
    private String setor;

    @NotBlank
    @Size(max=255)
    @Column(name = "mensagem", nullable = false)
    private String mensagem;

    @Size(max=11)
    @Size(min=10)
    @Column(name = "telefone")
    private String telefone;

    @Column(name = "data", nullable = false)
    private String data;

    @Column(name = "hora", nullable = false)
    private String hora;
}

