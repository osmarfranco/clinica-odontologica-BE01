package com.dh.grupo01.clinicaodontologica.service.impl;

import com.dh.grupo01.clinicaodontologica.entity.Perfil;
import com.dh.grupo01.clinicaodontologica.entity.dto.PerfilDTO;
import com.dh.grupo01.clinicaodontologica.exception.CadastroInvalidoException;
import com.dh.grupo01.clinicaodontologica.exception.ResourceNotFoundException;
import com.dh.grupo01.clinicaodontologica.repository.PerfilRepository;
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
public class PerfilIMPLService {

    @Autowired
    PerfilRepository repository;

    //Como é uma atribuição exclusivamente do ADMIN, não precisa de DTO
    public List<PerfilDTO> buscar(){
        List<Perfil> listPerfil = repository.findAll();
        List<PerfilDTO> listPerfilDTO = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (Perfil perfil : listPerfil) {
            PerfilDTO perfilDTO = mapper.convertValue(perfil, PerfilDTO.class);
            listPerfilDTO.add(perfilDTO);
        }
        log.info("Buscando Perfis |  public List<PerfilDTO> buscar()|");
        return listPerfilDTO;
    }

    public ResponseEntity salvar(PerfilDTO perfilDTO) throws CadastroInvalidoException {
        ObjectMapper mapper = new ObjectMapper();
        Perfil perfil = mapper.convertValue(perfilDTO, Perfil.class);
        try{
            Perfil perfilSalvo = repository.save(perfil);
            log.info("Salvando Perfil |  public ResponseEntity salvar()|" + perfilSalvo.getDescricao());
            return new ResponseEntity("Perfil " + perfilSalvo.getDescricao() + " criado com sucesso", HttpStatus.CREATED);

        }catch (Exception e){
            log.info("Erro ao salvar Perfil |  public ResponseEntity salvar()|");
            throw  new CadastroInvalidoException("Perfil não cadastrado");
        }
    }

    public ResponseEntity deletar(String descricao) throws ResourceNotFoundException {
        Optional<Perfil> perfil = repository.findByDescricao(descricao);
        if (perfil.isEmpty()){
            log.info("Erro deletando Perfil |  public ResponseEntity deletar |");
            throw  new ResourceNotFoundException("Perfil não encontrado");
        }
        repository.deleteById(perfil.get().getId());
        log.info("Deletando Perfil |  public ResponseEntity deletar|" + perfil.get().getId());
        return new ResponseEntity("Excluído com sucesso", HttpStatus.OK);

    }


}
