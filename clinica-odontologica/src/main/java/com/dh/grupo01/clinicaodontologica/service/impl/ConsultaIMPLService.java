package com.dh.grupo01.clinicaodontologica.service.impl;


import com.dh.grupo01.clinicaodontologica.entity.Consulta;
import com.dh.grupo01.clinicaodontologica.entity.dto.ConsultaDTO;
import com.dh.grupo01.clinicaodontologica.repository.ConsultaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaIMPLService {


    @Autowired
    ConsultaRepository repository;
    public List<Consulta> buscar(){
        return repository.findAll();
    }

    public ResponseEntity salvar(ConsultaDTO consultaDTO){
        ObjectMapper mapper = new ObjectMapper();
        Consulta consulta = mapper.convertValue(consultaDTO, Consulta.class);
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
//        ResponseEntity.f
//
//    }

//    public Consulta atualizarParcial(Consulta consulta){
//        return service.atualizarParcial(consulta);
//    }

}
