package com.dh.grupo01.clinicaodontologica.service.impl;

import com.dh.grupo01.clinicaodontologica.dao.EnderecoDao;
import com.dh.grupo01.clinicaodontologica.dao.PacienteDao;
import com.dh.grupo01.clinicaodontologica.model.Endereco;
import com.dh.grupo01.clinicaodontologica.model.Paciente;

import java.util.List;

public class EnderecoIMPLService {

    EnderecoDao enderecoDao = new EnderecoDao();
    public List<Endereco> buscar(){
        return enderecoDao.buscar();
    }

    public Endereco salvar(Endereco endereco){
        return enderecoDao.salvar(endereco);
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
