package com.desafio.ibm.demo.models.dtos;

import jakarta.validation.constraints.NotBlank;

public record ClienteAtualizaEmailDto(
        @NotBlank(message = "Não deve estar em branco")
        String email) {

}
