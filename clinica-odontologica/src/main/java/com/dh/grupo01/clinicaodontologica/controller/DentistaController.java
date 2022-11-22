package com.dh.grupo01.clinicaodontologica.controller;

import com.dh.grupo01.clinicaodontologica.model.Dentista;
import com.dh.grupo01.clinicaodontologica.service.impl.DentistaIMPLService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity salvar(@RequestBody Dentista dentista){

        return service.salvar(dentista);
    }

    @DeleteMapping()
    public Dentista deletar(@RequestBody Dentista dentista){
        return service.deletar(dentista);
    }
    @PatchMapping()
    public Dentista alteracaoParcial(@RequestBody Dentista dentista){

        return service.atualizarParcial(dentista);
    }
    @PutMapping()
    public Dentista alteracaoTotal(@RequestBody Dentista dentista){

        return service.atualizar(dentista);
    }

}
