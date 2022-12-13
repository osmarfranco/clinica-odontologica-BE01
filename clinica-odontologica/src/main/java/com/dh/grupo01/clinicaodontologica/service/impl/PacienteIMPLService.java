package com.dh.grupo01.clinicaodontologica.service.impl;


import com.dh.grupo01.clinicaodontologica.entity.Paciente;
import com.dh.grupo01.clinicaodontologica.entity.Perfil;
import com.dh.grupo01.clinicaodontologica.entity.dto.PacienteDTO;
import com.dh.grupo01.clinicaodontologica.exception.CadastroInvalidoException;
import com.dh.grupo01.clinicaodontologica.exception.ResourceNotFoundException;
import com.dh.grupo01.clinicaodontologica.repository.PacienteRepository;
import com.dh.grupo01.clinicaodontologica.repository.PerfilRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Autowired
    PerfilRepository perfilRepository;

    public List<PacienteDTO> buscar(){
        List<Paciente> listPaciente = repository.findAll();
        List<PacienteDTO> listPacienteDTO = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (Paciente paciente : listPaciente) {
            if(paciente.getDeletado() == 0) {
                PacienteDTO pacienteDTO = mapper.convertValue(paciente, PacienteDTO.class);
                listPacienteDTO.add(pacienteDTO);
            }
        }
        log.info("Buscando Paciente |  public List<PacienteDTO> buscar()|");
        return listPacienteDTO;
    }

    public ResponseEntity buscarPorCpf(String cpf) throws ResourceNotFoundException{
        ObjectMapper mapper = new ObjectMapper();
        Optional<Paciente> paciente = repository.findByCpf(cpf);
        Paciente paciente1 = paciente.get();
        if (paciente.isEmpty() || paciente1.getDeletado() != 0 ){
            log.info("Paciente não encontrado | public ResponseEntity buscarPorCpf(String cpf)|");
            throw  new ResourceNotFoundException("Paciente não encontrado");
        }
        log.info("teste");
        PacienteDTO pacienteDTO = mapper.convertValue(paciente1, PacienteDTO.class);
        log.info("Buscando Paciente |  public List<PacienteDTO> buscar()|" + paciente.get());
        return new ResponseEntity(pacienteDTO, HttpStatus.OK);
    }

    public ResponseEntity salvar(PacienteDTO pacienteDTO) throws CadastroInvalidoException {
        log.info("teste");
        ObjectMapper mapper = new ObjectMapper();
        Paciente paciente = mapper.convertValue(pacienteDTO, Paciente.class);
        try{
            paciente.setDataCadastro(Timestamp.from(Instant.now()));
            //Colocando por padrão como usuário ativo
            paciente.setDeletado(0);
            //Encriptando a senha antes de salvar no banco
            paciente.getUsuario().encriptarSenha();
            //Convertendo a descrição da permissão passada pelo DTO em um perfil
            List<Perfil> perfil = new ArrayList<>();
            perfil.add(perfilRepository.findByDescricao(pacienteDTO.getUsuario().getPerfil()).get());
            paciente.getUsuario().setPerfis(perfil);
            Paciente pacienteSalvo = repository.save(paciente);
            log.info("Salvando Paciente |  public ResponseEntity salvar()|" + pacienteSalvo.getNome());
            return new ResponseEntity("Paciente " + pacienteSalvo.getNome() + " criado com sucesso", HttpStatus.CREATED);

        }catch (Exception e){
            log.info("Erro ao salvar Paciente |  public ResponseEntity salvar()|");
            throw  new CadastroInvalidoException("Paciente não cadastrado");
        }
    }
    public ResponseEntity deletar(PacienteDTO pacienteDTO){
        Optional<Paciente> paciente1 = repository.findByCpf(pacienteDTO.getCpf());
        if (paciente1.isEmpty()){
            log.info("deletando |   public ResponseEntity deleta |");
            return new ResponseEntity("deletando Paciente não existe", HttpStatus.BAD_REQUEST);
        }        Paciente pacienteToUpdate = paciente1.get();
        if(pacienteDTO.getDeletado() != 0) {
            pacienteToUpdate.setDeletado(pacienteDTO.getDeletado());
            log.info(" deletando |   public ResponseEntity deleta |" + pacienteDTO.getDeletado());
        }
        repository.save(pacienteToUpdate);

        return new ResponseEntity("deletando com sucesso", HttpStatus.OK);
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
        Paciente pacienteToUpdate = paciente1.get();
        if(pacienteDTO.getNome() != null) {
            pacienteToUpdate.setNome(pacienteDTO.getNome());
        }
        if (pacienteDTO.getSobrenome() != null){
            pacienteToUpdate.setSobrenome(pacienteDTO.getSobrenome());
        }
        repository.save(pacienteToUpdate);
        log.info("Atualizando parcial Paciente| public ResponseEntity atualizarParcial |"+ pacienteDTO.getNome());
        return new ResponseEntity("Alterado com sucesso", HttpStatus.OK);
    }

}
