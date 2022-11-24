package com.dh.grupo01.clinicaodontologica.service.impl;


import com.dh.grupo01.clinicaodontologica.entity.Consulta;
import com.dh.grupo01.clinicaodontologica.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ConsultaIMPLService {


    @Autowired
    ConsultaRepository repository;
    public List<Consulta> buscar(){
        return repository.findAll();
    }

    public ResponseEntity salvar(Consulta consulta){
        try{
            repository.save(consulta);
            return new ResponseEntity("Consulta agendada com sucesso", HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity("Erro ao agendar consulta", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity deletar(Long id){
        Optional<Consulta> consulta = repository.findById(id);
        if (consulta.isEmpty()){
            return new ResponseEntity("Id da consulta não existe", HttpStatus.BAD_REQUEST);
        }
        repository.deleteById(id);
        return new ResponseEntity("Excluido com sucesso", HttpStatus.OK);

    }

//    public ResponseEntity atualizar(Consulta consulta){
//
//        Olha aqui amanhã VVVV
//        https://stackoverflow.com/questions/39741102/how-to-beautifully-update-a-jpa-entity-in-spring-data
//    }
//
//    public Consulta atualizarParcial(Consulta consulta){
//        return consultaDao.atualizarParcial(consulta);
//    }

}
