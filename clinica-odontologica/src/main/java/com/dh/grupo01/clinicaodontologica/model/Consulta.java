package com.dh.grupo01.clinicaodontologica.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Consulta {

    Date dataConsulta;
    Dentista dentista;
    Paciente paciente;
}
