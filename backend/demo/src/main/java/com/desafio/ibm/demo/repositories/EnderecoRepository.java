package com.desafio.ibm.demo.repositories;

import com.desafio.ibm.demo.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {

    Optional<Endereco> findByLogradouroAndBairroAndCidadeAndNumero(String logradouro, String bairro, String cidade, int numero);
}
