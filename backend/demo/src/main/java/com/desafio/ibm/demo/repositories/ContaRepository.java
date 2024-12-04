package com.desafio.ibm.demo.repositories;

import com.desafio.ibm.demo.models.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface ContaRepository extends JpaRepository<Conta, UUID> {

    boolean existsByDigitoConta(String digitoConta);

    boolean existsByNumeroContaAndDigitoConta(String numeroConta, String digitoConta);

    Optional<Conta> findByContaId(UUID uuid);
}
