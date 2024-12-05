package com.desafio.ibm.demo.services;

import com.desafio.ibm.demo.enums.Role;
import com.desafio.ibm.demo.exceptions.ErroConflitoExcecao;
import com.desafio.ibm.demo.exceptions.RecursoNaoEncontradoExcecao;
import com.desafio.ibm.demo.models.Cliente;
import com.desafio.ibm.demo.models.Conta;
import com.desafio.ibm.demo.models.ModelRole;
import com.desafio.ibm.demo.models.ModelUserDetailsImpl;
import com.desafio.ibm.demo.models.dtos.ClienteAtualizaEmailDto;
import com.desafio.ibm.demo.models.dtos.ClienteConsultaDto;
import com.desafio.ibm.demo.models.dtos.ClienteRegistroDto;
import com.desafio.ibm.demo.models.dtos.DadosClienteLogadoDto;
import com.desafio.ibm.demo.models.dtos.seguranca.JwtTokenDTO;
import com.desafio.ibm.demo.models.dtos.seguranca.LoginUserDTO;
import com.desafio.ibm.demo.repositories.ClienteRepository;
import com.desafio.ibm.demo.repositories.ModelRoleRepository;
import com.desafio.ibm.demo.security.SecurityConfig;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {
    @Autowired
    private  ClienteRepository clienteRepository;
    @Autowired
    private  ContaService contaService;

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private ModelRoleRepository modelRoleRepository;

    @Transactional
    public ClienteConsultaDto salvarCliente(ClienteRegistroDto clienteRegistroDto){

        boolean clienteCadastrado = clienteRepository
                .findByEmail(clienteRegistroDto.email()).isPresent();
        if(clienteCadastrado){
            throw new ErroConflitoExcecao("Cliente já cadastrado.");
        }

        ModelRole role = modelRoleRepository.findByName(Role.ROLE_USER);
        Cliente novoCliente = new Cliente();
        novoCliente.setNome(clienteRegistroDto.nome());
        novoCliente.setIdade(clienteRegistroDto.idade());
        novoCliente.setEmail(clienteRegistroDto.email());
        novoCliente.setPassword(securityConfig.passwordEncoder().encode(clienteRegistroDto.password()));
        novoCliente.setRoles(List.of(role));

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

    public JwtTokenDTO autenticarUsuario(LoginUserDTO loginUserDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        ModelUserDetailsImpl modelUserDetails = (ModelUserDetailsImpl) authentication.getPrincipal();
        return new JwtTokenDTO(jwtTokenService.generateToken(modelUserDetails));
    }

    public DadosClienteLogadoDto obtemDadosClienteLogado(String  username){
        Cliente cliente =  clienteRepository
                .findByEmail(username)
                .orElseThrow(()-> new RecursoNaoEncontradoExcecao("Cliente não encontrado."));
        DadosClienteLogadoDto dto =
                new DadosClienteLogadoDto(cliente.getClienteId(),
                        cliente.getConta().getContaId());
        return dto;
    }
}
