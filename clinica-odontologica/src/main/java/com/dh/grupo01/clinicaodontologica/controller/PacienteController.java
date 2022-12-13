package com.dh.grupo01.clinicaodontologica.controller;

import com.dh.grupo01.clinicaodontologica.entity.Paciente;
import com.dh.grupo01.clinicaodontologica.entity.dto.DentistaDTO;
import com.dh.grupo01.clinicaodontologica.entity.dto.PacienteDTO;
import com.dh.grupo01.clinicaodontologica.exception.CadastroInvalidoException;
import com.dh.grupo01.clinicaodontologica.exception.ResourceNotFoundException;
import com.dh.grupo01.clinicaodontologica.service.impl.PacienteIMPLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    PacienteIMPLService service ;

    @GetMapping()
    public List<PacienteDTO> buscar() {
        return service.buscar();
    }

    @GetMapping("/buscarCpf/{cpf}")
    public ResponseEntity buscarPorCpf(@PathVariable String cpf) throws ResourceNotFoundException{
        return service.buscarPorCpf(cpf);
    }
    @PostMapping()
    public ResponseEntity salvar(@RequestBody @Valid PacienteDTO pacienteDTO) throws CadastroInvalidoException {
        return service.salvar(pacienteDTO);
    }

    @PatchMapping("/deletar")
    public ResponseEntity deletar(@RequestBody PacienteDTO pacienteDTO){
        return service.deletar(pacienteDTO);
    }

    @PutMapping()
    public ResponseEntity atualizarTotal(@RequestBody PacienteDTO pacienteDTO) throws ResourceNotFoundException{
        return service.atualizarTotal(pacienteDTO);
    }


    @PatchMapping()
    public ResponseEntity atualizarParcial(@RequestBody PacienteDTO pacienteDTO) throws ResourceNotFoundException{
        return service.atualizarParcial(pacienteDTO);
    }



}
