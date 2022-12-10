package com.dh.grupo01.clinicaodontologica;

import com.dh.grupo01.clinicaodontologica.entity.dto.UsuarioDTO;
import com.dh.grupo01.clinicaodontologica.service.impl.UsuarioIMPLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateUserRun implements ApplicationRunner {

    @Autowired
    UsuarioIMPLService service;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setUsername("julian");
        usuario.setPassword("123456");
        usuario.setPerfil("ADMIN");
        service.salvar(usuario);

    }
}
