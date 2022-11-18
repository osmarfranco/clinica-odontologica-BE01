package com.dh.grupo01.clinicaodontologica.dao;

import com.dh.grupo01.clinicaodontologica.model.Dentista;

import java.util.ArrayList;
import java.util.List;

public class DentistaDao {

    public static List<Dentista> listDentista = new ArrayList<>();

    public List<Dentista> buscar() { return listDentista; }

    public Dentista salvar(Dentista dentista){
        listDentista.add(dentista);
        return dentista;
    }
}
