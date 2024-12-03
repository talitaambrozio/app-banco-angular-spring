package com.desafio.ibm.demo.services;

import com.desafio.ibm.demo.exceptions.RecursoNaoEncontradoExcecao;
import com.desafio.ibm.demo.models.Conta;
import com.desafio.ibm.demo.models.Transacao;
import com.desafio.ibm.demo.models.dtos.TransacaoConsultaDto;
import com.desafio.ibm.demo.models.dtos.TransacaoRegistroDto;
import com.desafio.ibm.demo.repositories.ContaRepository;
import com.desafio.ibm.demo.repositories.TransacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
