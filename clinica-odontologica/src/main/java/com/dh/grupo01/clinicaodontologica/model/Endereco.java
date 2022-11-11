package com.dh.grupo01.clinicaodontologica.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Endereco {

    String logradouro, bairro, cidade, estado, complemento;
    int id, numero;

}
