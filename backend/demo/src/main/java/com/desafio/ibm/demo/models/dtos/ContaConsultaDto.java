package com.desafio.ibm.demo.models.dtos;

import com.desafio.ibm.demo.models.Conta;

import java.util.Date;

public record ContaConsultaDto(
        String numeroConta,
        String digitoConta,
        String agencia,
        Date data_abertura
) {
    public ContaConsultaDto(Conta conta){
        this(conta.getNumeroConta(),
                conta.getDigitoConta(),
                conta.getAgencia(),
                conta.getDataAbertura());
    }
}
