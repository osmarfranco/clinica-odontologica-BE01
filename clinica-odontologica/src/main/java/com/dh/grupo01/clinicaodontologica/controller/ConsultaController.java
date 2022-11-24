package com.dh.grupo01.clinicaodontologica.controller;


import com.dh.grupo01.clinicaodontologica.entity.Consulta;
import com.dh.grupo01.clinicaodontologica.service.impl.ConsultaIMPLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    ConsultaIMPLService service;


    @GetMapping()
    public List<Consulta> buscar(){
        return service.buscar();
    }

    @PostMapping()
    public ResponseEntity salvar(@RequestBody Consulta consulta){

        return service.salvar(consulta);
    }

    @DeleteMapping()
    public Consulta deletar(@RequestParam("id") Long id){
        return service.deletar(id);
    }
    @PatchMapping()
    public Consulta alteracaoParcial(@RequestBody Consulta consulta){
        return service.atualizarParcial(consulta);
    }
    @PutMapping()
    public Consulta atualizar(@RequestBody Consulta consulta){
        return service.atualizar(consulta);
    }


}