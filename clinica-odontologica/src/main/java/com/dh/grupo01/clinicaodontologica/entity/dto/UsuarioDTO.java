package com.dh.grupo01.clinicaodontologica.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioDTO {

    @NotBlank
    @Size(min = 6)
    private String username;

    @NotBlank
    @Size(min = 6)
    private String password;

<<<<<<< Updated upstream
//    @NotBlank
=======
>>>>>>> Stashed changes
    @JsonIgnore //colocado para não dar conflito com a entidade na hora de usar o mapper no service
    private String perfil;

    public UsernamePasswordAuthenticationToken converter(){
        return new UsernamePasswordAuthenticationToken(this.username, this.password);
    }
}
