package com.controle.de.concorrencia.crebito.Crebito.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiResourceAdviceCliente {
    @ExceptionHandler(ClienteNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleClienteNaoEncontradoException(ClienteNaoEncontradoException exception){
        return new ApiError(exception.getMessage());
    }
}
