package com.dh.grupo01.clinicaodontologica.controller;

import com.dh.grupo01.clinicaodontologica.entity.dto.PerfilDTO;
import com.dh.grupo01.clinicaodontologica.exception.CadastroInvalidoException;
import com.dh.grupo01.clinicaodontologica.exception.ResourceNotFoundException;
import com.dh.grupo01.clinicaodontologica.service.impl.PerfilIMPLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    PerfilIMPLService service;

    @GetMapping
    public List<PerfilDTO> buscar() { return service.buscar(); }

    @PostMapping
    public ResponseEntity salvar(@RequestBody PerfilDTO perfilDTO) throws CadastroInvalidoException { return service.salvar(perfilDTO); }

    @DeleteMapping
    public ResponseEntity deletar(@RequestParam("descricao") String descricao) throws ResourceNotFoundException { return service.deletar(descricao); }
}
