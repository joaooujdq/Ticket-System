package com.example.ticket_system.models;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    private Recado recado;

}



