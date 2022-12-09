package com.dh.grupo01.clinicaodontologica.controller;

import com.dh.grupo01.clinicaodontologica.entity.Dentista;
import com.dh.grupo01.clinicaodontologica.entity.dto.DentistaDTO;
import com.dh.grupo01.clinicaodontologica.service.impl.DentistaIMPLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/dentista")
public class DentistaController {

    @Autowired
    DentistaIMPLService service;


    @GetMapping()
    public List<DentistaDTO> buscar(){
        return service.buscar();
    }

    @GetMapping("/buscarCro/{cro}")
    public ResponseEntity buscarPorCro(@PathVariable String cro){
        return service.buscarPorCro(cro);
    }

    @PostMapping()
    public ResponseEntity salvar(@RequestBody DentistaDTO dentistaDTO){
        return service.salvar(dentistaDTO);
    }

    @PatchMapping("/deletar")
    public ResponseEntity deletar(@RequestBody DentistaDTO dentistaDTO){
        return service.deletar(dentistaDTO);
    }

    @PutMapping()
    public ResponseEntity atualizarTotal( @RequestBody DentistaDTO dentistaDTO){
        return service.atualizarTotal(dentistaDTO);
    }


    @PatchMapping()
    public ResponseEntity alteracaoParcial(@RequestBody DentistaDTO dentistaDTO){
        return service.atualizarParcial(dentistaDTO);
    }


}
