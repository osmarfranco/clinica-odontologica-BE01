package com.dh.grupo01.clinicaodontologica.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Dentista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true)
    private String cro; //cro é a matrícula do dentista, assim como a do médico é o crm


    private String nome;

    private String sobrenome;

    private Integer deletado;
}
