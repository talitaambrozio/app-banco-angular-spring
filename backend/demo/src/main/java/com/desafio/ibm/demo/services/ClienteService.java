package com.desafio.ibm.demo.services;

import com.desafio.ibm.demo.exceptions.ErroConflitoExcecao;
import com.desafio.ibm.demo.exceptions.RecursoNaoEncontradoExcecao;
import com.desafio.ibm.demo.models.Cliente;
import com.desafio.ibm.demo.models.Conta;
import com.desafio.ibm.demo.models.dtos.ClienteAtualizaEmailDto;
import com.desafio.ibm.demo.models.dtos.ClienteConsultaDto;
import com.desafio.ibm.demo.models.dtos.ClienteRegistroDto;
import com.desafio.ibm.demo.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {
    @Autowired
    private  ClienteRepository clienteRepository;
    @Autowired
    private  ContaService contaService;

    @Transactional
    public ClienteConsultaDto salvarCliente(ClienteRegistroDto clienteRegistroDto){

        boolean clienteCadastrado = clienteRepository
                .findByEmail(clienteRegistroDto.email()).isPresent();
        if(clienteCadastrado){
            throw new ErroConflitoExcecao("Cliente já cadastrado.");
        }
        Cliente novoCliente = new Cliente();
        novoCliente.setNome(clienteRegistroDto.nome());
        novoCliente.setIdade(clienteRegistroDto.idade());
        novoCliente.setEmail(clienteRegistroDto.email());

        Conta novaConta = contaService.criarConta(clienteRegistroDto.contaRegistroDto());
        novoCliente.setConta(novaConta);
        clienteRepository.save(novoCliente);

        return new ClienteConsultaDto(novoCliente);
    }

    @Transactional
    public ClienteConsultaDto atualizarDadosCliente(UUID id, ClienteRegistroDto clienteRegistroDto){
        Cliente cliente = clienteRepository
                .findById(id).orElseThrow(() -> new RecursoNaoEncontradoExcecao("Cliente não encontrado."));

        cliente.setNome(clienteRegistroDto.nome());
        cliente.setIdade(clienteRegistroDto.idade());
        cliente.setEmail(clienteRegistroDto.email());

        clienteRepository.save(cliente);
        return new ClienteConsultaDto(cliente);
    }

    @Transactional
    public ClienteConsultaDto atualizarEmailCliente(ClienteAtualizaEmailDto clienteEmailDto,UUID id){
        Cliente cliente = clienteRepository
                .findById(id).orElseThrow(() -> new RecursoNaoEncontradoExcecao("Cliente não encontrado."));

        cliente.setEmail(clienteEmailDto.email());
        clienteRepository.save(cliente);
        return new ClienteConsultaDto(cliente);
    }

    public ClienteConsultaDto buscarClienteId(UUID id){
        Cliente cliente = clienteRepository
                .findById(id).orElseThrow(() -> new RecursoNaoEncontradoExcecao("Cliente não encontrado."));
        return new ClienteConsultaDto(cliente);
    }

    public List<ClienteConsultaDto> buscarClientes(){
        List<Cliente> clientes = clienteRepository.findAll();
        if(clientes.isEmpty()){
            throw new RecursoNaoEncontradoExcecao("Não há clientes cadastrados.");
        }
        return ClienteConsultaDto.converterParaListaDto(clientes);
    }

}
