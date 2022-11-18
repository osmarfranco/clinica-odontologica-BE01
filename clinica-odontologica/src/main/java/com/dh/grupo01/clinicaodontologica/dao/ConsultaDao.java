package com.dh.grupo01.clinicaodontologica.dao;

import com.dh.grupo01.clinicaodontologica.model.Consulta;

import java.util.ArrayList;
import java.util.List;

public class ConsultaDao {

    public static List<Consulta> listConsulta =  new ArrayList<>();

    public List<Consulta> buscar(){
        return listConsulta;
    }

    public Consulta salvar(Consulta consulta){
        listConsulta.add(consulta);
        return consulta;
    }
}
