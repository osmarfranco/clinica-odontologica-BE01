package com.dh.grupo01.clinicaodontologica.service.impl;

import com.dh.grupo01.clinicaodontologica.dao.ConsultaDao;
import com.dh.grupo01.clinicaodontologica.model.Consulta;
import com.dh.grupo01.clinicaodontologica.model.Paciente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;

public class ConsultaIMPLService {

    ConsultaDao consultaDao = new ConsultaDao();
    public List<Consulta> buscar(){
        return consultaDao.buscar();
    }

    public ResponseEntity salvar(Consulta consulta){


        try{
            Consulta consultaSalva = consultaDao.salvar(consulta);
            return new ResponseEntity("Consulta agendada com sucesso", HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity("Erro ao agendar consulta", HttpStatus.BAD_REQUEST);
        }
    }

    public Consulta deletar(Consulta consulta){
        return consultaDao.deletar(consulta);
    }

    public Consulta atualizar(Consulta consulta){
        return consultaDao.atualizar(consulta);
    }

    public Consulta atualizarParcial(Consulta consulta){
        return consultaDao.atualizarParcial(consulta);
    }

}
