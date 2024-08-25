package com.example.demo.dtos;

import com.example.demo.entities.Tarefa;

import java.io.Serial;
import java.io.Serializable;

public class TarefaDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private String descricao;

    private boolean realizado;

    private int prioridade;

    public TarefaDTO() {
    }

    public TarefaDTO(Long id, String nome, String descricao, boolean realizado, int prioridade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.realizado = realizado;
        this.prioridade = prioridade;
    }

    public TarefaDTO(Tarefa tarefa) {
        this.id = tarefa.getId();
        this.nome = tarefa.getNome();
        this.descricao = tarefa.getDescricao();
        this.realizado = tarefa.isRealizado();
        this.prioridade = tarefa.getPrioridade();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }
}
