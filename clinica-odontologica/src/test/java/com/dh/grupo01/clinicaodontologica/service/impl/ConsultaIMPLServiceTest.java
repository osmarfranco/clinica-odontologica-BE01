package com.dh.grupo01.clinicaodontologica.service.impl;

import com.dh.grupo01.clinicaodontologica.entity.dto.*;
import com.dh.grupo01.clinicaodontologica.exception.CadastroInvalidoException;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
@Log4j2
class ConsultaIMPLServiceTest {

    // Injentando as dependências do service
    @Autowired
    ConsultaIMPLService serviceConsulta;
    @Autowired
    PacienteIMPLService servicePaciente;
    @Autowired
    DentistaIMPLService serviceDentista;


    //Teste do método Salvar
    @Test
    void salvar() throws CadastroInvalidoException{
        log.info("Teste do método Salvar");
        //Criando e setando novos dados
        DentistaDTO dentista1 = new DentistaDTO();
        dentista1.setNome("Mustafá");
        dentista1.setSobrenome("Haddad");
        dentista1.setCro("789488");
        UsuarioDTO usuario2 = new UsuarioDTO();
        usuario2.setUsername("Mustafá");
        usuario2.setPassword("123456");
        usuario2.setPerfil("DENTISTA");
        dentista1.setUsuario(usuario2);
        serviceDentista.salvar(dentista1);

        PacienteDTO paciente = new PacienteDTO();
        paciente.setNome("Lucas2");
        paciente.setSobrenome("Teste");
        paciente.setCpf("778.311.869-05");
        EnderecoDTO enderecoDTO1 = new EnderecoDTO();
        enderecoDTO1.setLogradouro("Rua dos testes2");
        enderecoDTO1.setNumero(123);
        enderecoDTO1.setComplemento(null);
        enderecoDTO1.setBairro("Testolândia");
        enderecoDTO1.setCidade("Testópolis");
        enderecoDTO1.setEstado("São Teste");
        enderecoDTO1.setCep("67125-869");
        paciente.setEndereco(enderecoDTO1);
        UsuarioDTO usuario4 = new UsuarioDTO();
        usuario4.setUsername("Lucas2");
        usuario4.setPassword("123456");
        usuario4.setPerfil("PACIENTE");
        paciente.setUsuario(usuario4);
        servicePaciente.salvar(paciente);

        //Consulta
        ConsultaDTO consultaDTO = new ConsultaDTO();
        consultaDTO.setDataHoraConsulta(Timestamp.valueOf("2022-12-31 09:30:00"));
        consultaDTO.setDentista(dentista1);
        consultaDTO.setPaciente(paciente);
        serviceConsulta.salvar(consultaDTO);

        //Confirmando se a consulta foi criada no banco de dados
        Assertions.assertTrue(serviceConsulta.repository.findByIdConsulta("2022-12-31 09:30:00.0778.311.869-05789488").isPresent());
    }

    //Testando o Buscar completo
    @Test
    void buscar(){
        //Buscando por todas as entradas e validando se dentro dos resultados o IdConsulta criado está presente
        log.info("Buscando por todas as entradas e validando se dentro dos resultados o IdConsulta criado está presente");
        Assertions.assertTrue(serviceConsulta.buscar().stream().filter(consultaDTO -> consultaDTO.getIdConsulta().equals("2022-12-31 09:30:00.0421.346.403-24422789")).findFirst().isPresent());
    }

    //Teste do método buscarPorId
    @Test
    void buscarPorId()throws CadastroInvalidoException{

        //Buscando a consulta criada para o teste e verificando se ela foi encontrada (Status code 200)
        log.info("Buscando a consulta criada para o teste e verificando se ela foi encontrada (Status code 200)");
        Assertions.assertEquals(200, serviceConsulta.buscarPorId("2022-12-31 09:30:00.0421.346.403-24422789").getStatusCodeValue());
    }

    //Testando o método deletar
    @Test
    void deletar(){
        log.info("Deletando a consulta criada para o teste");
        //Deletando a consulta criada para o teste
        serviceConsulta.deletar("2022-12-31 09:30:00.0550.749.320-09422789");
        //Verificando se a consulta foi apagada
        Assertions.assertTrue(serviceConsulta.buscarPorId("2022-12-31 09:30:00.0550.749.320-09422789").getStatusCodeValue() != 200);
    }


}
