package com.dh.grupo01.clinicaodontologica.entity;

import lombok.*;

import javax.persistence.*;

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

    @Column(nullable = false, length = 50)
    private String nome;

    private String sobrenome;
}
