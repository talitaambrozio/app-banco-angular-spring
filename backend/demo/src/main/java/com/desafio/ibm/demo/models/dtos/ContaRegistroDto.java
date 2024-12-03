package com.desafio.ibm.demo.models.dtos;

import jakarta.validation.constraints.NotBlank;

public record ContaRegistroDto(

        @NotBlank(message = "Agência não pode estar em branco")
         String agencia

) {
}
