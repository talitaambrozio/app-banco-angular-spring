package com.desafio.ibm.demo.controllers;

import com.desafio.ibm.demo.models.dtos.ContaConsultaDto;
import com.desafio.ibm.demo.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("api/v1/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping("/dados-bancarios/{id}")
    public ResponseEntity<ContaConsultaDto> buscarConta(@PathVariable UUID id){
        return new ResponseEntity<ContaConsultaDto>(contaService.buscarDadosConta(id),
                HttpStatus.OK);
    }
}
