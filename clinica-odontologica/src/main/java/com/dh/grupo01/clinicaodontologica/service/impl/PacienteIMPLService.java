package com.dh.grupo01.clinicaodontologica.service.impl;


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

    public ResponseEntity buscarPorCpf(String cpf) {
        ObjectMapper mapper = new ObjectMapper();
        Optional<Paciente> paciente = repository.findByCpf(cpf);
        if (paciente.isEmpty()){
            return new ResponseEntity("Paciente não encontrado", HttpStatus.BAD_REQUEST);
        }
        Paciente paciente1 = paciente.get();
        PacienteDTO pacienteDTO = mapper.convertValue(paciente1, PacienteDTO.class);
        return new ResponseEntity(pacienteDTO,HttpStatus.OK);
    }

    public ResponseEntity salvar(PacienteDTO pacienteDTO){

        ObjectMapper mapper = new ObjectMapper();
        Paciente paciente = mapper.convertValue(pacienteDTO, Paciente.class);
        try{
            paciente.setDataCadastro(Timestamp.from(Instant.now()));
            Paciente pacienteSalvo = repository.save(paciente);
            return new ResponseEntity("Paciente " + pacienteSalvo.getNome() + " criado com sucesso", HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity("Erro ao cadastrar paciente", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity deletar(String cpf){
        Optional<Paciente> paciente = repository.findByCpf(cpf);
        if (paciente.isEmpty()){
            return new ResponseEntity("CPF do Paciente não existe", HttpStatus.BAD_REQUEST);
        }
        repository.deleteById(paciente.get().getId());
        return new ResponseEntity("Excluído com sucesso", HttpStatus.OK);

    }

    public ResponseEntity atualizarTotal(PacienteDTO pacienteDTO){


        Optional<Paciente> paciente1 = repository.findByCpf(pacienteDTO.getCpf());

        if (paciente1.isEmpty()){
            return new ResponseEntity("CPF do Paciente não existe", HttpStatus.BAD_REQUEST);
        }
        Paciente pacienteToUpdate = paciente1.get();
        pacienteToUpdate.setNome(pacienteDTO.getNome());
        pacienteToUpdate.setSobrenome(pacienteDTO.getSobrenome());
        repository.save(pacienteToUpdate);
        return new ResponseEntity("Alterado com sucesso", HttpStatus.OK);
    }

    public ResponseEntity atualizarParcial(PacienteDTO pacienteDTO){
        Optional<Paciente> paciente1 = repository.findByCpf(pacienteDTO.getCpf());

        if (paciente1.isEmpty()){
            return new ResponseEntity("CPF do Paciente não existe", HttpStatus.BAD_REQUEST);
        }
        Paciente dentistaToUpdate = paciente1.get();
        if(pacienteDTO.getNome() != null) {
            dentistaToUpdate.setNome(pacienteDTO.getNome());
        }
        if (pacienteDTO.getSobrenome() != null){
            dentistaToUpdate.setSobrenome(pacienteDTO.getSobrenome());
        }
        repository.save(dentistaToUpdate);
        return new ResponseEntity("Alterado com sucesso", HttpStatus.OK);
    }

}
