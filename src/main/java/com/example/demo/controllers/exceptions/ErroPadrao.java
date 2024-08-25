package com.example.demo.controllers.exceptions;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

public class ErroPadrao implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Instant timestamp;
    private Integer status;
    private String erro;
    private String msg;
    private String caminho;

    public ErroPadrao() {
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }
}
