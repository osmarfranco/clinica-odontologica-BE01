package com.dh.grupo01.clinicaodontologica.model;
import lombok.AllArgsConstructor;
import lombok.Data;

//Data gera os getter e setter
@Data
// AllArgsConstructor cria um construtor com todos os argumentos
@AllArgsConstructor
public class Dentista {
    //Atributos
    private String nome, sobrenome;
    private int idDentista;
}
