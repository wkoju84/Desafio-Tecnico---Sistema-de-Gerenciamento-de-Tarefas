package com.example.demo.tests;

import com.example.demo.dtos.TarefaDTO;
import com.example.demo.entities.Tarefa;

public class Factory {

    public static Tarefa criarTarefa(){
        return new Tarefa(null,"Entregar trabalho", "Trabalho escolar", false, 3);
    }

    public static TarefaDTO criarTarefaDTO(){
        Tarefa tarefa = criarTarefa();
        return new TarefaDTO(tarefa);
    }
}
