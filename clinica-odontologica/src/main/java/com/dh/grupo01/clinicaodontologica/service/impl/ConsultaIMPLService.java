package com.dh.grupo01.clinicaodontologica.service.impl;


import com.dh.grupo01.clinicaodontologica.entity.Consulta;
import com.dh.grupo01.clinicaodontologica.entity.Dentista;
import com.dh.grupo01.clinicaodontologica.entity.Paciente;
import com.dh.grupo01.clinicaodontologica.entity.dto.ConsultaDTO;
import com.dh.grupo01.clinicaodontologica.exception.CadastroInvalidoException;
import com.dh.grupo01.clinicaodontologica.repository.ConsultaRepository;
import com.dh.grupo01.clinicaodontologica.repository.DentistaRepository;
import com.dh.grupo01.clinicaodontologica.repository.PacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
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
        log.info("Buscando todas as consultas | public List<ConsultaDTO> buscar | ");
        return listConsultaDTO;
    }

    public ResponseEntity buscarPorId(String idConsulta){
        ObjectMapper mapper = new ObjectMapper();
        Optional<Consulta> consulta = repository.findByIdConsulta(idConsulta);
        if (consulta.isEmpty()){
            log.info("Erro ao buscar Consulta por Id| public ResponseEntity buscarPorId | ");
            return new ResponseEntity("Consulta não encontrada", HttpStatus.BAD_REQUEST);
        }
        Consulta consulta1 = consulta.get();
        ConsultaDTO consultaDTO = mapper.convertValue(consulta1, ConsultaDTO.class);
        log.info("Buscando consulta por id | public ResponseEntity buscarPorId | ");
        return new ResponseEntity(consultaDTO,HttpStatus.OK);
    }

    public ResponseEntity salvar(ConsultaDTO consultaDTO) throws CadastroInvalidoException{
        ObjectMapper mapper = new ObjectMapper();
        Consulta consulta = mapper.convertValue(consultaDTO, Consulta.class);
        Optional<Dentista> dentistaId = repositoryDent.findByCro(consultaDTO.getDentista().getCro());
        Optional<Paciente> pacienteId = repositoryPac.findByCpf(consultaDTO.getPaciente().getCpf());
        List<Consulta> listaConsultas = repository.findByDataHoraConsulta(consulta.getDataHoraConsulta());

        log.info("Validando disponibilidade de horário do dentista e paciente | public ResponseEntity salvar | ");
        //Verifica se o paciente ou o dentista já tem consulta marcada no mesmo horário
        for (Consulta c: listaConsultas) {
            // Verifica se o dentista já tem alguma consulta marcada nessa hora
            if (c.getDentista().getCro().equals(consulta.getDentista().getCro()) && c.getDataHoraConsulta().equals(consulta.getDataHoraConsulta())){
                throw  new CadastroInvalidoException("Dentista já tem consulta nessa hora");
            }
            // Verifica se o paciente já tem alguma consulta marcada nessa hora
            if (c.getPaciente().getCpf().equals(consulta.getPaciente().getCpf())   && c.getDataHoraConsulta().equals(consulta.getDataHoraConsulta())){
                throw  new CadastroInvalidoException("Paciente já tem consulta nessa hora");
            }

        }
        log.info("Validando disponibilidade de horário do dentista e paciente | public ResponseEntity salvar | Validado com sucesso ");
        
        consulta.getDentista().setId(dentistaId.get().getId());
        consulta.getPaciente().setId(pacienteId.get().getId());
        consulta.setIdConsulta(consulta.getDataHoraConsulta().toString() + consulta.getPaciente().getCpf() + consulta.getDentista().getCro());
        try{
            repository.save(consulta);
            log.info("Salvando Consulta |  public ResponseEntity salvar| ");
            return new ResponseEntity("Consulta agendada com sucesso", HttpStatus.CREATED);

        }catch (Exception e){
            log.info("Erro ao Salvar Consulta |  public ResponseEntity salvar| ");
            return new ResponseEntity("Erro ao agendar consulta", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity deletar(String idConsulta){
        Optional<Consulta> consulta = repository.findByIdConsulta(idConsulta);
        if (consulta.isEmpty()){
            log.info("Erro deletando consulta | public ResponseEntity deletar |");
            return new ResponseEntity("Id da Consulta não existe", HttpStatus.BAD_REQUEST);
        }
        repository.deleteById(consulta.get().getId());
        log.info("Deletando consulta | public ResponseEntity deletar |");
        return new ResponseEntity("Excluído com sucesso", HttpStatus.OK);

    }


}
