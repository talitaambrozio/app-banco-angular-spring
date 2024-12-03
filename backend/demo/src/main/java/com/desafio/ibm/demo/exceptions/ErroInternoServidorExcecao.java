package com.desafio.ibm.demo.exceptions;

import com.desafio.ibm.demo.exceptions.dtos.CausaErroDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErroInternoServidorExcecao extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private String mensagem;
    private CausaErroDto causa;

    public ErroInternoServidorExcecao(String mensagem) {
        super(mensagem);
        this.mensagem = mensagem;
    }

    public ErroInternoServidorExcecao(String mensagem, CausaErroDto causa) {
        super();
        this.mensagem = mensagem;
        this.causa = causa;
    }
}
