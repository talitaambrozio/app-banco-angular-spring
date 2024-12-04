package com.desafio.ibm.demo.controllers;

import com.desafio.ibm.demo.models.dtos.ClienteAtualizaEmailDto;
import com.desafio.ibm.demo.models.dtos.ClienteConsultaDto;
import com.desafio.ibm.demo.models.dtos.ClienteRegistroDto;
import com.desafio.ibm.demo.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("api/v1/cliente")
public class ClienteController {

    @Autowired
    private  ClienteService clienteService;

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

    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteConsultaDto>> buscarClientes(){
        return new ResponseEntity<List<ClienteConsultaDto>>(clienteService.buscarClientes(),
                HttpStatus.OK);
    }
}
