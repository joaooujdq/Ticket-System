package com.example.ticket_system.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Funcionarios")
public class Funcionario implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;


    @Column(name = "nome", nullable = false)
    private String nome;


    @Column(name = "cargo")
    private String cargo;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionarioObj", orphanRemoval = true)
    private List<Recado> recados = new ArrayList<>();

    public Funcionario(Integer codigo, String nome, String cargo, String email, String telefone) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargo = cargo;
        this.email = email;
        this.telefone = telefone;
    }
}