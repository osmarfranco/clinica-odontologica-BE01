package com.dh.grupo01.clinicaodontologica.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

//Data gera os getter e setter
@Data
// AllArgsConstructor cria um construtor com todos os argumentos
@AllArgsConstructor
public class Paciente {
    //Atributos
    private String rg, nome, sobrenome;
    private Endereco endereco;
    private LocalDate dataCadastro;
}
