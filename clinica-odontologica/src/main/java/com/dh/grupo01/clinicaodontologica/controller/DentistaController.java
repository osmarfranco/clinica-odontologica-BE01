package com.dh.grupo01.clinicaodontologica.controller;

import com.dh.grupo01.clinicaodontologica.model.Dentista;
import com.dh.grupo01.clinicaodontologica.service.impl.DentistaIMPLService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/dentista")
public class DentistaController {
    DentistaIMPLService service = new DentistaIMPLService();

    @GetMapping()
    public List<Dentista> buscar(){
        return service.buscar();
    }

    @PostMapping()
    public Dentista salvar(@RequestBody Dentista dentista){

        return service.salvar(dentista);
    }

    @DeleteMapping()
    public String deletar(){
        return "Entrou no delete";
    }
    @PatchMapping()
    public String alteracaoParcial(){
        return "Entrou no  Patch";
    }
    @PutMapping()
    public String alteracaoTotal(){
        return "Entrou no  Put";
    }

}
