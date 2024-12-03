package com.desafio.ibm.demo.handler;

import com.desafio.ibm.demo.exceptions.*;
import com.desafio.ibm.demo.exceptions.dtos.CausaErroDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


@RestControllerAdvice
public class TratadorDeError{

    public ResponseEntity<ApiErroDto> verificarRetornoComOuSemCausa(CausaErroDto causa, String mensagem, HttpStatus status) {
        if (causa == null) {
            ApiErroDto apiError = new ApiErroDto(mensagem, status);
            return new ResponseEntity<>(apiError, new HttpHeaders(), status);
        }

        ApiErroDto apiError = new ApiErroDto(mensagem, status, List.of(causa));
        return new ResponseEntity<>(apiError, new HttpHeaders(), status);
    }

    public ResponseEntity<ApiErroDto> verificarRetornoComOuSemCausa(List<CausaErroDto> causas, String mensagem, HttpStatus status) {
        ApiErroDto apiError = new ApiErroDto(mensagem, status, causas);
        return new ResponseEntity<>(apiError, new HttpHeaders(), status);
    }


    @ExceptionHandler({ RequisicaoInvalidaExcecao.class })
    public ResponseEntity<ApiErroDto> requisicaoInvalidaExcecaoErro400(RequisicaoInvalidaExcecao ex) {
        return verificarRetornoComOuSemCausa(ex.getCausa(), ex.getMensagem(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ RecursoNaoEncontradoExcecao.class })
    public ResponseEntity<ApiErroDto> recursoNaoEncontradoExcecaoErro404(RecursoNaoEncontradoExcecao ex) {
        return verificarRetornoComOuSemCausa(ex.getCausa(), ex.getMensagem(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ ErroConflitoExcecao.class })
    public ResponseEntity<ApiErroDto> erroConflitoExcecaoErro409(ErroConflitoExcecao ex) {
        return verificarRetornoComOuSemCausa(ex.getCausa(), ex.getMensagem(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ ErroInternoServidorExcecao.class })
    public ResponseEntity<ApiErroDto> erroInternoServidorExcecaoErro500(ErroInternoServidorExcecao ex) {
        return verificarRetornoComOuSemCausa(ex.getCausa(), ex.getMensagem(), HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
