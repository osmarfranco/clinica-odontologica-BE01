package com.dh.grupo01.clinicaodontologica;

import com.dh.grupo01.clinicaodontologica.entity.dto.PerfilDTO;
import com.dh.grupo01.clinicaodontologica.service.impl.PerfilIMPLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreatePerfilRun implements ApplicationRunner {

    @Autowired
    PerfilIMPLService service;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        PerfilDTO perfilAdmin = new PerfilDTO();
        perfilAdmin.setDescricao("ADMIN");
        service.salvar(perfilAdmin);

        PerfilDTO perfilAtendente = new PerfilDTO();
        perfilAtendente.setDescricao("ATENDENTE");
        service.salvar(perfilAtendente);

        PerfilDTO perfilDentista = new PerfilDTO();
        perfilDentista.setDescricao("DENTISTA");
        service.salvar(perfilDentista);

        PerfilDTO perfilPaciente = new PerfilDTO();
        perfilPaciente.setDescricao("PACIENTE");
        service.salvar(perfilPaciente);

    }
}
