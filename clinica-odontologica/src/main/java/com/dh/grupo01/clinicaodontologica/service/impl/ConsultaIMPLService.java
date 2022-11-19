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

    public Consulta deletar(Consulta consulta){
        return consultaDao.deletar(consulta);
    }

    public Consulta atualizar(Consulta consulta){
        return consultaDao.atualizar(consulta);
    }

    public Consulta atualizarParcial(Consulta consulta){
        return consultaDao.atualizarParcial(consulta);
    }

}
