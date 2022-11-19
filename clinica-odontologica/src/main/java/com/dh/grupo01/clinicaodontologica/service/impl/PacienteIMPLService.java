package com.dh.grupo01.clinicaodontologica.service.impl;

import com.dh.grupo01.clinicaodontologica.dao.PacienteDao;
import com.dh.grupo01.clinicaodontologica.model.Paciente;

import java.util.List;

public class PacienteIMPLService {

    PacienteDao pacienteDao = new PacienteDao();
    public List<Paciente> buscar(){
        return pacienteDao.buscar();
    }

    public Paciente salvar(Paciente paciente){
        return pacienteDao.salvar(paciente);
    }

    public Paciente deletar(Paciente paciente){
        return pacienteDao.deletar(paciente);
    }

    public Paciente atualizar(Paciente paciente){
        return pacienteDao.atualizar(paciente);
    }

    public Paciente atualizarParcial(Paciente paciente){
        return pacienteDao.atualizarParcial(paciente);
    }

}
