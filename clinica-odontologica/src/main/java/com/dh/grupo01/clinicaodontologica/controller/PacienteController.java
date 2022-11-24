package com.dh.grupo01.clinicaodontologica.controller;

import com.dh.grupo01.clinicaodontologica.entity.Paciente;
import com.dh.grupo01.clinicaodontologica.entity.dto.PacienteDTO;
import com.dh.grupo01.clinicaodontologica.service.impl.PacienteIMPLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    PacienteIMPLService service ;

    @GetMapping()
    public List<PacienteDTO> buscar(){
        return service.buscar();
    }

    @PostMapping()
    public ResponseEntity salvar(@RequestBody Paciente paciente){

        return service.salvar(paciente);
    }

    @DeleteMapping()
    public Paciente deletar(@RequestParam("id") Long id){
        return service.deletar(id);
    }
    @PatchMapping()
//    public Paciente alteracaoParcial(@RequestBody Paciente paciente){
//        return service.atualizarParcial(paciente);
//    }
//    @PutMapping()
//    public Paciente atualizar(@RequestBody Paciente paciente){
//        return service.atualizar(paciente);
//    }


}
