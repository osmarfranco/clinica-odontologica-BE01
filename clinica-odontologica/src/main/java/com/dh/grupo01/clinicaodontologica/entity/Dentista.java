package com.dh.grupo01.clinicaodontologica.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Dentista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String cro; //cro é a matrícula do dentista, assim como a do médico é o crm

    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;

    @OneToOne (cascade = CascadeType.ALL)
    private Usuario usuario;

    private Integer deletado;
}
