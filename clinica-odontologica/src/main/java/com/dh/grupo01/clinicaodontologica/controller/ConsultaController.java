package com.dh.grupo01.clinicaodontologica.controller;


import com.dh.grupo01.clinicaodontologica.model.Consulta;
import com.dh.grupo01.clinicaodontologica.service.impl.ConsultaIMPLService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity salvar(@RequestBody Consulta consulta){

        return service.salvar(consulta);
    }

    @DeleteMapping()
    public Consulta deletar(@RequestBody Consulta consulta){
        return service.deletar(consulta);
    }
    @PatchMapping()
    public Consulta alteracaoParcial(@RequestBody Consulta consulta){
        return service.atualizarParcial(consulta);
    }
    @PutMapping()
    public Consulta atualizar(@RequestBody Consulta consulta){
        return service.atualizar(consulta);
    }


}