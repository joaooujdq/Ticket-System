package com.example.ticket_system.models;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Empresas")
public class Empresa implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;


    @Column(name = "nome", nullable = false)
    private String nome;


    @Column(name = "razao", nullable = false)
    private String razao;


    @Column(name = "cnpj", nullable = false, length = 14)
    private String cnpj;


    @Column(name = "email", nullable = false)
    private String email;


    @Column(name = "endereco", nullable = false)
    private String endereco;


    @Column(name = "telefone")
    private String telefone;

    @OneToMany(mappedBy = "funcionarioObj")
    private List<Recado> recados = new ArrayList<>();

    public Empresa(Integer codigo, String nome, String razao, String cnpj, String email, String endereco, String telefone) {
        this.codigo = codigo;
        this.nome = nome;
        this.razao = razao;
        this.cnpj = cnpj;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
    }
}



