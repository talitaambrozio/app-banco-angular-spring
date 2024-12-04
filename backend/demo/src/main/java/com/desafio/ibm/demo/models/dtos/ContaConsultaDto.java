package com.desafio.ibm.demo.models.dtos;

import com.desafio.ibm.demo.models.Conta;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record ContaConsultaDto(
        String numeroConta,
        String digitoConta,
        String agencia,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        Date data_abertura
) {
    public ContaConsultaDto(Conta conta){
        this(conta.getNumeroConta(),
                conta.getDigitoConta(),
                conta.getAgencia(),
                conta.getDataAbertura());
    }
}
