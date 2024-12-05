package com.desafio.ibm.demo.services;

import com.desafio.ibm.demo.models.Cliente;
import com.desafio.ibm.demo.models.ModelUserDetailsImpl;
import com.desafio.ibm.demo.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        return new ModelUserDetailsImpl(cliente);
    }
}