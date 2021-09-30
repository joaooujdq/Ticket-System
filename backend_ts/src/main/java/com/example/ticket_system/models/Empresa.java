package com.example.ticket_system.models;

import lombok.*;
import org.hibernate.validator.constraints.Length;

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
@Table(name = "Empresas")
public class Empresa implements Serializable {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @NotBlank
    @Size(max = 60)
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotBlank
    @Size(max = 60)
    @Column(name = "razao", nullable = false)
    private String razao;

    @NotBlank
    @Column(name = "cnpj", nullable = false, length = 14)
    private String cnpj;

    @NotBlank
    @Size(max = 255)
    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank
    @Size(max = 255)
    @Column(name = "endereco", nullable = false)
    private String endereco;

    @Size(max = 11)
    @Size(min = 10)
    @Column(name = "telefone")
    private String telefone;
}



