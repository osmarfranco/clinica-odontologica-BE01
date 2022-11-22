package com.dh.grupo01.clinicaodontologica.service.impl;

import com.dh.grupo01.clinicaodontologica.dao.PacienteDao;
import com.dh.grupo01.clinicaodontologica.model.Dentista;
import com.dh.grupo01.clinicaodontologica.model.Paciente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
@Service
public class PacienteIMPLService {

    PacienteDao pacienteDao = new PacienteDao();
    public List<Paciente> buscar(){
        return pacienteDao.buscar();
    }

    public ResponseEntity salvar(Paciente paciente){

        try{
            paciente.setDataCadastro(Timestamp.from(Instant.now()));
            Paciente pacienteSalvo = pacienteDao.salvar(paciente);
            return new ResponseEntity("Paciente " + pacienteSalvo.getNome() + " criado com sucesso", HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity("Erro ao cadastrar paciente", HttpStatus.BAD_REQUEST);
        }
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
