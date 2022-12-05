package com.dh.grupo01.clinicaodontologica.service.impl;

import com.dh.grupo01.clinicaodontologica.entity.Paciente;
import com.dh.grupo01.clinicaodontologica.entity.dto.EnderecoDTO;
import com.dh.grupo01.clinicaodontologica.entity.Endereco;
import com.dh.grupo01.clinicaodontologica.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoIMPLService {

    @Autowired
    EnderecoRepository repository;


    public List<EnderecoDTO> buscar(){
        List<Endereco> listEndereco = repository.findAll();
        List<EnderecoDTO> listEnderecoDTO = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (Endereco endereco : listEndereco) {
            EnderecoDTO enderecoDTO = mapper.convertValue(endereco, EnderecoDTO.class);
            listEnderecoDTO.add(enderecoDTO);
        }
        return listEnderecoDTO;
    }

    public ResponseEntity salvar(EnderecoDTO enderecoDTO){

        ObjectMapper mapper = new ObjectMapper();
        Endereco endereco = mapper.convertValue(enderecoDTO, Endereco.class);

        try{
            Endereco enderecoSalvo = repository.save(endereco);
            return new ResponseEntity("Endereço " + enderecoSalvo.getLogradouro() + " criado com sucesso", HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity("Erro ao cadastrar endereço", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity deletar(Long id){
        Optional<Endereco> endereco = repository.findById(id);
        if (endereco.isEmpty()){
            return new ResponseEntity("Id do Endeço não existe", HttpStatus.BAD_REQUEST);
        }
        repository.deleteById(id);
        return new ResponseEntity("Excluído com sucesso", HttpStatus.OK);

    }

//    public Endereco atualizar(Endereco endereco){
//        return enderecoDao.atualizar(endereco);
//    }
//
//    public Endereco atualizarParcial(Endereco endereco){
//        return enderecoDao.atualizarParcial(endereco);
//    }
}
