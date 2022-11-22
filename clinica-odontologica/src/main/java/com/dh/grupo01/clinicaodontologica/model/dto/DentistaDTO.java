package com.dh.grupo01.clinicaodontologica.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DentistaDTO {
    private Long id;
    private String cro, nome, sobrenome;
}
