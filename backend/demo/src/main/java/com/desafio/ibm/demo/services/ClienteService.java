package com.desafio.ibm.demo.services;

import com.desafio.ibm.demo.models.Cliente;
import com.desafio.ibm.demo.models.Conta;
import com.desafio.ibm.demo.models.Endereco;
import com.desafio.ibm.demo.models.dtos.ClienteAtualizaEmailDto;
import com.desafio.ibm.demo.models.dtos.ClienteConsultaDto;
import com.desafio.ibm.demo.models.dtos.ClienteRegistroDto;
import com.desafio.ibm.demo.repositories.ClienteRepository;
import com.desafio.ibm.demo.repositories.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.NotFound;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;

    private final ContaService contaService;

    public ClienteService(ClienteRepository clienteRepository, EnderecoRepository enderecoRepository, ContaService contaService) {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.contaService = contaService;
    }

    @Transactional
    public ClienteConsultaDto salvarCliente(ClienteRegistroDto clienteRegistroDto){

        boolean clienteCadastrado = clienteRepository
                .findByEmail(clienteRegistroDto.email()).isPresent();
        Cliente novoCliente = null;
        if(!clienteCadastrado){
            novoCliente = new Cliente();
            novoCliente.setNome(clienteRegistroDto.nome());
            novoCliente.setIdade(clienteRegistroDto.idade());
            novoCliente.setEmail(clienteRegistroDto.email());

            Endereco novoEndereco = getEndereco(clienteRegistroDto);
            enderecoRepository.save(novoEndereco);

            novoCliente.setEndereco(novoEndereco);

            Conta novaConta = contaService.criarConta(clienteRegistroDto.contaRegistroDto());
            novoCliente.setConta(novaConta);
            novoCliente.setEndereco(novoEndereco);
            clienteRepository.save(novoCliente);

        }
        return new ClienteConsultaDto(novoCliente);
    }

    @Transactional
    public ClienteConsultaDto atualizarDadosCliente(UUID id, ClienteRegistroDto clienteRegistroDto){
        Cliente cliente = clienteRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado."));

        cliente.setNome(clienteRegistroDto.nome());
        cliente.setIdade(clienteRegistroDto.idade());
        cliente.setEmail(clienteRegistroDto.email());

        Endereco enderecoAtual = cliente.getEndereco();
        Endereco enderecoAtualizado = clienteRegistroDto.enderecoDto().converterParaEntidade(id);

        if (enderecoAtual != null) {
            enderecoAtual.setLogradouro(enderecoAtualizado.getLogradouro());
            enderecoAtual.setBairro(enderecoAtualizado.getBairro());
            enderecoAtual.setNumero(enderecoAtualizado.getNumero());
            enderecoAtual.setCidade(enderecoAtualizado.getCidade());
            enderecoAtual.setCep(enderecoAtualizado.getCep());
            enderecoAtual.setEstado(enderecoAtualizado.getEstado());
            enderecoAtual.setPais(enderecoAtualizado.getPais());
        } else {
            cliente.setEndereco(enderecoAtualizado);
        }

        clienteRepository.save(cliente);
        return new ClienteConsultaDto(cliente);
    }

    @Transactional
    public ClienteConsultaDto atualizarEmailCliente(ClienteAtualizaEmailDto clienteEmailDto,UUID id){
        Cliente cliente = clienteRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado."));

        cliente.setEmail(clienteEmailDto.email());
        clienteRepository.save(cliente);
        return new ClienteConsultaDto(cliente);
    }

    public ClienteConsultaDto buscarClienteId(UUID id){
        Cliente cliente = clienteRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado."));
        return new ClienteConsultaDto(cliente);
    }
    private static Endereco getEndereco(ClienteRegistroDto clienteRegistroDto) {
        Endereco novoEndereco = new Endereco();
        novoEndereco.setLogradouro(clienteRegistroDto.enderecoDto().logradouro());
        novoEndereco.setBairro(clienteRegistroDto.enderecoDto().bairro());
        novoEndereco.setNumero(clienteRegistroDto.enderecoDto().numero());
        novoEndereco.setCidade(clienteRegistroDto.enderecoDto().cidade());
        novoEndereco.setCep(clienteRegistroDto.enderecoDto().cep());
        novoEndereco.setEstado(clienteRegistroDto.enderecoDto().estado());
        novoEndereco.setPais(clienteRegistroDto.enderecoDto().pais());
        return novoEndereco;
    }
}
