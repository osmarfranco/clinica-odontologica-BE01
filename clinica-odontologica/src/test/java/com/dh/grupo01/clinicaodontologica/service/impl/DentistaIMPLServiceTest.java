package com.dh.grupo01.clinicaodontologica.service.impl;

import com.dh.grupo01.clinicaodontologica.entity.dto.DentistaDTO;
import com.dh.grupo01.clinicaodontologica.entity.dto.UsuarioDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
class DentistaIMPLServiceTest {

    // Injentando as dependências do service
    @Autowired
    DentistaIMPLService service;

    //Teste do método Salvar
    @Test
    void salvar(){
        log.info("Criando um novo dentistaDTO para salvar no Banco");
        //Criando um novo dentistaDTO para salvar no Banco
        DentistaDTO dentistaDTO = new DentistaDTO();
        //Setando os atributos do novo dentista DTO
        dentistaDTO.setCro("12345");
        dentistaDTO.setNome("Dr DTO");
        dentistaDTO.setSobrenome("Teste");
        UsuarioDTO usuario1 = new UsuarioDTO();
        usuario1.setUsername("Dr DTO");
        usuario1.setPassword("123456");
        usuario1.setPerfil("DENTISTA");
        dentistaDTO.setUsuario(usuario1);

        //Salvando o dentistaDTO utilizando o metodo salvar do Service
        service.salvar(dentistaDTO);

        //Confirmando que o dentista foi criado dentro do Banco de dados
        Assertions.assertTrue(service.repository.findByCro("12345").isPresent());

    }

    //Testando o Buscar completo
    @Test
    void buscar(){
        log.info("Buscando por todas as entradas e validando se dentro dos resultados o cro do dentista criado está presente");
        //Buscando por todas as entradas e validando se dentro dos resultados o cro do dentista criado está presente
        Assertions.assertTrue(service.buscar().stream().filter(dentistaDTO -> dentistaDTO.getCro().equals("456987")).findFirst().isPresent());
    }

    //Teste do método buscarPorCro
    @Test
    void buscarPorCro(){
        log.info("Buscando o dentista criado para o teste e verificando se ele foi encontrado (Status code 200)");
        //Buscando o dentista criado para o teste e verificando se ele foi encontrado (Status code 200)
        Assertions.assertTrue(service.buscarPorCro("456987").getStatusCodeValue() == 200);

    }


}