package com.dh.grupo01.clinicaodontologica.service.impl;

import com.dh.grupo01.clinicaodontologica.dao.DentistaDao;
import com.dh.grupo01.clinicaodontologica.model.Dentista;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistaIMPLService {


    DentistaDao dentistaDao = new DentistaDao();

    public List<Dentista> buscar(){

        return dentistaDao.buscar();
    }

    public ResponseEntity salvar(Dentista dentista){



        try{
            Dentista dentistaSalvo = dentistaDao.salvar(dentista);
            return new ResponseEntity("Dentista " + dentistaSalvo.getNome() + " criado com sucesso", HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity("Erro ao cadastrar dentista", HttpStatus.BAD_REQUEST);
        }

    }

    public Dentista deletar(Dentista dentista) {
        return dentistaDao.deletar(dentista);
    }

    public Dentista atualizar(Dentista dentista) {
        return dentistaDao.atualizar(dentista);
    }

    public Dentista atualizarParcial(Dentista dentista) {
        return dentistaDao.atualizarParcial(dentista);
    }

}
