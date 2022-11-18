package com.dh.grupo01.clinicaodontologica.controller;

import com.dh.grupo01.clinicaodontologica.model.Endereco;
import com.dh.grupo01.clinicaodontologica.service.impl.EnderecoIMPLService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    EnderecoIMPLService service = new EnderecoIMPLService();

    @GetMapping()
    public List<Endereco> buscar(){
        return service.buscar();
    }
    @PostMapping()
    public Endereco salvar(@RequestBody Endereco endereco){
        System.out.println();
        return service.salvar(endereco);
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
