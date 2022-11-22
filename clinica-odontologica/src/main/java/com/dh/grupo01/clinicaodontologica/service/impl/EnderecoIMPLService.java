package com.dh.grupo01.clinicaodontologica.service.impl;

import com.dh.grupo01.clinicaodontologica.dao.EnderecoDao;
import com.dh.grupo01.clinicaodontologica.dao.PacienteDao;
import com.dh.grupo01.clinicaodontologica.model.Dentista;
import com.dh.grupo01.clinicaodontologica.model.Endereco;
import com.dh.grupo01.clinicaodontologica.model.Paciente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

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
