package com.dh.grupo01.clinicaodontologica.controller;

import com.dh.grupo01.clinicaodontologica.entity.dto.UsuarioDTO;
import com.dh.grupo01.clinicaodontologica.exception.CadastroInvalidoException;
import com.dh.grupo01.clinicaodontologica.exception.ResourceNotFoundException;
import com.dh.grupo01.clinicaodontologica.service.impl.UsuarioIMPLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioIMPLService service;

    @GetMapping
    public List<UsuarioDTO> buscar() { return service.buscar(); }

    @PostMapping
    public ResponseEntity salvar(@RequestBody UsuarioDTO usuarioDTO) throws CadastroInvalidoException { return service.salvar(usuarioDTO); }

    @DeleteMapping
    public ResponseEntity deletar(@RequestParam("username") String username) throws ResourceNotFoundException { return service.deletar(username); }

    @PatchMapping
    public ResponseEntity atualizarSenha(@RequestBody UsuarioDTO usuarioDTO) throws ResourceNotFoundException { return service.atualizarSenha(usuarioDTO); }

}
