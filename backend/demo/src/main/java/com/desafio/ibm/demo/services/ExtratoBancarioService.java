package com.desafio.ibm.demo.services;

import com.desafio.ibm.demo.exceptions.RecursoNaoEncontradoExcecao;
import com.desafio.ibm.demo.models.Transacao;
import com.desafio.ibm.demo.models.dtos.ExtratoBancarioDto;
import com.desafio.ibm.demo.repositories.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ExtratoBancarioService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    public ExtratoBancarioDto gerarExtrato(UUID contaId, LocalDate dataInicial, LocalDate dataFinal){
        LocalDateTime dataInicialComHora = dataInicial.atStartOfDay();
        LocalDateTime dataFinalComHora = dataFinal.atTime(23, 59, 59);

        Date dataInicialConvertida = Date.from(dataInicialComHora.atZone(ZoneId.systemDefault()).toInstant());
        Date dataFinalConvertida = Date.from(dataFinalComHora.atZone(ZoneId.systemDefault()).toInstant());

        List<Transacao> transacoes = transacaoRepository
                                        .findByContaContaIdAndDataTransacaoBetween(contaId, dataInicialConvertida, dataFinalConvertida);

        if(transacoes.isEmpty()){
            throw new RecursoNaoEncontradoExcecao("Conta id incorreto.");
        }
        double saldo = transacoes.stream().mapToDouble(transacao -> {
            if(transacao.getTipoTransacao() == 'D'){
                return - transacao.getValor();
            }else{
                return transacao.getValor();
            }
        }).sum();

        return new ExtratoBancarioDto(dataInicial, dataFinal, saldo, transacoes);
    }


}
