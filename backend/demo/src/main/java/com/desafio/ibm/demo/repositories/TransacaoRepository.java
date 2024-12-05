package com.desafio.ibm.demo.repositories;

import com.desafio.ibm.demo.models.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, UUID> {

    List<Transacao> findByContaContaIdAndDataTransacaoBetween(UUID contaId, Date dataInicial, Date dataFinal);
    List<Transacao> findByContaContaId(UUID contaId);
}
