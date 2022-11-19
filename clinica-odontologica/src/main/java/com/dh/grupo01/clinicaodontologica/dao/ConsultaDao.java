package com.dh.grupo01.clinicaodontologica.dao;

import com.dh.grupo01.clinicaodontologica.model.Consulta;
import com.dh.grupo01.clinicaodontologica.model.Paciente;

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

    public Consulta deletar(Consulta consulta){
        listConsulta.remove(consulta);
        return consulta;
    }

    public Consulta atualizar(Consulta consulta) {
        for (Consulta consult : listConsulta) {
            if (consult.getId() == consulta.getId()) {
                listConsulta.remove(consult);
                listConsulta.add(consulta);
            } else {
                System.out.println("Não encontrado");
            }
        }
        return consulta;
    }

    public Consulta atualizarParcial(Consulta consulta) {
        for (Consulta consult : listConsulta) {
            if (consult.getId() == consulta.getId()) {
                if (consult.getId() == consulta.getId()) {
                    if (consulta.getDataConsulta() != null) {
                        consult.setDataConsulta(consulta.getDataConsulta());
                    }
                } else {
                    System.out.println("Não encontrado");
                }
            }
        }
        return consulta;
    }
}
