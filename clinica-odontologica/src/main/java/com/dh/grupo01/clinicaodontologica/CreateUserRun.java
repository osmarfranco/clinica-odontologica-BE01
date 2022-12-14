package com.dh.grupo01.clinicaodontologica;

import com.dh.grupo01.clinicaodontologica.entity.dto.*;
import com.dh.grupo01.clinicaodontologica.service.impl.ConsultaIMPLService;
import com.dh.grupo01.clinicaodontologica.service.impl.DentistaIMPLService;
import com.dh.grupo01.clinicaodontologica.service.impl.PacienteIMPLService;
import com.dh.grupo01.clinicaodontologica.service.impl.UsuarioIMPLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;

@Configuration
public class CreateUserRun implements ApplicationRunner {

    @Autowired
    UsuarioIMPLService service;

    @Autowired
    PacienteIMPLService pacienteService;

    @Autowired
    DentistaIMPLService dentistaService;

    @Autowired
    ConsultaIMPLService consultaService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setUsername("julian");
        usuario.setPassword("123456");
        usuario.setPerfil("ADMIN");
        service.salvar(usuario);

        //Dentistas
        DentistaDTO dentista1 = new DentistaDTO();
        dentista1.setNome("Mustafa");
        dentista1.setSobrenome("Haddad");
        dentista1.setCro("456987");

        UsuarioDTO usuario2 = new UsuarioDTO();
        usuario2.setUsername("mustafa");
        usuario2.setPassword("123456");
        usuario2.setPerfil("DENTISTA");

        dentista1.setUsuario(usuario2);

        DentistaDTO dentista2 = new DentistaDTO();
        dentista2.setNome("Kalil");
        dentista2.setSobrenome("Kabelo");
        dentista2.setCro("56984");

        UsuarioDTO usuario3 = new UsuarioDTO();
        usuario3.setUsername("kalil");
        usuario3.setPassword("123456");
        usuario3.setPerfil("DENTISTA");

        dentista2.setUsuario(usuario3);

        dentistaService.salvar(dentista1);
        dentistaService.salvar(dentista2);

        //Paciente
        PacienteDTO paciente = new PacienteDTO();
        paciente.setNome("Lucas");
        paciente.setSobrenome("Teste");
        paciente.setCpf("358.023.642-38");

        EnderecoDTO enderecoDTO1 = new EnderecoDTO();
        enderecoDTO1.setLogradouro("Rua dos testes");
        enderecoDTO1.setNumero(123);
        enderecoDTO1.setComplemento(null);
        enderecoDTO1.setBairro("Testolândia");
        enderecoDTO1.setCidade("Testópolis");
        enderecoDTO1.setEstado("São Teste");
        enderecoDTO1.setCep("67125-869");

        paciente.setEndereco(enderecoDTO1);

        UsuarioDTO usuario4 = new UsuarioDTO();
        usuario4.setUsername("lucas");
        usuario4.setPassword("123456");
        usuario4.setPerfil("PACIENTE");

        paciente.setUsuario(usuario4);

        PacienteDTO paciente2 = new PacienteDTO();
        paciente2.setCpf("765.319.122-35");
        paciente2.setNome("Testildo");
        paciente2.setSobrenome("Silva");

        EnderecoDTO endereco2 = new EnderecoDTO();
        endereco2.setLogradouro("Rua dos testes");
        endereco2.setNumero(123);
        endereco2.setComplemento(null);
        endereco2.setBairro("Testolândia");
        endereco2.setCidade("Testópolis");
        endereco2.setEstado("São Teste");
        endereco2.setCep("67125-869");

        paciente2.setEndereco(endereco2);

        UsuarioDTO usuario5 = new UsuarioDTO();
        usuario5.setUsername("testildo");
        usuario5.setPassword("123456");
        usuario5.setPerfil("PACIENTE");

        paciente2.setUsuario(usuario5);

        pacienteService.salvar(paciente);
        pacienteService.salvar(paciente2);


        //Criando e setando novos dados
        DentistaDTO dentista7 = new DentistaDTO();
        dentista7.setNome("Jalin");
        dentista7.setSobrenome("Haddad");
        dentista7.setCro("422789");
        UsuarioDTO usuario7 = new UsuarioDTO();
        usuario7.setUsername("Jalin");
        usuario7.setPassword("123456");
        usuario7.setPerfil("DENTISTA");
        dentista7.setUsuario(usuario7);
        dentistaService.salvar(dentista7);

        PacienteDTO paciente7 = new PacienteDTO();
        paciente7.setNome("Lucas");
        paciente7.setSobrenome("Teste");
        paciente7.setCpf("421.346.403-24");
        EnderecoDTO enderecoDTO7 = new EnderecoDTO();
        enderecoDTO7.setLogradouro("Rua dos testes");
        enderecoDTO7.setNumero(123);
        enderecoDTO7.setComplemento(null);
        enderecoDTO7.setBairro("Testolândia");
        enderecoDTO7.setCidade("Testópolis");
        enderecoDTO7.setEstado("São Teste");
        enderecoDTO7.setCep("67125-869");
        paciente7.setEndereco(enderecoDTO7);
        UsuarioDTO usuario8 = new UsuarioDTO();
        usuario8.setUsername("Lucas");
        usuario8.setPassword("123456");
        usuario8.setPerfil("PACIENTE");
        paciente7.setUsuario(usuario8);
        pacienteService.salvar(paciente7);

        //Consulta
        ConsultaDTO consultaDTO = new ConsultaDTO();
        consultaDTO.setDataHoraConsulta(Timestamp.valueOf("2022-12-31 09:30:00"));
        consultaDTO.setDentista(dentista7);
        consultaDTO.setPaciente(paciente7);
        consultaService.salvar(consultaDTO);



    }
}
