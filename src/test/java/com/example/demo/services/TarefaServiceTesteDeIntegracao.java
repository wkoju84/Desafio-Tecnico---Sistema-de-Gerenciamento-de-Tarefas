package com.example.demo.services;

import com.example.demo.dtos.TarefaDTO;
import com.example.demo.repositories.TarefaRepository;
import com.example.demo.services.exceptions.BancoDeDadosException;
import com.example.demo.services.exceptions.EntidadeNaoEncontradaException;
import com.example.demo.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class TarefaServiceTesteDeIntegracao {

    @Autowired
    private TarefaService tarefaService;

    @Autowired
    private TarefaRepository tarefaRepository;

    private long idExistente;
    private long idInexistente;
    private long idDependente;
    private TarefaDTO dto;

    @BeforeEach
    void setup() throws Exception{
        idExistente = 1;
        idInexistente = 999;
        idDependente = 3;
        dto = Factory.criarTarefaDTO();
    }

    // Teste do método findAll
    @Test
    public void findAllDeveriaTrazerUmaLista(){
        List<TarefaDTO> resultado = tarefaService.buscarTodos();
        Assertions.assertFalse(resultado.isEmpty());
    }

    // Teste do método findById
    @Test
    public void findByIdDeveriaTrazerUmRegistro(){
        TarefaDTO resultado = tarefaService.buscarPorId(idExistente);
        Assertions.assertNotNull(resultado);
    }

    // Teste do método findById retornando uma Exceção
    @Test
    public void findByIdDeveriaLancarUmaExcecao(){
        Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
            tarefaService.buscarPorId(idInexistente);
        });
    }

    // Teste do método save
    @Test
    public void saveDeveriaPersistirNoBancoDeDados(){
        TarefaDTO dto1 = dto;
        dto1.setId(null);
        dto1 = tarefaService.inserir(dto1);
        Assertions.assertNotNull(dto1.getId());
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
        Assertions.assertDoesNotThrow(() -> {
            tarefaService.excluir(idExistente);
        });
    }

//    // Teste do método delete com exceção de recurso não encontrado
//    @Test
//    public void deleteDeveriaLancarUmaExcecao(){
//        Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
//            tarefaService.excluir(idInexistente);
//        });
//    }
//
//   // Teste do método delete com exceção de integridade do BD
//   @Test
//   public void deleteDeveriaLancarUmaViolacaoDeIntegridade(){
//       Assertions.assertThrows(BancoDeDadosException.class, () -> {
//           tarefaService.excluir(idDependente);
//       });
//   }

}
