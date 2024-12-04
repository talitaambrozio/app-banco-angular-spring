package com.desafio.ibm.demo.controllers;

import com.desafio.ibm.demo.models.dtos.ExtratoBancarioDto;
import com.desafio.ibm.demo.models.dtos.TransacaoConsultaDto;
import com.desafio.ibm.demo.models.dtos.TransacaoRegistroDto;
import com.desafio.ibm.demo.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    @GetMapping("/transacoes/gerar-extrato-bancario")
    public ResponseEntity<ExtratoBancarioDto> gerarExtratoBancario(@RequestParam UUID contaId,
                                                                   @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataInicial,
                                                                   @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate  dataFinal){
        return new ResponseEntity<ExtratoBancarioDto>(transacaoService.gerarExtrato(contaId, dataInicial, dataFinal),
                HttpStatus.OK);
    }

    @GetMapping("/transacoes/obter-saldo/")
    public ResponseEntity<Double> obterSaldo(@PathVariable UUID contaId){
        return new ResponseEntity<Double>(transacaoService.obterSaldoAtual(contaId),
                HttpStatus.OK);
    }

}
