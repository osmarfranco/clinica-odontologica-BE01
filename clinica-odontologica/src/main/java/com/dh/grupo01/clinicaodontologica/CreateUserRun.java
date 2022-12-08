package com.dh.grupo01.clinicaodontologica;

import com.dh.grupo01.clinicaodontologica.entity.Usuario;
import com.dh.grupo01.clinicaodontologica.entity.dto.UsuarioDTO;
import com.dh.grupo01.clinicaodontologica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class CreateUserRun implements ApplicationRunner {

    @Autowired
    UsuarioRepository repository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

        Usuario usuario = new Usuario();
        usuario.setUsername("julian");
        usuario.setPassword(bCrypt.encode("123456"));

        repository.save(usuario);
    }
}
