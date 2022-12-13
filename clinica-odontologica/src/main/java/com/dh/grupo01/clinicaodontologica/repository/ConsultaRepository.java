package com.dh.grupo01.clinicaodontologica.repository;

import com.dh.grupo01.clinicaodontologica.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    Optional<Consulta> findByIdConsulta(String idConsulta);

    List<Consulta> findByDataHoraConsulta(Timestamp dataHoraConsulta);

}
