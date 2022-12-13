package com.dh.grupo01.clinicaodontologica.entity;

import lombok.*;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CPF
    @Column(nullable = false, unique = true)
    private String cpf;

    private Timestamp dataCadastro;
    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;

    @OneToOne (cascade = CascadeType.ALL)
    private Endereco endereco;

    @OneToOne (cascade = CascadeType.ALL)
    private Usuario usuario;

    //João aqui não seria int ao invés de Integer?
    private Integer deletado;

}
