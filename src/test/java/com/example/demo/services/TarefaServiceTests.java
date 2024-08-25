package com.example.demo.services;

import com.example.demo.dtos.TarefaDTO;
import com.example.demo.entities.Tarefa;
import com.example.demo.repositories.TarefaRepository;
import com.example.demo.services.exceptions.BancoDeDadosException;
import com.example.demo.services.exceptions.EntidadeNaoEncontradaException;
import com.example.demo.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class TarefaServiceTests {

    @InjectMocks
    private TarefaService tarefaService;

    @Mock
    private TarefaRepository tarefaRepository;

    private long idExistente;
    private long idInexistente;
    private long idDependente;
    private Tarefa tarefa;
    private TarefaDTO dto;
    private List<Tarefa> list;

    @BeforeEach
    void setup() throws Exception{

        idExistente = 1;
        idInexistente = 999;
        idDependente = 2;

        tarefa = Factory.criarTarefa();
        dto = Factory.criarTarefaDTO();
        list = new ArrayList<>();

        //Mock do findAll
        Mockito.when(tarefaRepository.findAll((Sort) ArgumentMatchers.any())).thenReturn(list);

        //Mock do findById
        Mockito.when(tarefaRepository.findById(idExistente)).thenReturn(Optional.of(tarefa));
        Mockito.when(tarefaRepository.findById(idInexistente)).thenReturn(Optional.empty());

        //Mock do save
        Mockito.when(tarefaRepository.save(ArgumentMatchers.any())).thenReturn(tarefa);

        //Mock do getReferenceById
        Mockito.when(tarefaRepository.getReferenceById(idExistente)).thenReturn(tarefa);
        Mockito.when(tarefaRepository.getReferenceById(idInexistente)).thenThrow(EntidadeNaoEncontradaException.class);

        //Mock do delete
        Mockito.doNothing().when(tarefaRepository).deleteById(idExistente);
        Mockito.doThrow(EmptyResultDataAccessException.class).when(tarefaRepository).deleteById(idInexistente);
        Mockito.doThrow(DataIntegrityViolationException.class).when(tarefaRepository).deleteById(idDependente);
    }

    //Teste do método findAll
    @Test
    public void findAllDeveriaRetornarUmaLista(){
        List<TarefaDTO> resultado = tarefaService.buscarTodos();
        Assertions.assertNotNull(resultado);
    }

    //Teste do método findById
    @Test
    public void findByIdDeveriaRetornarUmRegistroDoBD(){
        TarefaDTO resultado = tarefaService.buscarPorId(idExistente);
        Assertions.assertNotNull(resultado);
    }

    // Teste do método findById retornando uma Exceção
    @Test
    public void findByIdDeveriaRetornarUmaExcecao(){
        Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
            tarefaService.buscarPorId(idInexistente);
        });
    }

    // Teste do método save
    @Test
    public void saveDeveriaPersistirNoBD(){
        TarefaDTO resultado = tarefaService.inserir(dto);
        Assertions.assertNotNull(resultado);
    }

    // Teste do método update
    @Test
    public void updateDeveriaAtualizarUmRegistro(){
        TarefaDTO resultado = tarefaService.atualizar(idExistente, dto);
        Assertions.assertNotNull(resultado);
    }

    // Teste do método update com exceção
    @Test
    public void updateDeveriaLancarUmaExcecao(){
        Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
            tarefaService.atualizar(idInexistente, dto);
        });
    }

    // Teste do método delete
    @Test
    public void deleteDeveriaExcluirUmRegistro(){
        Assertions.assertDoesNotThrow(() -> tarefaService.excluir(idExistente));
        Mockito.verify(tarefaRepository, Mockito.times(1)).deleteById(idExistente);
    }

    // Teste do método delete com exceção de recurso não encontrado
    @Test
    public void deleteDeveriaLancarExcecao(){
        Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> tarefaService.excluir(idInexistente));
        Mockito.verify(tarefaRepository, Mockito.times(1)).deleteById(idInexistente);
    }

    // Teste do método delete com exceção de integridade do BD
    @Test
    public void deleteDeveriaLancarExcecaoDeIntegridadeDeBD(){
        Assertions.assertThrows(BancoDeDadosException.class, () -> tarefaService.excluir(idDependente));
        Mockito.verify(tarefaRepository, Mockito.times(1)).deleteById(idDependente);
    }


}
