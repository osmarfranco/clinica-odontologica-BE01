package com.dh.grupo01.clinicaodontologica.model;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Endereco {

    private Long id;
    private int numero;
    private String logradouro, complemento, bairro, cidade, estado, cep;

}
