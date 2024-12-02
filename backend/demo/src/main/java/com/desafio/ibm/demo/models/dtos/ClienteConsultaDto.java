package com.desafio.ibm.demo.models.dtos;

import com.desafio.ibm.demo.models.Cliente;

public record ClienteConsultaDto(

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
        this(cliente.getNome(),
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
