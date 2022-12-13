package com.dh.grupo01.clinicaodontologica.controller;


import com.dh.grupo01.clinicaodontologica.entity.dto.ConsultaDTO;
import com.dh.grupo01.clinicaodontologica.exception.CadastroInvalidoException;
import com.dh.grupo01.clinicaodontologica.service.impl.ConsultaIMPLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    ConsultaIMPLService service;


    @GetMapping()
    public List<ConsultaDTO> buscar(){
        return service.buscar();
    }

    @GetMapping("/buscarIdConsulta/{idConsulta}")
    public ResponseEntity buscarPorId(@PathVariable String idConsulta){
        return service.buscarPorId(idConsulta);
    }

    @PostMapping()
    public ResponseEntity salvar(@RequestBody ConsultaDTO consultaDTO) throws CadastroInvalidoException {

        return service.salvar(consultaDTO);
    }

    @DeleteMapping()
    public ResponseEntity deletar(@RequestParam("idConsulta") String idConsulta){
        return service.deletar(idConsulta);
    }

//    @PutMapping()
//    public ResponseEntity atualizar(@RequestBody ConsultaDto consulta){
//
//        return service.atualizar(consulta);
//    }


//    @PatchMapping()
//    public ResponseEntity alteracaoParcial(@RequestBody Consulta consulta){
//        return service.atualizarParcial(consulta);
//    }



}