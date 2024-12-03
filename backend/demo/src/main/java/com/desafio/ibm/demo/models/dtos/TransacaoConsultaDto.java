package com.desafio.ibm.demo.models.dtos;

import com.desafio.ibm.demo.models.Transacao;

import java.util.List;
import java.util.stream.Collectors;

public record TransacaoConsultaDto(
        char tipoTransacao,
        Double valor,
        String descricao,
        String numeroConta
) {
    public TransacaoConsultaDto(Transacao transacao){
        this(transacao.getTipoTransacao(),
                transacao.getValor(),
                transacao.getDescricao(),
                transacao.getConta().getNumeroConta());
    }

    public static List<TransacaoConsultaDto> converterParaListaDto(List<Transacao> transacoes){
        return transacoes.stream()
                .map(TransacaoConsultaDto::new)
                .collect(Collectors.toList());
    }
}
