package com.dh.grupo01.clinicaodontologica.controller;


import com.dh.grupo01.clinicaodontologica.model.Consulta;
import com.dh.grupo01.clinicaodontologica.model.Paciente;
import com.dh.grupo01.clinicaodontologica.service.impl.ConsultaIMPLService;
import com.dh.grupo01.clinicaodontologica.service.impl.PacienteIMPLService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    ConsultaIMPLService service = new ConsultaIMPLService();

    @GetMapping()
    public List<Consulta> buscar(){
        return service.buscar();
    }

    @PostMapping()
    public Consulta salvar(@RequestBody Consulta consulta){

        return service.salvar(consulta);
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