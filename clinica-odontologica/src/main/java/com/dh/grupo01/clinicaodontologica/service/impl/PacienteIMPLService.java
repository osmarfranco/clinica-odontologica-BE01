package com.dh.grupo01.clinicaodontologica.service.impl;


import com.dh.grupo01.clinicaodontologica.entity.dto.PacienteDTO;
import com.dh.grupo01.clinicaodontologica.entity.Paciente;
import com.dh.grupo01.clinicaodontologica.exception.CadastroInvalidoException;
import com.dh.grupo01.clinicaodontologica.exception.ResourceNotFoundException;
import com.dh.grupo01.clinicaodontologica.repository.PacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
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

    public ResponseEntity buscarPorCpf(String cpf) throws ResourceNotFoundException{
        ObjectMapper mapper = new ObjectMapper();
        Optional<Paciente> paciente = repository.findByCpf(cpf);
        if (paciente.isEmpty()){
            throw  new ResourceNotFoundException("Paciente não encontrado");
        }
        Paciente paciente1 = paciente.get();
        PacienteDTO pacienteDTO = mapper.convertValue(paciente1, PacienteDTO.class);
        return new ResponseEntity(pacienteDTO,HttpStatus.OK);
    }

    public ResponseEntity salvar(PacienteDTO pacienteDTO) throws CadastroInvalidoException {

        ObjectMapper mapper = new ObjectMapper();
        Paciente paciente = mapper.convertValue(pacienteDTO, Paciente.class);
        try{
            paciente.setDataCadastro(Timestamp.from(Instant.now()));
            Paciente pacienteSalvo = repository.save(paciente);
            return new ResponseEntity("Paciente " + pacienteSalvo.getNome() + " criado com sucesso", HttpStatus.CREATED);

        }catch (Exception e){
            throw  new CadastroInvalidoException("Paciente não cadastrado");
        }
    }



    public ResponseEntity deletar(String cpf) throws ResourceNotFoundException{
        Optional<Paciente> paciente = repository.findByCpf(cpf);
        if (paciente.isEmpty()){
            throw  new ResourceNotFoundException("Paciente não encontrado");
        }
        repository.deleteById(paciente.get().getId());
        return new ResponseEntity("Excluído com sucesso", HttpStatus.OK);

    }

    public ResponseEntity atualizarTotal(PacienteDTO pacienteDTO) throws ResourceNotFoundException{


        Optional<Paciente> paciente1 = repository.findByCpf(pacienteDTO.getCpf());

        if (paciente1.isEmpty()){
            throw  new ResourceNotFoundException("Paciente não encontrado");
        }
        Paciente pacienteToUpdate = paciente1.get();
        pacienteToUpdate.setNome(pacienteDTO.getNome());
        pacienteToUpdate.setSobrenome(pacienteDTO.getSobrenome());
        repository.save(pacienteToUpdate);
        return new ResponseEntity("Alterado com sucesso", HttpStatus.OK);
    }

    public ResponseEntity atualizarParcial(PacienteDTO pacienteDTO) throws ResourceNotFoundException{
        Optional<Paciente> paciente1 = repository.findByCpf(pacienteDTO.getCpf());

        if (paciente1.isEmpty()){
            throw  new ResourceNotFoundException("Paciente não encontrado");
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
