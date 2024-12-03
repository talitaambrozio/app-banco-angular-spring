package com.desafio.ibm.demo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transacao")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID transacaoId;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dataTransacao;
    private char tipoTransacao;
    private Double valor;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

}
