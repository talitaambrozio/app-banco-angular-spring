package com.desafio.ibm.demo.models.dtos;

import com.desafio.ibm.demo.models.Conta;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record TransacaoRegistroDto(

        @NotBlank(message = "Não deve estar em branco")
        @Pattern(regexp = "^[DC]$", message = "Deve conter apenas uma letra: \"D\" para débito ou \"C\" para crédito")
        char tipoTransacao,
        @NotNull(message = "Não deve ser Nulo")
        Double valor,
        @NotBlank(message = "Não deve estar em branco")
        String descricao,
        @Valid
        Conta conta
) {
}
