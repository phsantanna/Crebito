package com.controle.de.concorrencia.crebito.Crebito.infra.exceptions;


public class SaldoMenorQueLimiteException extends RuntimeException {
    public SaldoMenorQueLimiteException(String message) {
        super(message);
    }
}
