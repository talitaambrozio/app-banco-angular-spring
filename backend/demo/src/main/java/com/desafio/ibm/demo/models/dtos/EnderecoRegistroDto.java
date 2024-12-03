package com.desafio.ibm.demo.models.dtos;

import com.desafio.ibm.demo.models.Endereco;
import jakarta.validation.constraints.*;

import java.util.UUID;

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
        @Pattern(regexp = "^[a-zA-Z]{2}$", message = "Deve conter apenas duas letras")
         String estado,
        @NotBlank(message = "Não deve estar em branco")
        @Pattern(regexp = "[a-zA-Z]+", message = "Deve conter apenas letras")
         String pais
) {
    public Endereco converterParaEntidade( UUID id) {
        Endereco endereco = new Endereco();
        endereco.setEnderecoId(id);
        endereco.setLogradouro(this.logradouro());
        endereco.setBairro(this.bairro());
        endereco.setNumero(this.numero());
        endereco.setCidade(this.cidade());
        endereco.setCep(this.cep());
        endereco.setEstado(this.estado());
        endereco.setPais(this.pais());
        return endereco;
    }
}
