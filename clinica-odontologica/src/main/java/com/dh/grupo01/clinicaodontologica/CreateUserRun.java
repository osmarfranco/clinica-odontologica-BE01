package com.dh.grupo01.clinicaodontologica;

import com.dh.grupo01.clinicaodontologica.entity.dto.DentistaDTO;
import com.dh.grupo01.clinicaodontologica.entity.dto.EnderecoDTO;
import com.dh.grupo01.clinicaodontologica.entity.dto.PacienteDTO;
import com.dh.grupo01.clinicaodontologica.entity.dto.UsuarioDTO;
import com.dh.grupo01.clinicaodontologica.service.impl.DentistaIMPLService;
import com.dh.grupo01.clinicaodontologica.service.impl.PacienteIMPLService;
import com.dh.grupo01.clinicaodontologica.service.impl.UsuarioIMPLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateUserRun implements ApplicationRunner {

    @Autowired
    UsuarioIMPLService service;

    @Autowired
    PacienteIMPLService pacienteService;

    @Autowired
    DentistaIMPLService dentistaService;

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

        DentistaDTO dentista2 = new DentistaDTO();
        dentista2.setNome("Kalil");
        dentista2.setSobrenome("Kabelo");
        dentista2.setCro("56984");

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

        pacienteService.salvar(paciente);
        pacienteService.salvar(paciente2);

    }
}
