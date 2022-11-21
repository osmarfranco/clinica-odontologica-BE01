package com.dh.grupo01.clinicaodontologica.model;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Endereco {

    private int id, numero;
    private String logradouro, complemento, bairro, cidade, estado, cep;

}
