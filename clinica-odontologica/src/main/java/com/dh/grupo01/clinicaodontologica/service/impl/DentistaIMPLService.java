package com.dh.grupo01.clinicaodontologica.service.impl;

import com.dh.grupo01.clinicaodontologica.entity.dto.DentistaDTO;
import com.dh.grupo01.clinicaodontologica.entity.Dentista;
import com.dh.grupo01.clinicaodontologica.repository.DentistaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DentistaIMPLService {

    @Autowired
    DentistaRepository repository;

    public List<DentistaDTO> buscar(){
        List<Dentista> listDentista = repository.findAll();
        List<DentistaDTO> listDentistaDTO = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (Dentista dentista : listDentista) {
            DentistaDTO dentistaDTO = mapper.convertValue(dentista, DentistaDTO.class);
            listDentistaDTO.add(dentistaDTO);
        }
        return listDentistaDTO;
    }

    public ResponseEntity salvar(Dentista dentista){



        try{
            Dentista dentistaSalvo = repository.save(dentista);
            return new ResponseEntity("Dentista " + dentistaSalvo.getNome() + " criado com sucesso", HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity("Erro ao cadastrar dentista", HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity deletar(Long id){
        Optional<Dentista> dentista = repository.findById(id);
        if (dentista.isEmpty()){
            return new ResponseEntity("Id do Dentista não existe", HttpStatus.BAD_REQUEST);
        }
        repository.deleteById(id);
        return new ResponseEntity("Excluído com sucesso", HttpStatus.OK);

    }

//    public Dentista atualizar(Dentista dentista) {
//        return dentistaDao.atualizar(dentista);
//    }
//
//    public Dentista atualizarParcial(Dentista dentista) {
//        return dentistaDao.atualizarParcial(dentista);
//    }

}
