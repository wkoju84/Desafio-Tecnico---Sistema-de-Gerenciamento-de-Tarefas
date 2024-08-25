package com.example.demo.controllers.exceptions;

import com.example.demo.services.exceptions.BancoDeDadosException;
import com.example.demo.services.exceptions.EntidadeNaoEncontradaException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ManipuladorDeExcecoes {
    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<ErroPadrao> entidadeNaoEncontrada(EntidadeNaoEncontradaException e,
                                                            HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErroPadrao erro = new ErroPadrao();
        erro.setTimestamp(Instant.now());
        erro.setStatus(status.value());
        erro.setErro("Registro não encontrado!");
        erro.setMsg(e.getMessage());
        erro.setCaminho(request.getRequestURI());
        return ResponseEntity.status(status).body(erro);
    }

    @ExceptionHandler(BancoDeDadosException.class)
    public ResponseEntity<ErroPadrao> violacaoDeIntegridade(BancoDeDadosException e,
                                                            HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErroPadrao erro = new ErroPadrao();
        erro.setTimestamp(Instant.now());
        erro.setStatus(status.value());
        erro.setErro("Violação de integridade (chave estrangeira)");
        erro.setMsg(e.getMessage());
        erro.setCaminho(request.getRequestURI());
        return ResponseEntity.status(status).body(erro);
    }
}
