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

    public Dentista deletar(Dentista dentista) {
        listDentista.remove(dentista);
        return dentista;
    }

    public Dentista atualizar(Dentista dentista) {
        for (Dentista dent : listDentista) {
            if (dent.getId() == dentista.getId()) {
                listDentista.remove(dent);
                listDentista.add(dentista);
            } else {
                System.out.println("Não encontrado");
            }
        }
        return dentista;
    }

    public Dentista atualizarParcial(Dentista dentista) {
        for (Dentista dent : listDentista) {
            if (dent.getId() == dentista.getId()) {
                if (dentista.getCro() != null){
                    dent.setCro(dentista.getCro());
                }
                if (dentista.getNome() != null){
                    dent.setNome(dentista.getNome());
                }
                if (dentista.getSobrenome() != null){
                    dent.setSobrenome(dentista.getSobrenome());
                }
            } else {
                System.out.println("Não encontrado");
            }
        }
        return dentista;
    }
}
