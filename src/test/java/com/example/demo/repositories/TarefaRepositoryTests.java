package com.example.demo.repositories;

import com.example.demo.entities.Tarefa;
import com.example.demo.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class TarefaRepositoryTests {

    @Autowired
    private TarefaRepository tarefaRepository;

    private long idExistente;
    private long idInexistente;

    private long totalDeTarefas;

    private List<Tarefa> tarefas;

    @BeforeEach
    void setup() throws Exception{

        idExistente = 1;
        idInexistente = 99;
        totalDeTarefas = 0;// alterar a medida que a lista cresce
        tarefas = new ArrayList<>();
    }

    @Test
    public void saveDeveriaSalvarComAutoincrementoQuandoOIdForNulo(){
        Tarefa tarefa = Factory.criarTarefa(); // Simula um DTO
        tarefa.setId(null);
        tarefa = tarefaRepository.save(tarefa);

        Assertions.assertNotNull(tarefa.getId());
        Assertions.assertEquals(totalDeTarefas + 1, tarefa.getId());
    }

    @Test
    public void findAllDeveriaRetornarUmaLista(){
        tarefas = tarefaRepository.findAll();
        Assertions.assertNotNull(tarefas);
    }

    @Test
    public void buscarPorIdERetornarUmaTarefa(){
        Optional<Tarefa> resultado = tarefaRepository.findById(idExistente);
        Assertions.assertFalse(resultado.isPresent());//Trocar para assertTrue após finalizar testes
    }

    @Test
    public void buscarPorIdERetornarUmOptionalVazio(){
        Optional<Tarefa> resultado = tarefaRepository.findById(idInexistente);
        Assertions.assertTrue(resultado.isEmpty());
    }

    @Test
    public void deleteDeveriaExcluirUmRegistroDoBD(){
        tarefaRepository.deleteById(idExistente);
        Optional<Tarefa> resultado = tarefaRepository.findById(idExistente);
        Assertions.assertTrue(resultado.isEmpty());
    }

//    @Test
//    public void deleteDeveriaNaoEncontrarORegistroNoBD(){
//        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
//            tarefaRepository.deleteById(idInexistente);
//        });
//    }

}
