package com.example.demo.services.exceptions;

import java.io.Serial;

public class EntidadeNaoEncontradaException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public EntidadeNaoEncontradaException(String msg){
        super(msg);
    }
}
