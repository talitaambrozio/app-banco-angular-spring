package com.desafio.ibm.demo.exceptions.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CausaErroDto {
    private String campo;
    private String valor;

    public CausaErroDto() {}

    public CausaErroDto(String campo, String valor) {
        this.campo = campo;
        this.valor = valor;
    }
}
