package com.desafio.ibm.demo.controllers;

import com.desafio.ibm.demo.models.dtos.ClienteConsultaDto;
import com.desafio.ibm.demo.models.dtos.ClienteRegistroDto;
import com.desafio.ibm.demo.models.dtos.seguranca.JwtTokenDTO;
import com.desafio.ibm.demo.models.dtos.seguranca.LoginUserDTO;
import com.desafio.ibm.demo.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1/users")
public class LoginController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/salvar-cliente")
    public ResponseEntity<ClienteConsultaDto> salvarCliente(@RequestBody ClienteRegistroDto dto){
        return new ResponseEntity<ClienteConsultaDto>(clienteService.salvarCliente(dto),
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDTO> loginUsuario(@RequestBody LoginUserDTO loginUserDto) {
        JwtTokenDTO token = clienteService.autenticarUsuario(loginUserDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
