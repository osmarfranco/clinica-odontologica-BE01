package com.dh.grupo01.clinicaodontologica.model.dto;

import com.dh.grupo01.clinicaodontologica.model.Dentista;
import com.dh.grupo01.clinicaodontologica.model.Paciente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaDTO {
    private Long id;
    private String dataConsulta;
    private Dentista dentista;
    private Paciente paciente;
}
