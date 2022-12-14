package com.dh.grupo01.clinicaodontologica.entity.dto;

import com.dh.grupo01.clinicaodontologica.entity.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDTO {

    private Timestamp dataCadastro;
    @NotBlank
    @CPF
    private String cpf;
    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;
    //Endereço não precisa de @NotBlank pois é chave estrangeira, ao menos é oq achamos...
    private EnderecoDTO endereco;


    private UsuarioDTO usuario;


    private Integer deletado;
}
