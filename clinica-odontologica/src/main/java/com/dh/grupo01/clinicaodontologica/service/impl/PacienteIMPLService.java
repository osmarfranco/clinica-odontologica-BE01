package com.dh.grupo01.clinicaodontologica.service.impl;

import com.dh.grupo01.clinicaodontologica.entity.Dentista;
import com.dh.grupo01.clinicaodontologica.entity.dto.PacienteDTO;
import com.dh.grupo01.clinicaodontologica.entity.Paciente;
import com.dh.grupo01.clinicaodontologica.repository.PacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteIMPLService {
    @Autowired
    PacienteRepository repository;

    public List<PacienteDTO> buscar(){
        List<Paciente> listPaciente = repository.findAll();
        List<PacienteDTO> listPacienteDTO = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (Paciente paciente : listPaciente) {
            PacienteDTO pacienteDTO = mapper.convertValue(paciente, PacienteDTO.class);
            listPacienteDTO.add(pacienteDTO);
        }
        return listPacienteDTO;
    }

    public ResponseEntity salvar(Paciente paciente){

        try{
            paciente.setDataCadastro(Timestamp.from(Instant.now()));
            Paciente pacienteSalvo = repository.save(paciente);
            return new ResponseEntity("Paciente " + pacienteSalvo.getNome() + " criado com sucesso", HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity("Erro ao cadastrar paciente", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity deletar(Long id){
        Optional<Paciente> paciente = repository.findById(id);
        if (paciente.isEmpty()){
            return new ResponseEntity("Id do Paciente não existe", HttpStatus.BAD_REQUEST);
        }
        repository.deleteById(id);
        return new ResponseEntity("Excluído com sucesso", HttpStatus.OK);

    }

    public ResponseEntity alteracaoTotal(Paciente paciente){

        ObjectMapper mapper = new ObjectMapper();
        Optional<Paciente> paciente1 = repository.findByRg(paciente.getRg());

        if (paciente1.isEmpty()){
            return new ResponseEntity("RG do Paciente não existe", HttpStatus.BAD_REQUEST);
        }
        Paciente pacienteToUpdate = mapper.convertValue(paciente1, Paciente.class);
        pacienteToUpdate.setNome(paciente.getNome());
        pacienteToUpdate.setSobrenome(paciente.getSobrenome());
        repository.save(pacienteToUpdate);
        return new ResponseEntity("Alterado com sucesso", HttpStatus.OK);
    }

//    public Paciente atualizarParcial(Paciente paciente){
//        return pacienteDao.atualizarParcial(paciente);
//    }

}
