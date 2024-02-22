package com.controle.de.concorrencia.crebito.Crebito.infra.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppResourceAdviceSaldo {
    @ExceptionHandler(SaldoMenorQueLimiteException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ApiError handleTransacaoException(SaldoMenorQueLimiteException exception){
        return new ApiError(exception.getMessage());
    }

}
