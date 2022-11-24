package com.dh.grupo01.clinicaodontologica.controller;

import com.dh.grupo01.clinicaodontologica.entity.Dentista;
import com.dh.grupo01.clinicaodontologica.entity.dto.DentistaDTO;
import com.dh.grupo01.clinicaodontologica.service.impl.DentistaIMPLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/dentista")
public class DentistaController {

    @Autowired
    DentistaIMPLService service;

    @GetMapping()
    public List<DentistaDTO> buscar(){
        return service.buscar();
    }

    @PostMapping()
    public ResponseEntity salvar(@RequestBody Dentista dentista){

        return service.salvar(dentista);
    }

    @DeleteMapping()
    public ResponseEntity deletar(@RequestParam("id") Long id){
        return service.deletar(id);
    }
//    @PatchMapping()
//    public Dentista alteracaoParcial(@RequestBody Dentista dentista){
//
//        return service.atualizarParcial(dentista);
//    }
//    @PutMapping()
//    public Dentista alteracaoTotal(@RequestBody Dentista dentista){
//
//        return service.atualizar(dentista);
//    }
//
}
