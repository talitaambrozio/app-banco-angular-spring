package com.desafio.ibm.demo.repositories;

import com.desafio.ibm.demo.models.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, UUID> {
}
