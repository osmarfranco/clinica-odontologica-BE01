package com.dh.grupo01.clinicaodontologica.controller;

import com.dh.grupo01.clinicaodontologica.entity.Paciente;
import com.dh.grupo01.clinicaodontologica.entity.dto.PacienteDTO;
import com.dh.grupo01.clinicaodontologica.service.impl.PacienteIMPLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/buscarCpf/{cpf}")
    public ResponseEntity buscarPorCpf(@PathVariable String cpf){
        return service.buscarPorCpf(cpf);
    }
    @PostMapping()
    public ResponseEntity salvar(@RequestBody @Valid PacienteDTO pacienteDTO){
        return service.salvar(pacienteDTO);
    }

    @DeleteMapping()
    public ResponseEntity deletar(@RequestParam("cpf") String cpf){
        return service.deletar(cpf);
    }

    @PutMapping()
    public ResponseEntity atualizarTotal(@RequestBody PacienteDTO pacienteDTO){
        return service.atualizarTotal(pacienteDTO);
    }


    @PatchMapping()
    public ResponseEntity atualizarParcial(@RequestBody PacienteDTO pacienteDTO){
        return service.atualizarParcial(pacienteDTO);
    }



}
