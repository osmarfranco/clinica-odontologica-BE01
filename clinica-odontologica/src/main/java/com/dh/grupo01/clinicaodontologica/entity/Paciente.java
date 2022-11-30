package com.dh.grupo01.clinicaodontologica.entity;

import java.sql.Timestamp;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true)
    private String cpf;

    private Timestamp dataCadastro;

    private String nome;

    private String sobrenome;

    @OneToOne (cascade = CascadeType.ALL)
    private Endereco endereco;

}
