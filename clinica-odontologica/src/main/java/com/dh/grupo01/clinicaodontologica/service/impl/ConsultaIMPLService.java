package com.dh.grupo01.clinicaodontologica.service.impl;


import com.dh.grupo01.clinicaodontologica.entity.Consulta;
import com.dh.grupo01.clinicaodontologica.entity.Dentista;
import com.dh.grupo01.clinicaodontologica.entity.Paciente;
import com.dh.grupo01.clinicaodontologica.entity.dto.ConsultaDTO;
import com.dh.grupo01.clinicaodontologica.repository.ConsultaRepository;
import com.dh.grupo01.clinicaodontologica.repository.DentistaRepository;
import com.dh.grupo01.clinicaodontologica.repository.PacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaIMPLService {


    @Autowired
    ConsultaRepository repository;
    @Autowired
    DentistaRepository repositoryDent;
    @Autowired
    PacienteRepository repositoryPac;
    public List<ConsultaDTO> buscar(){
        List<Consulta> listConsulta = repository.findAll();
        List<ConsultaDTO> listConsultaDTO = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (Consulta consulta : listConsulta) {
            ConsultaDTO consultaDTO = mapper.convertValue(consulta, ConsultaDTO.class);
            listConsultaDTO.add(consultaDTO);
        }
        return listConsultaDTO;
    }

    public ResponseEntity buscarPorId(String idConsulta){
        ObjectMapper mapper = new ObjectMapper();
        Optional<Consulta> consulta = repository.findByIdConsulta(idConsulta);
        if (consulta.isEmpty()){
            return new ResponseEntity("Consulta não encontrada", HttpStatus.BAD_REQUEST);
        }
        Consulta consulta1 = consulta.get();
        ConsultaDTO consultaDTO = mapper.convertValue(consulta1, ConsultaDTO.class);
        return new ResponseEntity(consultaDTO,HttpStatus.OK);
    }

    public ResponseEntity salvar(ConsultaDTO consultaDTO){
        ObjectMapper mapper = new ObjectMapper();
        Consulta consulta = mapper.convertValue(consultaDTO, Consulta.class);
        Optional<Dentista> dentistaId = repositoryDent.findByCro(consultaDTO.getDentista().getCro());
        Optional<Paciente> pacienteId = repositoryPac.findByCpf(consultaDTO.getPaciente().getCpf());
        consulta.getDentista().setId(dentistaId.get().getId());
        consulta.getPaciente().setId(pacienteId.get().getId());
        consulta.setIdConsulta(consulta.getDataHoraConsulta().toString() + consulta.getPaciente().getCpf() + consulta.getDentista().getCro());
        try{
            repository.save(consulta);
            return new ResponseEntity("Consulta agendada com sucesso", HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity("Erro ao agendar consulta", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity deletar(String idConsulta){
        Optional<Consulta> consulta = repository.findByIdConsulta(idConsulta);
        if (consulta.isEmpty()){
            return new ResponseEntity("Id da Consulta não existe", HttpStatus.BAD_REQUEST);
        }
        repository.deleteById(consulta.get().getId());
        return new ResponseEntity("Excluído com sucesso", HttpStatus.OK);

    }

//    public ResponseEntity atualizarTotal(ConsultaDTO consultaDTO) {
//
//        Optional<Consulta> consulta = repository.findByIdConsulta(consultaDTO.getIdConsulta());
//
//        if (consulta.isEmpty()){
//            return new ResponseEntity("CRO do Dentista não existe", HttpStatus.BAD_REQUEST);
//        }
//        Consulta consultaToUpdate = consulta.get();
//        consultaToUpdate.setDataHoraConsulta(consultaDTO.getDataHoraConsulta());
//        repository.save(consultaToUpdate);
//        return new ResponseEntity("Alterado com sucesso", HttpStatus.OK);
//
//
//
//    }

//    public Consulta atualizarParcial(Consulta consulta){
//        return service.atualizarParcial(consulta);
//    }

}
