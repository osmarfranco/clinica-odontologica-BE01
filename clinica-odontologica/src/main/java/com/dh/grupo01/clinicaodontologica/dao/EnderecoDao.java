package com.dh.grupo01.clinicaodontologica.dao;

import com.dh.grupo01.clinicaodontologica.model.Endereco;

import java.util.ArrayList;
import java.util.List;

public class EnderecoDao {

    public static List<Endereco> listEndereco = new ArrayList<>();

    public List<Endereco> buscar() {
        return listEndereco;
    }

    public Endereco salvar(Endereco endereco) {
        listEndereco.add(endereco);
        return endereco;
    }

}
