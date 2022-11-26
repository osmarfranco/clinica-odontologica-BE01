package com.dh.grupo01.clinicaodontologica.service.impl;

import com.dh.grupo01.clinicaodontologica.entity.Paciente;
import com.dh.grupo01.clinicaodontologica.entity.dto.DentistaDTO;
import com.dh.grupo01.clinicaodontologica.entity.Dentista;
import com.dh.grupo01.clinicaodontologica.repository.DentistaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DentistaIMPLService {

    // Autowired é a injeção de dependência, não precisa mais instanciar nenhum objeto, o spring fará isso.
    @Autowired
    DentistaRepository repository;

    // Criamos esse método utilizando um mapper. Ele busca todos os dados do banco, coloca em uma lista e depois
    // convertemos  os objetos para nosso objetoDTO que é nosso filtro do que pode ser mostrado no front.
    public List<DentistaDTO> buscar(){
        List<Dentista> listDentista = repository.findAll();
        List<DentistaDTO> listDentistaDTO = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (Dentista dentista : listDentista) {
            DentistaDTO dentistaDTO = mapper.convertValue(dentista, DentistaDTO.class);
            listDentistaDTO.add(dentistaDTO);
        }
        return listDentistaDTO;
    }
    // Cria o método BuscarPorCRO para encontrar o CRO já que não passamos o ID do banco para o front.
    // Criamos um Optional para validar se tem um CRO correspondente no repository caso não exista o CRO informado ele traz vazio.
    public ResponseEntity buscarPorCro(String cro) {
        ObjectMapper mapper = new ObjectMapper();
        Optional<Dentista> dentista = repository.findByCro(cro);
        if (dentista.isEmpty()){
            return new ResponseEntity("Dentista não encontrado", HttpStatus.BAD_REQUEST);
        }
        Dentista dentista1 = repository.findByCroIs(cro);
        DentistaDTO dentistaDTO = mapper.convertValue(dentista, DentistaDTO.class);
        return new ResponseEntity(dentistaDTO,HttpStatus.OK);
    }

    public ResponseEntity salvar(Dentista dentista){

        try{
            Dentista dentistaSalvo = repository.save(dentista);
            return new ResponseEntity("Dentista " + dentista.getNome() + " criado com sucesso", HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity("Erro ao cadastrar dentista", HttpStatus.BAD_REQUEST);
        }

    }

    // Cria o método deletar usando o FindById, procura o id correspondente no repository e usa o optional para
    // caso não exista o ID informado ele trazer vazio.
    public ResponseEntity deletar(String cro){
        Optional<Dentista> dentista = repository.findByCro(cro);
        if (dentista.isEmpty()){
            return new ResponseEntity("Id do Dentista não existe", HttpStatus.BAD_REQUEST);
        }
        repository.deleteById(dentista.get().getId());
        return new ResponseEntity("Excluído com sucesso", HttpStatus.OK);

    }


// PRECISA CRIAR UM NOVO FindBy para pegar os dados e alterar sem realizar o mapper como no buscar por ID.

    public ResponseEntity atualizarTotal(Dentista dentista) {

        Optional<Dentista> dentista1 = repository.findByCro(dentista.getCro());

        if (dentista1.isEmpty()){
            return new ResponseEntity("CRO do Dentista não existe", HttpStatus.BAD_REQUEST);
        }
        Dentista dentistaToUpdate = repository.findByCroIs(dentista.getCro());
        dentistaToUpdate.setNome(dentista.getNome());
        dentistaToUpdate.setSobrenome(dentista.getSobrenome());
        repository.save(dentistaToUpdate);
        return new ResponseEntity("Alterado com sucesso", HttpStatus.OK);



    }


    // Usamos a mesma estrutura do atualizarTotal e só colocamos uma validação de caso algum campo não venha nulo
    // atualizamos aquele(s) campo(s)
    public ResponseEntity atualizarParcial(Dentista dentista) {
        Optional<Dentista> dentista1 = repository.findByCro(dentista.getCro());

        if (dentista1.isEmpty()){
            return new ResponseEntity("CRO do Dentista não existe", HttpStatus.BAD_REQUEST);
        }
        Dentista dentistaToUpdate = repository.findByCroIs(dentista.getCro());
        if(dentista.getNome() != null) {
            dentistaToUpdate.setNome(dentista.getNome());
        }
        if (dentista.getSobrenome() != null){
            dentistaToUpdate.setSobrenome(dentista.getSobrenome());
        }
        repository.save(dentistaToUpdate);
        return new ResponseEntity("Alterado com sucesso", HttpStatus.OK);
    }

}
