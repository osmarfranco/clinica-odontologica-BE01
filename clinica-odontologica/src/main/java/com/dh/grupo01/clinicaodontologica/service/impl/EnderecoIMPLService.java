package com.dh.grupo01.clinicaodontologica.service.impl;

import com.dh.grupo01.clinicaodontologica.entity.Paciente;
import com.dh.grupo01.clinicaodontologica.entity.dto.EnderecoDTO;
import com.dh.grupo01.clinicaodontologica.entity.Endereco;
import com.dh.grupo01.clinicaodontologica.exception.CadastroInvalidoException;
import com.dh.grupo01.clinicaodontologica.exception.ResourceNotFoundException;
import com.dh.grupo01.clinicaodontologica.repository.EnderecoRepository;
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
        log.info("Buscando endereço |  public List<EnderecoDTO> buscar |");
        return listEnderecoDTO;
    }

    public ResponseEntity salvar(EnderecoDTO enderecoDTO) throws CadastroInvalidoException {

        ObjectMapper mapper = new ObjectMapper();
        Endereco endereco = mapper.convertValue(enderecoDTO, Endereco.class);
        log.info("teste");
        try{
            Endereco enderecoSalvo = repository.save(endereco);
            log.info("Salvando endereço  |  public ResponseEntity salvar |"+ enderecoSalvo.getLogradouro());
            return new ResponseEntity("Endereço " + enderecoSalvo.getLogradouro() + " criado com sucesso", HttpStatus.CREATED);

        }catch (Exception e){
            log.info("Erro ao cadastrar endereço |  public ResponseEntity salvar |");
            throw new CadastroInvalidoException("Erro ao cadastrar endereço");
        }
    }

    public ResponseEntity deletar(Long id)  throws ResourceNotFoundException{
        Optional<Endereco> endereco = repository.findById(id);
        if (endereco.isEmpty()){
            log.info("Erro ao deletar endereço | public ResponseEntity deletar |");
            throw new ResourceNotFoundException("Id do Endeço não existe");
        }
        repository.deleteById(id);
        log.info("Deletando endereço | public ResponseEntity deletar |");
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
