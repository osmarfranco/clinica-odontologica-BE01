package com.dh.grupo01.clinicaodontologica.dao;

import com.dh.grupo01.clinicaodontologica.model.Dentista;
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

    public Endereco deletar(Endereco endereco) {
        listEndereco.remove(endereco);
        return endereco;
    }

    public Endereco atualizar(Endereco endereco) {
        for (Endereco end : listEndereco) {
            if (end.getId() == endereco.getId()) {
                listEndereco.remove(end);
                listEndereco.add(endereco);
            } else {
                System.out.println("Não encontrado");
            }
        }
        return endereco;
    }

    public Endereco atualizarParcial(Endereco endereco) {
        for (Endereco end : listEndereco) {
            if (end.getId() == endereco.getId()) {
                if (endereco.getLogradouro() != null) {
                    end.setLogradouro(endereco.getLogradouro());
                }
                if (endereco.getNumero() != 0) {
                    end.setNumero(endereco.getNumero());
                }
                if (endereco.getBairro() != null) {
                    end.setBairro(endereco.getBairro());
                }
                if (endereco.getComplemento() != null) {
                    end.setComplemento(endereco.getComplemento());
                }
                if (endereco.getCidade() != null) {
                    end.setCidade(endereco.getCidade());
                }
                if (endereco.getEstado() != null) {
                    end.setEstado(endereco.getEstado());
                }
                if (endereco.getCep() != null) {
                    end.setCep(endereco.getCep());
                }
            } else {
                System.out.println("Não encontrado");
            }
        }
        return endereco;
    }

}

