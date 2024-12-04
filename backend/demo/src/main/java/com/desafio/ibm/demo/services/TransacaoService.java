package com.desafio.ibm.demo.services;

import com.desafio.ibm.demo.exceptions.RecursoNaoEncontradoExcecao;
import com.desafio.ibm.demo.models.Conta;
import com.desafio.ibm.demo.models.Transacao;
import com.desafio.ibm.demo.models.dtos.ExtratoBancarioDto;
import com.desafio.ibm.demo.models.dtos.TransacaoConsultaDto;
import com.desafio.ibm.demo.models.dtos.TransacaoRegistroDto;
import com.desafio.ibm.demo.repositories.ContaRepository;
import com.desafio.ibm.demo.repositories.TransacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransacaoService {
    @Autowired
    private  TransacaoRepository transacaoRepository;
    @Autowired
    private  ContaRepository contaRepository;


    @Transactional
    public TransacaoConsultaDto novaTransacao(TransacaoRegistroDto transacaoRegistroDto){

        Transacao novaTransacao = new Transacao();
        novaTransacao.setDataTransacao(new Date());
        novaTransacao.setTipoTransacao(transacaoRegistroDto.tipoTransacao());
        novaTransacao.setValor(transacaoRegistroDto.valor());
        novaTransacao.setDescricao(transacaoRegistroDto.descricao());

        Optional<Conta> conta = contaRepository.findById(transacaoRegistroDto.conta().getContaId());
        if(conta.isEmpty()){
            throw new RecursoNaoEncontradoExcecao("Conta inexistente.");
        }
        conta.ifPresent(novaTransacao::setConta);
        transacaoRepository.save(novaTransacao);
        return new TransacaoConsultaDto(novaTransacao);
    }

    public List<TransacaoConsultaDto> consultarTransacoes(UUID contaId){
        List<Transacao> transacoes = transacaoRepository.findAllByContaContaId(contaId);
        if(transacoes.isEmpty()){
            throw new RecursoNaoEncontradoExcecao("Não há transações para a conta" + contaId + ".");
        }
        return TransacaoConsultaDto.converterParaListaDto(transacoes);
    }

    public ExtratoBancarioDto gerarExtrato(UUID contaId, LocalDate dataInicial, LocalDate dataFinal){
        LocalDateTime dataInicialComHora = dataInicial.atStartOfDay();
        LocalDateTime dataFinalComHora = dataFinal.atTime(23, 59, 59);

        Date dataInicialConvertida = Date.from(dataInicialComHora.atZone(ZoneId.systemDefault()).toInstant());
        Date dataFinalConvertida = Date.from(dataFinalComHora.atZone(ZoneId.systemDefault()).toInstant());

        List<Transacao> transacoes = transacaoRepository
                .findByContaContaIdAndDataTransacaoBetween(contaId, dataInicialConvertida, dataFinalConvertida);

        if(transacoes.isEmpty()){
            throw new RecursoNaoEncontradoExcecao("Parâmetros inválidos.");
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

    public Double obterSaldoAtual(UUID contaId){
        return transacaoRepository.findAll().stream().mapToDouble(transacao ->{
            if(transacao.getTipoTransacao() == 'D'){
                return - transacao.getValor();
            }else{
                return  transacao.getValor();
            }
        }).sum();
    }
}
