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
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID enderecoId;
    private String logradouro;
    private String bairro;
    private int numero;
    private String cidade;
    private String cep;
    private String estado;
    private String pais;
}
