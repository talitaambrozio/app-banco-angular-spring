package com.desafio.ibm.demo.controllers;

import com.desafio.ibm.demo.models.dtos.ExtratoBancarioDto;
import com.desafio.ibm.demo.services.ExtratoBancarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/extrato-bancario")
public class ExtratoBancarioController {

    @Autowired
    private ExtratoBancarioService extratoBancarioService;

    @GetMapping
    public ResponseEntity<ExtratoBancarioDto> gerarExtratoBancario(@RequestParam UUID contaID,
                                                                   @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate  dataInicial,
                                                                   @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate  dataFinal){
        return new ResponseEntity<ExtratoBancarioDto>(extratoBancarioService.gerarExtrato(contaID, dataInicial, dataFinal),
                HttpStatus.OK);
    }
}
