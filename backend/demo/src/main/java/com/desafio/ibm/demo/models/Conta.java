package com.desafio.ibm.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID contaId;
    private String numeroConta;
    private String digitoConta;
    private String agencia;
    private Date dataAbertura;

    @OneToMany(mappedBy = "conta")
    private List<Transacao> transacoes;




}
