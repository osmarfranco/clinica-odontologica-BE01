package com.dh.grupo01.clinicaodontologica.controller;

import com.dh.grupo01.clinicaodontologica.entity.Endereco;
import com.dh.grupo01.clinicaodontologica.service.impl.EnderecoIMPLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    EnderecoIMPLService service;

    @GetMapping()
    public List<Endereco> buscar(){
        return service.buscar();
    }
    @PostMapping()
    public ResponseEntity salvar(@RequestBody Endereco endereco){

        return service.salvar(endereco);
    }
    @DeleteMapping()
    public Endereco deletar(@RequestBody Endereco endereco){
        return service.deletar(endereco);
    }
    @PatchMapping()
    public Endereco alteracaoParcial(@RequestBody Endereco endereco){
        return service.atualizarParcial(endereco);
    }
    @PutMapping()
    public Endereco atualizar(@RequestBody Endereco endereco){
        return service.atualizar(endereco);
    }

}
