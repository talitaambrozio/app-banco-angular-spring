package com.desafio.ibm.demo.models.dtos;

import com.desafio.ibm.demo.models.Cliente;
import com.desafio.ibm.demo.models.Conta;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record ClienteConsultaDto(

        UUID clienteId,
        String nome,
        Integer idade,
        String email,
        Conta conta
        ) {
    public ClienteConsultaDto(Cliente cliente){
        this(cliente.getClienteId(),
                cliente.getNome(),
                cliente.getIdade(),
                cliente.getEmail(),
                cliente.getConta());
    }
    public static List<ClienteConsultaDto> converterParaListaDto(List<Cliente> clientes){
        return clientes.stream()
                .map(ClienteConsultaDto::new)
                .collect(Collectors.toList());
    }
}
