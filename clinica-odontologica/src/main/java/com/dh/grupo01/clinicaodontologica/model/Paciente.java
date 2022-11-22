package com.dh.grupo01.clinicaodontologica.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Paciente {

    private Long id;
    private Timestamp dataCadastro;
    private String rg, nome, sobrenome;
    private Endereco endereco;

}
