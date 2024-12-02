package com.desafio.ibm.demo.models.dtos;

import com.desafio.ibm.demo.models.Conta;
import com.desafio.ibm.demo.models.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ClienteRegistroDto(

        @NotBlank(message = "Não deve estar em branco")
        @Pattern(regexp = "[a-zA-Z]+", message = "Deve conter apenas letras")
         String nome,
        @NotNull(message = "Não deve ser Nulo")
         Integer idade,
        @NotBlank(message = "Não deve estar em branco")
         String email,
        @Valid
        EnderecoRegistroDto enderecoDto,
        @Valid
        ContaRegistroDto contaRegistroDto

) {
}
