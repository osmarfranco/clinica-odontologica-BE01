package com.dh.grupo01.clinicaodontologica.service.impl;

import com.dh.grupo01.clinicaodontologica.entity.Paciente;
import com.dh.grupo01.clinicaodontologica.entity.Perfil;
import com.dh.grupo01.clinicaodontologica.entity.dto.DentistaDTO;
import com.dh.grupo01.clinicaodontologica.entity.Dentista;
import com.dh.grupo01.clinicaodontologica.repository.DentistaRepository;
import com.dh.grupo01.clinicaodontologica.repository.PerfilRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class DentistaIMPLService {

    // Autowired é a injeção de dependência, não precisa mais instanciar nenhum objeto, o spring fará isso.
    @Autowired
    DentistaRepository repository;

    @Autowired
    PerfilRepository perfilRepository;

    // Criamos esse método utilizando um mapper. Ele busca todos os dados do banco, coloca em uma lista e depois
    // convertemos  os objetos para nosso objetoDTO que é nosso filtro do que pode ser mostrado no front.
    public List<DentistaDTO> buscar(){
        List<Dentista> listDentista = repository.findAll();
        List<DentistaDTO> listDentistaDTO = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (Dentista dentista : listDentista) {
            if(dentista.getDeletado() == 0) {
                DentistaDTO dentistaDTO = mapper.convertValue(dentista, DentistaDTO.class);
                listDentistaDTO.add(dentistaDTO);
            }
        }
        log.info("Buscando todos dentista | public List<DentistaDTO> buscar |");
        return listDentistaDTO;
    }
    // Cria o método BuscarPorCRO para encontrar o CRO já que não passamos o ID do banco para o front.
    // Criamos um Optional para validar se tem um CRO correspondente no repository caso não exista o CRO informado ele traz vazio.
    public ResponseEntity buscarPorCro(String cro) {
        ObjectMapper mapper = new ObjectMapper();
        Optional<Dentista> dentista = repository.findByCro(cro);
        Dentista dentista1 = dentista.get();
        if (dentista.isEmpty() || dentista1.getDeletado() != 0) {
            log.info("Erro ao buscar dentista por cro | public ResponseEntity buscarPorCro |");
            return new ResponseEntity("Dentista não encontrado", HttpStatus.BAD_REQUEST);
        }
            DentistaDTO dentistaDTO = mapper.convertValue(dentista1, DentistaDTO.class);
            log.info("Buscando dentista por cro | public ResponseEntity buscarPorCro |");
            return new ResponseEntity(dentistaDTO, HttpStatus.OK);


    }

    public ResponseEntity salvar(DentistaDTO dentistaDTO){
        ObjectMapper mapper = new ObjectMapper();
        Dentista dentista = mapper.convertValue(dentistaDTO, Dentista.class);
        try{
            log.info("Salvando dentista | public ResponseEntity salvar |");
            //Colocando por padrão como usuário ativo
            dentista.setDeletado(0);
            //Encriptando a senha antes de salvar no banco
            dentista.getUsuario().encriptarSenha();
            //Convertendo a descrição da permissão passada pelo DTO em um perfil
            List<Perfil> perfil = new ArrayList<>();
            perfil.add(perfilRepository.findByDescricao(dentistaDTO.getUsuario().getPerfil()).get());
            dentista.getUsuario().setPerfis(perfil);
            Dentista dentistaSalvo = repository.save(dentista);
            return new ResponseEntity("Dentista " + dentista.getNome() + " criado com sucesso", HttpStatus.CREATED);

        }catch (Exception e){
            log.info("Erro ao salvar dentista | public ResponseEntity salvar |");
            return new ResponseEntity("Erro ao cadastrar dentista", HttpStatus.BAD_REQUEST);
        }

    }

    // Cria o método deletar usando o FindById, procura o id correspondente no repository e usa o optional para
    // caso não exista o ID informado ele trazer vazio.
    public ResponseEntity deletar(DentistaDTO dentistaDTO){
        Optional<Dentista> dentista1 = repository.findByCro(dentistaDTO.getCro());
        if (dentista1.isEmpty()){
            log.info("deletando |   public ResponseEntity deleta |");
            return new ResponseEntity("deletando Dentista não existe", HttpStatus.BAD_REQUEST);
        }        Dentista dentistaToUpdate = dentista1.get();
        if(dentistaDTO.getDeletado() != 0) {
            dentistaToUpdate.setDeletado(dentistaDTO.getDeletado());
        }
        repository.save(dentistaToUpdate);
        log.info(" deletando |   public ResponseEntity deleta |" + dentistaDTO.getDeletado());
        return new ResponseEntity("deletando com sucesso", HttpStatus.OK);
    }



// PRECISA CRIAR UM NOVO FindBy para pegar os dados e alterar sem realizar o mapper como no buscar por ID.

    public ResponseEntity atualizarTotal(DentistaDTO dentistaDTO) {

        Optional<Dentista> dentista1 = repository.findByCro(dentistaDTO.getCro());

        if (dentista1.isEmpty()){
            log.info("Erro Atualizando total dentista | public ResponseEntity atualizarTotal |");
            return new ResponseEntity("CRO do Dentista não existe", HttpStatus.BAD_REQUEST);
        }
        Dentista dentistaToUpdate = dentista1.get();
        dentistaToUpdate.setNome(dentistaDTO.getNome());
        dentistaToUpdate.setSobrenome(dentistaDTO.getSobrenome());
        repository.save(dentistaToUpdate);
        log.info(" Atualizando total dentista | public ResponseEntity atualizarTotal |");
        return new ResponseEntity("Alterado com sucesso", HttpStatus.OK);



    }


    // Usamos a mesma estrutura do atualizarTotal e só colocamos uma validação de caso algum campo não venha nulo
    // atualizamos aquele(s) campo(s)
    public ResponseEntity atualizarParcial(DentistaDTO dentistaDTO) {
        Optional<Dentista> dentista1 = repository.findByCro(dentistaDTO.getCro());

        if (dentista1.isEmpty()){
            log.info("Erro Atualizando parcial dentista |  public ResponseEntity atualizarParcial |");
            return new ResponseEntity("CRO do Dentista não existe", HttpStatus.BAD_REQUEST);
        }
        Dentista dentistaToUpdate = dentista1.get();
        if(dentistaDTO.getNome() != null) {
            dentistaToUpdate.setNome(dentistaDTO.getNome());
        }
        if (dentistaDTO.getSobrenome() != null){
            dentistaToUpdate.setSobrenome(dentistaDTO.getSobrenome());
        }
        repository.save(dentistaToUpdate);
        log.info(" Atualizando total dentista |  public ResponseEntity atualizarParcial |");
        return new ResponseEntity("Alterado com sucesso", HttpStatus.OK);
    }

}
