package com.desafio.ibm.demo.exceptions;

import com.desafio.ibm.demo.exceptions.dtos.CausaErroDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
public class RecursoNaoEncontradoExcecao extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    private String mensagem;
    private CausaErroDto causa;

    public RecursoNaoEncontradoExcecao(String mensagem) {
        super(mensagem);
        this.mensagem = mensagem;
    }

    public RecursoNaoEncontradoExcecao(String mensagem, CausaErroDto causa) {
        super();
        this.mensagem = mensagem;
        this.causa = causa;

    }
}
