package com.desafio.ibm.demo.services;

import com.desafio.ibm.demo.models.Conta;
import com.desafio.ibm.demo.models.dtos.ContaConsultaDto;
import com.desafio.ibm.demo.models.dtos.ContaRegistroDto;
import com.desafio.ibm.demo.repositories.ContaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private static final int MIN_NUMERO_CONTA = 10000000;
    private static final int MAX_NUMERO_CONTA = 99999999;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    /*@Transactional
    public ContaConsultaDto criarConta(ContaRegistroDto contaRegistroDto){
        Conta novaConta = new Conta();

        gerarNumeroContaEDigitoUnicos(novaConta);
        novaConta.setAgencia(contaRegistroDto.agencia());
        novaConta.setDataAbertura(new Date());
        contaRepository.save(novaConta);

        return new ContaConsultaDto(novaConta);
    }*/
    public Conta criarConta(ContaRegistroDto contaRegistroDto) {
        Conta novaConta = new Conta();
        gerarNumeroContaEDigitoUnicos(novaConta);
        novaConta.setAgencia(contaRegistroDto.agencia());
        novaConta.setDataAbertura(new Date());
        contaRepository.save(novaConta);
        return novaConta;
    }

    public String gerarDigitoConta() {
        Random random = new Random();
        int digitoConta;
        String digitoContaString;

        do {
            digitoConta = random.nextInt(90);
            digitoContaString = String.valueOf(digitoConta);
        } while (contaRepository.existsByDigitoConta(digitoContaString));

        return digitoContaString;

    }
    private String gerarNumeroConta() {
        int numeroContaInt = ThreadLocalRandom.current().nextInt(MIN_NUMERO_CONTA, MAX_NUMERO_CONTA + 1);
        return String.valueOf(numeroContaInt);
    }
    private boolean numeroEDigitoContaJaExistentes(String numeroConta,String digitoConta) {
        return contaRepository.existsByNumeroContaAndDigitoConta(numeroConta, digitoConta);
    }

    @Transactional
    private void gerarNumeroContaEDigitoUnicos(Conta conta){
        String digitoConta;
        String numeroConta;
        do{
            digitoConta = gerarDigitoConta();
            numeroConta = gerarNumeroConta();
        }while(numeroEDigitoContaJaExistentes(numeroConta, digitoConta));

        conta.setNumeroConta(numeroConta);
        conta.setDigitoConta(digitoConta);
    }

}