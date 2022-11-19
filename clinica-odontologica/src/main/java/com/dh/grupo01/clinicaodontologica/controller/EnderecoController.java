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
