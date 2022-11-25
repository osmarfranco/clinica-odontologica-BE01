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

    @Autowired
    DentistaRepository repository;

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

    public ResponseEntity buscarPorCro(String cro) {
        ObjectMapper mapper = new ObjectMapper();
        Optional<Dentista> dentista = repository.findByCro(cro);
        if (dentista.isEmpty()){
            return new ResponseEntity("Dentista não encontrado", HttpStatus.BAD_REQUEST);
        }
        DentistaDTO dentistaDTO = mapper.convertValue(dentista, DentistaDTO.class);
        return new ResponseEntity(dentistaDTO,HttpStatus.OK);
    }

    public ResponseEntity salvar(Dentista dentista){

        try{
            Dentista dentistaSalvo = repository.save(dentista);
            return new ResponseEntity("Dentista " + dentistaSalvo.getNome() + " criado com sucesso", HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity("Erro ao cadastrar dentista", HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity deletar(Long id){
        Optional<Dentista> dentista = repository.findById(id);
        if (dentista.isEmpty()){
            return new ResponseEntity("Id do Dentista não existe", HttpStatus.BAD_REQUEST);
        }
        repository.deleteById(id);
        return new ResponseEntity("Excluído com sucesso", HttpStatus.OK);

    }


// PRECISA CRIAR UM NOVO FindBy para pegar os dados e alterar sem realizar o mapper pq buga. uso o mapper só
// para testar se está vazio ou não. O mapper da erro pq o Optional é da classe dentista e ele não converte dentista em dentista
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


//
//    public Dentista atualizarParcial(Dentista dentista) {
//        return dentistaDao.atualizarParcial(dentista);
//    }

}
