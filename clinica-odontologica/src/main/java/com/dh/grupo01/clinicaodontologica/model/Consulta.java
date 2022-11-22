package com.dh.grupo01.clinicaodontologica.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Consulta {

    private Long id;
    private String dataConsulta;
    private Dentista dentista;
    private Paciente paciente;
}
