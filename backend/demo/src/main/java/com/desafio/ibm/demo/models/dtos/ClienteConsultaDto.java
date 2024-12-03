package com.desafio.ibm.demo.models.dtos;

import com.desafio.ibm.demo.models.Cliente;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public static List<ClienteConsultaDto> converterParaListaDto(List<Cliente> clientes){
        return clientes.stream()
                .map(ClienteConsultaDto::new)
                .collect(Collectors.toList());
    }
}
