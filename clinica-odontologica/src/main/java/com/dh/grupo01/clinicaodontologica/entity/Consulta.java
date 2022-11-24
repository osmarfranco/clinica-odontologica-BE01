package com.dh.grupo01.clinicaodontologica.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String dataConsulta;

    @OneToOne
    private Dentista dentista;

    @OneToOne
    private Paciente paciente;
}
