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
        log.info("Buscando Paciente |  public List<PacienteDTO> buscar()|");
        return listPacienteDTO;
    }

    public ResponseEntity buscarPorCpf(String cpf) throws ResourceNotFoundException{
        ObjectMapper mapper = new ObjectMapper();
        Optional<Paciente> paciente = repository.findByCpf(cpf);
        if (paciente.isEmpty()){
            log.info("Paciente não encontrado | public ResponseEntity buscarPorCpf(String cpf)|");
            throw  new ResourceNotFoundException("Paciente não encontrado");
        }
        log.info("teste");
        Paciente paciente1 = paciente.get();
        PacienteDTO pacienteDTO = mapper.convertValue(paciente1, PacienteDTO.class);
        log.info("Buscando Paciente |  public List<PacienteDTO> buscar()|" + paciente.get());
        return new ResponseEntity(pacienteDTO,HttpStatus.OK);
    }

    public ResponseEntity salvar(PacienteDTO pacienteDTO) throws CadastroInvalidoException {
        log.info("teste");
        ObjectMapper mapper = new ObjectMapper();
        Paciente paciente = mapper.convertValue(pacienteDTO, Paciente.class);
        try{
            paciente.setDataCadastro(Timestamp.from(Instant.now()));
            Paciente pacienteSalvo = repository.save(paciente);
            log.info("Salvando Paciente |  public ResponseEntity salvar()|" + pacienteSalvo.getNome());
            return new ResponseEntity("Paciente " + pacienteSalvo.getNome() + " criado com sucesso", HttpStatus.CREATED);

        }catch (Exception e){
            log.info("Erro ao salvar Paciente |  public ResponseEntity salvar()|");
            throw  new CadastroInvalidoException("Paciente não cadastrado");
        }
    }



    public ResponseEntity deletar(String cpf) throws ResourceNotFoundException{
        Optional<Paciente> paciente = repository.findByCpf(cpf);
        if (paciente.isEmpty()){
            log.info("Erro deletando Paciente |  public ResponseEntity deletar |");
            throw  new ResourceNotFoundException("Paciente não encontrado");
        }
        repository.deleteById(paciente.get().getId());
        log.info("Deletando Paciente |  public ResponseEntity deletar|" + paciente.get().getId());
        return new ResponseEntity("Excluído com sucesso", HttpStatus.OK);

    }

    public ResponseEntity atualizarTotal(PacienteDTO pacienteDTO) throws ResourceNotFoundException{


        Optional<Paciente> paciente1 = repository.findByCpf(pacienteDTO.getCpf());
        log.info("teste");
        if (paciente1.isEmpty()){
            log.info("Erro ao atualizar total Paciente |  public ResponseEntity atualizarTotal|");
            throw  new ResourceNotFoundException("Paciente não encontrado");
        }
        Paciente pacienteToUpdate = paciente1.get();
        pacienteToUpdate.setNome(pacienteDTO.getNome());
        pacienteToUpdate.setSobrenome(pacienteDTO.getSobrenome());
        repository.save(pacienteToUpdate);
        log.info("AtualizaNDO total Paciente |  public ResponseEntity atualizarTotal|" + paciente1.get());
        return new ResponseEntity("Alterado com sucesso", HttpStatus.OK);
    }

    public ResponseEntity atualizarParcial(PacienteDTO pacienteDTO) throws ResourceNotFoundException{
        Optional<Paciente> paciente1 = repository.findByCpf(pacienteDTO.getCpf());
        log.info("teste");
        if (paciente1.isEmpty()){
            log.info("Erro atualizar parcial Paciente| public ResponseEntity atualizarParcial |");
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
        log.info("Atualizando parcial Paciente| public ResponseEntity atualizarParcial |"+ pacienteDTO.getNome());
        return new ResponseEntity("Alterado com sucesso", HttpStatus.OK);
    }

}
