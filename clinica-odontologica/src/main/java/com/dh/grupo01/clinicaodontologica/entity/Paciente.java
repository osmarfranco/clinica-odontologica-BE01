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

    private Timestamp dataCadastro;
    private String rg;
    private String nome;
    private String sobrenome;

    @OneToOne
    private Endereco endereco;

}
