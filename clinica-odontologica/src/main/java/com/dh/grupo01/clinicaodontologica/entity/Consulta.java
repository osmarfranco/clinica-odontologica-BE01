package com.dh.grupo01.clinicaodontologica.entity;

import lombok.*;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Future;
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

    @NotBlank
    @Column(nullable = false, unique = true)
    private String idConsulta;

    @Future
    @Column(nullable = false)
    private Timestamp dataHoraConsulta;

    @OneToOne
    private Dentista dentista;

    @OneToOne
    private Paciente paciente;
}
