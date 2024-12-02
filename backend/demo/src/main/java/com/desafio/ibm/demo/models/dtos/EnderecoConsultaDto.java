package com.desafio.ibm.demo.models.dtos;

import com.desafio.ibm.demo.models.Endereco;

import java.util.UUID;

public record EnderecoConsultaDto(
         UUID enderecoId,
         String logradouro,
         String bairro,
         int numero,
         String cidade,
         String cep,
         String estado,
         String pais
) {
    public EnderecoConsultaDto(Endereco endereco){
        this(endereco.getEnderecoId(),
                endereco.getLogradouro(),
                endereco.getBairro(),
                endereco.getNumero(),
                endereco.getCidade(),
                endereco.getCep(),
                endereco.getEstado(),
                endereco.getPais());
    }
}
