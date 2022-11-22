package com.dh.grupo01.clinicaodontologica.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Dentista {

    private Long id;
    private String cro, nome, sobrenome; //cro é a matrícula do dentista, assim como a do médico é o crm
}
