package com.desafio.ibm.demo.models.dtos;

import com.desafio.ibm.demo.models.Transacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.util.Date;
import java.util.List;

public record ContaRegistroDto(

        @NotBlank(message = "Agência não pode estar em branco")
         String agencia

) {
}
