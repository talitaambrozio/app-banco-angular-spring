package com.desafio.ibm.demo.services;

import com.desafio.ibm.demo.models.Cliente;
import com.desafio.ibm.demo.models.Conta;
import com.desafio.ibm.demo.models.Endereco;
import com.desafio.ibm.demo.models.dtos.ClienteConsultaDto;
import com.desafio.ibm.demo.models.dtos.ClienteRegistroDto;
import com.desafio.ibm.demo.repositories.ClienteRepository;
import com.desafio.ibm.demo.repositories.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
