package com.dh.grupo01.clinicaodontologica.service.impl;

import com.dh.grupo01.clinicaodontologica.dao.DentistaDao;
import com.dh.grupo01.clinicaodontologica.model.Dentista;

import java.util.List;

public class DentistaIMPLService {


    DentistaDao dentistaDao = new DentistaDao();

    public List<Dentista> buscar(){

        return dentistaDao.buscar();
    }

    public Dentista salvar(Dentista dentista){

        return dentistaDao.salvar(dentista);

    }

}
