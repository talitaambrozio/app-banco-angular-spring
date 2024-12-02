package com.desafio.ibm.demo.repositories;

import com.desafio.ibm.demo.models.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface ContaRepository extends JpaRepository<Conta, UUID> {

    Optional<Conta> findByNumeroContaAndDigitoConta(String numeroConta, String digitoConta);

    boolean existsByNumeroConta(String numeroConta);

    boolean existsByDigitoConta(String digitoConta);

    boolean existsByNumeroContaAndDigitoConta(String numeroConta, String digitoConta);
}
