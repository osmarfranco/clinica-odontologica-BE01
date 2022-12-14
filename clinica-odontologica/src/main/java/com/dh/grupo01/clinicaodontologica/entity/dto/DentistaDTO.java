package com.dh.grupo01.clinicaodontologica.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DentistaDTO {

    @NotBlank
    private String cro;
    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;

    private UsuarioDTO usuario;

    private Integer deletado;
}
