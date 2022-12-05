package com.dh.grupo01.clinicaodontologica.service.impl;

import com.dh.grupo01.clinicaodontologica.entity.dto.ConsultaDTO;
import com.dh.grupo01.clinicaodontologica.entity.dto.DentistaDTO;
import com.dh.grupo01.clinicaodontologica.entity.dto.EnderecoDTO;
import com.dh.grupo01.clinicaodontologica.entity.dto.PacienteDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
class ConsultaIMPLServiceTest {

    // Injentando as dependências do service
    @Autowired
    ConsultaIMPLService serviceConsulta;
    @Autowired
    PacienteIMPLService servicePaciente;
    @Autowired
    DentistaIMPLService serviceDentista;

    //Criando um paciente, um dentista e uma consulta para testar o buscar e o deletar
    @BeforeEach
    public void inicio(){
        //Paciente
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
        servicePaciente.salvar(pacienteDTO);

        //Dentista
        DentistaDTO dentistaDTO = new DentistaDTO();
        dentistaDTO.setCro("12365");
        dentistaDTO.setNome("Testeiro");
        dentistaDTO.setSobrenome("Pompílio de Amaral");
        serviceDentista.salvar(dentistaDTO);

        //Consulta
        ConsultaDTO consultaDTO = new ConsultaDTO();
        consultaDTO.setDataHoraConsulta(Timestamp.valueOf("2022-12-31 23:59:00"));
        consultaDTO.setDentista(dentistaDTO);
        consultaDTO.setPaciente(pacienteDTO);
        serviceConsulta.salvar(consultaDTO);

    }

    //Teste do método Salvar
    @Test
    void salvar(){

        //Criando e setando novos dados
        //Paciente
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setCpf("338.059.141-64");
        pacienteDTO.setNome("Coriolano");
        pacienteDTO.setSobrenome("Rocha");

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setLogradouro("Rua dos testes");
        enderecoDTO.setNumero(4564);
        enderecoDTO.setComplemento(null);
        enderecoDTO.setBairro("Testolândia");
        enderecoDTO.setCidade("Testópolis");
        enderecoDTO.setEstado("São Teste");
        enderecoDTO.setCep("67125-869");

        pacienteDTO.setEndereco(enderecoDTO);
        servicePaciente.salvar(pacienteDTO);

        //Dentista
        DentistaDTO dentistaDTO = new DentistaDTO();
        dentistaDTO.setCro("67890");
        dentistaDTO.setNome("Aristeu");
        dentistaDTO.setSobrenome("Cunha");
        serviceDentista.salvar(dentistaDTO);

        //Consulta
        ConsultaDTO consultaDTO = new ConsultaDTO();
        consultaDTO.setDataHoraConsulta(Timestamp.valueOf("2022-12-15 09:30:00"));
        consultaDTO.setDentista(dentistaDTO);
        consultaDTO.setPaciente(pacienteDTO);
        serviceConsulta.salvar(consultaDTO);

        //Confirmando se a consulta foi criada no banco de dados
        Assertions.assertTrue(serviceConsulta.repository.findByIdConsulta("2022-12-15 09:30:00.0338.059.141-6467890").isPresent());
    }

    //Testando o Buscar completo
    @Test
    void buscar(){
        //Buscando por todas as entradas e validando se dentro dos resultados o IdConsulta criado está presente
        Assertions.assertTrue(serviceConsulta.buscar().stream().filter(consultaDTO -> consultaDTO.getIdConsulta().equals("2022-12-31 23:59:00.0765.319.122-3512365")).findFirst().isPresent());
    }

    //Teste do método buscarPorId
    @Test
    void buscarPorId(){
        //Buscando a consulta criada para o teste e verificando se ela foi encontrada (Status code 200)
        Assertions.assertTrue(serviceConsulta.buscarPorId("2022-12-31 23:59:00.0765.319.122-3512365").getStatusCodeValue() == 200);
    }

    //Testando o método deletar
    @Test
    void deletar(){
        //Deletando a consulta criada para o teste
        serviceConsulta.deletar("2022-12-31 23:59:00.0765.319.122-3512365");
        //Verificando se a consulta foi apagada
        Assertions.assertTrue(serviceConsulta.buscarPorId("2022-12-31 23:59:00.0765.319.122-3512365").getStatusCodeValue() != 200);
    }


}
