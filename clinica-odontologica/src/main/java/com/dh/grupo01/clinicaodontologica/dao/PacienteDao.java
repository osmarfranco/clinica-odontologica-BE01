package com.dh.grupo01.clinicaodontologica.dao;

import com.dh.grupo01.clinicaodontologica.model.Endereco;
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

    public Paciente deletar(Paciente paciente) {
        listPaciente.remove(paciente);
        return paciente;
    }

    public Paciente atualizar(Paciente paciente) {
        for (Paciente pac : listPaciente) {
            if (pac.getId() == paciente.getId()) {
                listPaciente.remove(pac);
                listPaciente.add(paciente);
            } else {
                System.out.println("Não encontrado");
            }
        }
        return paciente;
    }

    public Paciente atualizarParcial(Paciente paciente) {
        for (Paciente pac : listPaciente) {
            if (pac.getId() == paciente.getId()) {
                if (paciente.getNome() != null){
                    pac.setNome(paciente.getNome());
                }
                if (paciente.getRg() != null){
                    pac.setRg(paciente.getRg());
                }
                if (paciente.getDataCadastro() != null){
                    pac.setDataCadastro(paciente.getDataCadastro());
                }
            } else {
                System.out.println("Não encontrado");
            }
        }
        return paciente;
    }
}
