package com.dh.grupo01.clinicaodontologica.repository;

import com.dh.grupo01.clinicaodontologica.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    Optional<Consulta> findByIdConsulta(String idConsulta);
}
