package com.dh.grupo01.clinicaodontologica.model;

import java.time.LocalDate;
import java.util.Date;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Paciente {

    private int id;
    private LocalDate dataCadastro;
    private String rg, nome, sobrenome;
    private Endereco endereco;

}
