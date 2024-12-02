package com.desafio.ibm.demo.models.dtos;

import jakarta.validation.constraints.*;
public record EnderecoRegistroDto(

        @NotBlank(message = "Não deve estar em branco")
         String logradouro,
        @NotBlank(message = "Não deve estar em branco")
         String bairro,
        @NotNull(message = "Não deve ser Nulo")
        int numero,
        @NotBlank(message = "Não deve estar em branco")
        @Pattern(regexp = "[a-zA-Z]+", message = "Deve conter apenas letras")
         String cidade,
        @NotNull(message = "Não deve ser Nulo")
        @Pattern(regexp = "[0-9]{5}-[0-9]{3}", message = "Formato de CEP inválido. Use o formato XXXXX-XXX")
         String cep,
        @NotBlank(message = "Não deve estar em branco")
        @Pattern(regexp = "[a-zA-Z]+", message = "Deve conter apenas letras")
         String estado,
        @NotBlank(message = "Não deve estar em branco")
        @Pattern(regexp = "[a-zA-Z]+", message = "Deve conter apenas letras")
         String pais
) {

}
