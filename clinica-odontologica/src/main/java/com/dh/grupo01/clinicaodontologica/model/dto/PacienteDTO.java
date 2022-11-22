package com.dh.grupo01.clinicaodontologica.model.dto;

import com.dh.grupo01.clinicaodontologica.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDTO {
    private Long id;
    private String dataCadastro;
    private String rg, nome, sobrenome;
    private Endereco endereco;
}
