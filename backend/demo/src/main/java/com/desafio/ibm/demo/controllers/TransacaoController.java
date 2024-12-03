package com.desafio.ibm.demo.controllers;

import com.desafio.ibm.demo.models.dtos.TransacaoConsultaDto;
import com.desafio.ibm.demo.models.dtos.TransacaoRegistroDto;
import com.desafio.ibm.demo.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/transacao")
public class TransacaoController {

    @Autowired
    private  TransacaoService transacaoService;

    @PostMapping("/nova-transacao/")
    public ResponseEntity<TransacaoConsultaDto> novaTransacao(@RequestBody TransacaoRegistroDto transacaoRegistroDto){
        return new ResponseEntity<TransacaoConsultaDto>(transacaoService.novaTransacao(transacaoRegistroDto),
                HttpStatus.CREATED);
    }

    @GetMapping("/transacoes/{contaId}")
    public ResponseEntity<List<TransacaoConsultaDto>> consultarTransacoes(@PathVariable UUID contaId){
        return new ResponseEntity<List<TransacaoConsultaDto>>(transacaoService.consultarTransacoes(contaId),
                HttpStatus.OK);
    }
}
