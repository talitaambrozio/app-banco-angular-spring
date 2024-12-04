package com.desafio.ibm.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID clienteId;
    private String nome;
    private Integer idade;
    private String email;
    @OneToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;
}
