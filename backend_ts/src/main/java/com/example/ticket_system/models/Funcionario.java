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
@Table(name = "Funcionarios")
public class Funcionario implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @NotBlank
    @Size(max=60)
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotBlank
    @Size(max=60)
    @Column(name = "cargo", nullable = false)
    private String cargo;

    @NotBlank
    @Size(max=255)
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max=11)
    @Size(min=10)
    @Column(name = "telefone")
    private String telefone;

}
