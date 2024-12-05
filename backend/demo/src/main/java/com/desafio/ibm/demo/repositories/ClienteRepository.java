package com.desafio.ibm.demo.repositories;

import com.desafio.ibm.demo.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    Optional<Cliente> findByEmail(String email);

}
