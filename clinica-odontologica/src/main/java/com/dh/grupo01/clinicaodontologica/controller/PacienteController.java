package com.dh.grupo01.clinicaodontologica.controller;

import com.dh.grupo01.clinicaodontologica.model.Paciente;
import com.dh.grupo01.clinicaodontologica.service.impl.PacienteIMPLService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    PacienteIMPLService service = new PacienteIMPLService();

    @GetMapping()
    public List<Paciente> buscar(){
        return service.buscar();
    }

    @PostMapping()
    public Paciente salvar(@RequestBody Paciente paciente){

        return service.salvar(paciente);
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
