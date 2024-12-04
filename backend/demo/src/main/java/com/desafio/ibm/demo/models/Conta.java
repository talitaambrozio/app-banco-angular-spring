package com.desafio.ibm.demo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dataAbertura;
    @OneToMany(mappedBy = "conta", fetch = FetchType.LAZY)
    private List<Transacao> transacoes;

}
