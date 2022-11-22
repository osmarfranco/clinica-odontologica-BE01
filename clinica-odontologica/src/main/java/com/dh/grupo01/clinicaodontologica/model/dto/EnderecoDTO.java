package com.dh.grupo01.clinicaodontologica.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {
    private Long id;
    private int numero;
    private String logradouro, complemento, bairro, cidade, estado, cep;
}
