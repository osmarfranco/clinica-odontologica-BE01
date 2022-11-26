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

    @GetMapping("/buscarRg/{rg}")
    public ResponseEntity buscarPorCro(@PathVariable String rg){
        return service.buscarPorRg(rg);
    }
    @PostMapping()
    public ResponseEntity salvar(@RequestBody Paciente paciente){

        return service.salvar(paciente);
    }

    @DeleteMapping()
    public ResponseEntity deletar(@RequestParam("rg") String rg){
        return service.deletar(rg);
    }

    @PutMapping()
    public ResponseEntity atualizarTotal(@RequestBody Paciente paciente){
        return service.atualizarTotal(paciente);
    }


    @PatchMapping()
    public ResponseEntity atualizarParcial(@RequestBody Paciente paciente){
        return service.atualizarParcial(paciente);
    }



}
