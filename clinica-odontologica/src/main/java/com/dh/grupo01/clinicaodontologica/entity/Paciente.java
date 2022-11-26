package com.dh.grupo01.clinicaodontologica.entity;

import java.sql.Timestamp;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String rg;

    private Timestamp dataCadastro;


    private String nome;

    private String sobrenome;

    @OneToOne
    private Endereco endereco;

}
