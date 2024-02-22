package com.controle.de.concorrencia.crebito.Crebito.infra.exceptions;

public class ClienteNaoEncontradoException extends RuntimeException{
    public ClienteNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
