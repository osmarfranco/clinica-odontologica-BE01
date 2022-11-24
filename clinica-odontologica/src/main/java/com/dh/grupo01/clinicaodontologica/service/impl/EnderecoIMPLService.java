package com.dh.grupo01.clinicaodontologica.service.impl;

import com.dh.grupo01.clinicaodontologica.repository.EnderecoDao;
import com.dh.grupo01.clinicaodontologica.entity.Endereco;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoIMPLService {

    EnderecoDao enderecoDao = new EnderecoDao();
    public List<Endereco> buscar(){
        return enderecoDao.buscar();
    }

    public ResponseEntity salvar(Endereco endereco){

        try{
            Endereco enderecoSalvo = enderecoDao.salvar(endereco);
            return new ResponseEntity("Endereço " + enderecoSalvo.getLogradouro() + " criado com sucesso", HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity("Erro ao cadastrar endereço", HttpStatus.BAD_REQUEST);
        }
    }

    public Endereco deletar(Endereco endereco){
        return enderecoDao.deletar(endereco);
    }

    public Endereco atualizar(Endereco endereco){
        return enderecoDao.atualizar(endereco);
    }

    public Endereco atualizarParcial(Endereco endereco){
        return enderecoDao.atualizarParcial(endereco);
    }
}
