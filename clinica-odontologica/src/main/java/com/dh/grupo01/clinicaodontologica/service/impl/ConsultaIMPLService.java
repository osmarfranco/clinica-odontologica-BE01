package com.dh.grupo01.clinicaodontologica.service.impl;

import com.dh.grupo01.clinicaodontologica.repository.ConsultaDao;
import com.dh.grupo01.clinicaodontologica.entity.Consulta;
import com.dh.grupo01.clinicaodontologica.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ConsultaIMPLService {


    @Autowired
    ConsultaRepository repository;
    //ConsultaDao consultaDao = new ConsultaDao();
    public List<Consulta> buscar(){
        return repository.findAll();
    }

    public ResponseEntity salvar(Consulta consulta){


        try{
            Consulta consultaSalva = repository.save(consulta);
            return new ResponseEntity("Consulta agendada com sucesso", HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity("Erro ao agendar consulta", HttpStatus.BAD_REQUEST);
        }
    }

    public Consulta deletar(Consulta consulta){
        return repository.delete(consulta);
        //return consultaDao.deletar(consulta);
    }

    public Consulta atualizar(Consulta consulta){
        return consultaDao.atualizar(consulta);
    }

    public Consulta atualizarParcial(Consulta consulta){
        return consultaDao.atualizarParcial(consulta);
    }

}
