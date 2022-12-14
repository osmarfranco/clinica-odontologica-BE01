package com.dh.grupo01.clinicaodontologica.service.impl;

import com.dh.grupo01.clinicaodontologica.entity.dto.EnderecoDTO;
import com.dh.grupo01.clinicaodontologica.entity.dto.PacienteDTO;
import com.dh.grupo01.clinicaodontologica.entity.dto.UsuarioDTO;
import com.dh.grupo01.clinicaodontologica.exception.CadastroInvalidoException;
import com.dh.grupo01.clinicaodontologica.exception.ResourceNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
class PacienteIMPLServiceTest {

    // Injentando as dependências do service
    @Autowired
    PacienteIMPLService service;

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

        // Criando o usuário e integrando ele ao paciente
        UsuarioDTO usuario1 = new UsuarioDTO();
        usuario1.setUsername("Testoaldo");
        usuario1.setPassword("123456");
        usuario1.setPerfil("PACIENTE");
        pacienteDTO.setUsuario(usuario1);

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
        Assertions.assertTrue(service.buscar().stream().filter(pacienteDTO -> pacienteDTO.getCpf().equals("358.023.642-38")).findFirst().isPresent());
    }

    //Teste do método buscarPorCpf
    @Test
    void buscarPorCpf() throws ResourceNotFoundException{
        //Buscando o paciente criado para o teste e verificando se ele foi encontrado (Status code 200)
        log.info("Buscando o paciente criado para o teste e verificando se ele foi encontrado (Status code 200)");
        Assertions.assertTrue(service.buscarPorCpf("358.023.642-38").getStatusCodeValue() == 200);

    }

}
