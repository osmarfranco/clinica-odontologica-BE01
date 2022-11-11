package com.dh.grupo01.clinicaodontologica.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Paciente {

    String nome, sobrenome, rg;
    Date dataCadastro;
    Endereco endereco;



}
