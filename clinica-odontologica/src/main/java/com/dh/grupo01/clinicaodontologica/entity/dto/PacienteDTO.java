package com.dh.grupo01.clinicaodontologica.entity.dto;

import com.dh.grupo01.clinicaodontologica.entity.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDTO {

    private Timestamp dataCadastro;
    private String rg;
    private String nome;
    private String sobrenome;
    private Endereco endereco;

    public LocalDate getDataCadastro() {
        return dataCadastro.toLocalDateTime().toLocalDate();
    }
}
