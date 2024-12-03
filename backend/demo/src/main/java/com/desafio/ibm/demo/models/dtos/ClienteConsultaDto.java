package com.desafio.ibm.demo.models.dtos;

import com.desafio.ibm.demo.models.Cliente;

import java.util.UUID;

public record ClienteConsultaDto(

        UUID clienteId,
        String nome,
        Integer idade,
        String email,
        String numeroConta,
        String logradouro,
        String bairro,
        int numero,
        String cidade,
        String cep,
        String estado,
        String pais
) {
    public ClienteConsultaDto(Cliente cliente){
        this(cliente.getClienteId(),
                cliente.getNome(),
                cliente.getIdade(),
                cliente.getEmail(),
                cliente.getConta().getNumeroConta(),
                cliente.getEndereco().getLogradouro(),
                cliente.getEndereco().getBairro(),
                cliente.getEndereco().getNumero(),
                cliente.getEndereco().getCidade(),
                cliente.getEndereco().getCep(),
                cliente.getEndereco().getEstado(),
                cliente.getEndereco().getPais());
    }
}
