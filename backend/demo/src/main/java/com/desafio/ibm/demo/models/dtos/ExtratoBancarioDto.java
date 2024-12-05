package com.desafio.ibm.demo.models.dtos;

import com.desafio.ibm.demo.models.Transacao;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public record ExtratoBancarioDto(

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataInicial,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataFinal,
        Double saldoAtual,
        Double saldoDoPeriodo,
        List<Transacao> transacoes
) {
}
