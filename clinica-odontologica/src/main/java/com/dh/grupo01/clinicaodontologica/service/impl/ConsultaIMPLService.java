package com.dh.grupo01.clinicaodontologica.service.impl;

import com.dh.grupo01.clinicaodontologica.dao.ConsultaDao;
import com.dh.grupo01.clinicaodontologica.model.Consulta;


import java.util.List;

public class ConsultaIMPLService {

    ConsultaDao consultaDao = new ConsultaDao();
    public List<Consulta> buscar(){
        return consultaDao.buscar();
    }

    public Consulta salvar(Consulta consulta){
        return consultaDao.salvar(consulta);
    }

}
