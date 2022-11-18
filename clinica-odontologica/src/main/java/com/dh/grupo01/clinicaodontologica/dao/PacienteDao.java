package com.dh.grupo01.clinicaodontologica.dao;

import com.dh.grupo01.clinicaodontologica.model.Paciente;

import java.util.ArrayList;
import java.util.List;

public class PacienteDao {

    public static List<Paciente> listPaciente = new ArrayList<>();

    public List<Paciente> buscar() {
        return listPaciente;
    }

    public Paciente salvar(Paciente paciente) {
        listPaciente.add(paciente);
        return paciente;
    }
}
