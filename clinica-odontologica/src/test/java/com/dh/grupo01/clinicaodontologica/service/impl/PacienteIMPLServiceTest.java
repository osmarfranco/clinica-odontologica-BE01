package com.dh.grupo01.clinicaodontologica.service.impl;

import com.dh.grupo01.clinicaodontologica.entity.dto.EnderecoDTO;
import com.dh.grupo01.clinicaodontologica.entity.dto.PacienteDTO;
import com.dh.grupo01.clinicaodontologica.exception.CadastroInvalidoException;
import com.dh.grupo01.clinicaodontologica.exception.ResourceNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
class PacienteIMPLServiceTest {

    // Injentando as dependências do service
    @Autowired
    PacienteIMPLService service;

    //Criando um paciente e um endereço para testar nos outros métodos
    @BeforeEach
    public void inicio() throws CadastroInvalidoException{
        log.info("Criando um paciente e um endereço para testar nos outros métodos");
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setCpf("765.319.122-35");
        pacienteDTO.setNome("Testildo");
        pacienteDTO.setSobrenome("Silva");

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setLogradouro("Rua dos testes");
        enderecoDTO.setNumero(123);
        enderecoDTO.setComplemento(null);
        enderecoDTO.setBairro("Testolândia");
        enderecoDTO.setCidade("Testópolis");
        enderecoDTO.setEstado("São Teste");
        enderecoDTO.setCep("67125-869");

        pacienteDTO.setEndereco(enderecoDTO);

        service.salvar(pacienteDTO);
    }

    //Deletando o paciente depois de cada teste
//    @AfterEach
//    public void fim() throws ResourceNotFoundException{
//        if (service.buscarPorCpf("765.319.122-35").hasBody())
//        log.info("Deletando o paciente depois de cada teste");
//        service.deletar("765.319.122-35");
//    }

    //Teste do método Salvar
    @Test
    void salvar() throws CadastroInvalidoException {
        //Criando um novo pacienteDTO para salvar no Banco
        log.info("Criando um novo pacienteDTO para salvar no Banco");
        PacienteDTO pacienteDTO = new PacienteDTO();

        //Setando os atributos do novo pacienteDTO
        pacienteDTO.setCpf("842.355.232-28");
        pacienteDTO.setNome("Testoaldo");
        pacienteDTO.setSobrenome("Pereira");

        //Setando um enderecoDTO para ser salvo em cascata com o pacienteDTO
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setLogradouro("Rua dos testes");
        enderecoDTO.setNumero(456);
        enderecoDTO.setComplemento(null);
        enderecoDTO.setBairro("Testolândia");
        enderecoDTO.setCidade("Testópolis");
        enderecoDTO.setEstado("São Teste");
        enderecoDTO.setCep("67125-869");

        pacienteDTO.setEndereco(enderecoDTO);

        //Salvando o pacienteDTO utilizando o metodo salvar do Service
        service.salvar(pacienteDTO);

        //Confirmando que o paciente foi criado dentro do Banco de dados
        Assertions.assertTrue(service.repository.findByCpf("842.355.232-28").isPresent());

    }

    //Testando se o endereço do paciente foi salvo corretamente
    @Test
    void enderecoSalvoCorretamente(){
        log.info("Testando se o endereço do paciente foi salvo corretamente");
        Assertions.assertTrue(service.buscar().stream().filter(pacienteDTO -> pacienteDTO.getEndereco().getCep().equals("67125-869")).findFirst().isPresent());
    }

    //Testando o Buscar completo
    @Test
    void buscar(){
        //Buscando por todas as entradas e validando se dentro dos resultados o CPF do paciente criado está presente
        log.info("Buscando por todas as entradas e validando se dentro dos resultados o CPF do paciente criado está presente");
        Assertions.assertTrue(service.buscar().stream().filter(pacienteDTO -> pacienteDTO.getCpf().equals("765.319.122-35")).findFirst().isPresent());
    }

    //Teste do método buscarPorCpf
    @Test
    void buscarPorCpf() throws ResourceNotFoundException{
        //Buscando o paciente criado para o teste e verificando se ele foi encontrado (Status code 200)
        log.info("Buscando o paciente criado para o teste e verificando se ele foi encontrado (Status code 200)");
        Assertions.assertTrue(service.buscarPorCpf("765.319.122-35").getStatusCodeValue() == 200);

    }

    //Testando o método deletar
//    @Test
//    void deletar() throws ResourceNotFoundException {
//        //Deletando o paciente criado para o teste
//        log.info("Deletando o paciente criado para o teste");
//        service.deletar("765.319.122-35");
//        //Verificando se o paciente foi apagado
//        Assertions.assertTrue(service.buscarPorCpf("765.319.122-35").getStatusCodeValue() != 200);
//    }

}
