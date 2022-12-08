package com.dh.grupo01.clinicaodontologica.controller;

import com.dh.grupo01.clinicaodontologica.entity.Endereco;
import com.dh.grupo01.clinicaodontologica.entity.dto.EnderecoDTO;
import com.dh.grupo01.clinicaodontologica.exception.CadastroInvalidoException;
import com.dh.grupo01.clinicaodontologica.exception.ResourceNotFoundException;
import com.dh.grupo01.clinicaodontologica.service.impl.EnderecoIMPLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    EnderecoIMPLService service;

    @GetMapping()
    public List<EnderecoDTO> buscar(){
        return service.buscar();
    }
    @PostMapping()
    public ResponseEntity salvar(@RequestBody EnderecoDTO enderecoDTO) throws CadastroInvalidoException {

        return service.salvar(enderecoDTO);
    }
    @DeleteMapping()
    public ResponseEntity deletar(@RequestParam("id") Long id) throws ResourceNotFoundException {
        return service.deletar(id);
    }

//    @PatchMapping()
//    public Endereco alteracaoParcial(@RequestBody Endereco endereco){
//        return service.atualizarParcial(endereco);
//    }
//    @PutMapping()
//    public Endereco atualizar(@RequestBody Endereco endereco){
//        return service.atualizar(endereco);
//    }

}
