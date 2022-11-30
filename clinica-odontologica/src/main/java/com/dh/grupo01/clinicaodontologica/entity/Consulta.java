package com.dh.grupo01.clinicaodontologica.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idConsulta;

    @Column(nullable = false)
    private Timestamp dataHoraConsulta;

    @OneToOne
    private Dentista dentista;

    @OneToOne
    private Paciente paciente;
}
