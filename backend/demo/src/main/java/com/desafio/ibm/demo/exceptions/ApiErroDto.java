package com.desafio.ibm.demo.exceptions;

import com.desafio.ibm.demo.exceptions.dtos.CausaErroDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErroDto {

    private String mensagem;
    private HttpStatus status;
    private List<CausaErroDto> causas;

    public ApiErroDto(String mensagem, HttpStatus status) {
        this.mensagem = mensagem;
        this.status = status;
    }

    public ApiErroDto(String mensagem, HttpStatus status, List<CausaErroDto> causas) {
        this.mensagem = mensagem;
        this.status = status;
        this.causas = causas;
    }
}
