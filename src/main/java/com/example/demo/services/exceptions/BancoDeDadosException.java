package com.example.demo.services.exceptions;

import java.io.Serial;

public class BancoDeDadosException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public BancoDeDadosException(String msg){
        super(msg);
    }
}
