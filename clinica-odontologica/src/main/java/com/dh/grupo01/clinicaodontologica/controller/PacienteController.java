package com.dh.grupo01.clinicaodontologica.controller;

import com.dh.grupo01.clinicaodontologica.model.Paciente;
import com.dh.grupo01.clinicaodontologica.service.impl.PacienteIMPLService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity salvar(@RequestBody Paciente paciente){

        return service.salvar(paciente);
    }

    @DeleteMapping()
    public Paciente deletar(@RequestBody Paciente paciente){
        return service.deletar(paciente);
    }
    @PatchMapping()
    public Paciente alteracaoParcial(@RequestBody Paciente paciente){
        return service.atualizarParcial(paciente);
    }
    @PutMapping()
    public Paciente atualizar(@RequestBody Paciente paciente){
        return service.atualizar(paciente);
    }


}
