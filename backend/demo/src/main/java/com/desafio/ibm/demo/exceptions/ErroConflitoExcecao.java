package com.desafio.ibm.demo.exceptions;

import com.desafio.ibm.demo.exceptions.dtos.CausaErroDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErroConflitoExcecao extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private String mensagem;
    private CausaErroDto causa;

    public ErroConflitoExcecao(String mensagem) {
        super(mensagem);
        this.mensagem = mensagem;
    }

    public ErroConflitoExcecao(String mensagem, CausaErroDto causa) {
        super();
        this.mensagem = mensagem;
        this.causa = causa;
    }
}
