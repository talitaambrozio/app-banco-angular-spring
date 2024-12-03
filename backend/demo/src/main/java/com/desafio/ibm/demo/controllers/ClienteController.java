package com.desafio.ibm.demo.controllers;

import com.desafio.ibm.demo.models.dtos.ClienteAtualizaEmailDto;
import com.desafio.ibm.demo.models.dtos.ClienteConsultaDto;
import com.desafio.ibm.demo.models.dtos.ClienteRegistroDto;
import com.desafio.ibm.demo.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/salvar-cliente/")
    public ResponseEntity<ClienteConsultaDto> salvarCliente(@RequestBody ClienteRegistroDto dto){
        return new ResponseEntity<ClienteConsultaDto>(clienteService.salvarCliente(dto),
                HttpStatus.CREATED);
    }

    @PutMapping("/atualizar-cliente/{id}")
    public ResponseEntity<ClienteConsultaDto> atualizarDadosCliente(@PathVariable UUID id,
                                                                    @Valid @RequestBody ClienteRegistroDto dto){
        return new ResponseEntity<ClienteConsultaDto>(clienteService.atualizarDadosCliente(id, dto),
                HttpStatus.OK);
    }

    @PatchMapping("/atualizar-email-cliente/{id}")
    public ResponseEntity<ClienteConsultaDto> atualizarEmailCliente(@PathVariable UUID id,
                                                                    @Valid @RequestBody ClienteAtualizaEmailDto dto){
        return new ResponseEntity<ClienteConsultaDto>(clienteService.atualizarEmailCliente(dto, id),
                HttpStatus.OK);
    }

    @GetMapping("/buscar-cliente-id/{id}")
    public ResponseEntity<ClienteConsultaDto> buscarCliente(@PathVariable UUID id){
        return new ResponseEntity<ClienteConsultaDto>(clienteService.buscarClienteId(id),
                HttpStatus.OK);
    }
}
