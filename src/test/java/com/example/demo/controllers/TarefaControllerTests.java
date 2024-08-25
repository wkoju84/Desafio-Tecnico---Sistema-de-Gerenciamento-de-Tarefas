package com.example.demo.controllers;

import com.example.demo.dtos.TarefaDTO;
import com.example.demo.services.TarefaService;
import com.example.demo.services.exceptions.BancoDeDadosException;
import com.example.demo.services.exceptions.EntidadeNaoEncontradaException;
import com.example.demo.tests.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(TarefaController.class)
public class TarefaControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TarefaService tarefaService;

    @Autowired
    private ObjectMapper objectMapper;

    private long idExistente;
    private long idInexistente;
    private long idDependente;

    private TarefaDTO dto;
    private List<TarefaDTO> list;

    @BeforeEach
    void setup() throws Exception{
        idExistente = 1;
        idInexistente = 999;
        idDependente = 2;

        dto = Factory.criarTarefaDTO();
        list = new ArrayList<>();

        // Mock do findAll
        when(tarefaService.buscarTodos()).thenReturn(list);

        //Mock do findById
        when(tarefaService.buscarPorId(idExistente)).thenReturn(dto);
        when(tarefaService.buscarPorId(idInexistente)).thenThrow(EntidadeNaoEncontradaException.class);

        //Mock do save
        when(tarefaService.inserir(any())).thenReturn(dto);

        //Mock do update
        when(tarefaService.atualizar(eq(idExistente), any())).thenReturn(dto);
        when(tarefaService.atualizar(eq(idInexistente), any())).thenThrow(EntidadeNaoEncontradaException.class);

        //Mock do delete
        doNothing().when(tarefaService).excluir(idExistente);

        //Mock do delete com Exception de entidade não encontrada
        doThrow(EntidadeNaoEncontradaException.class).when(tarefaService).excluir(idInexistente);

        //Mock do delete com Exception de violação de integridade
        doThrow(BancoDeDadosException.class).when(tarefaService).excluir(idDependente);
    }

    // Teste do método findAll
    @Test
    public void findAllDeveriaRetornarUmaLista() throws Exception {
        ResultActions resultado = mockMvc.perform(get("/tarefas").accept(MediaType.APPLICATION_JSON));
        resultado.andExpect(status().isOk());
    }

    // Teste do método findById
    @Test
    public void findByIdDeveriaRetornarUmDto() throws Exception{
        ResultActions resultado = mockMvc.perform(get("/tarefas/{id}", idExistente)
                .accept(MediaType.APPLICATION_JSON));
        resultado.andExpect(status().isOk());
//        resultado.andExpect(jsonPath("$.id").exists());
//        resultado.andExpect(jsonPath("$.nome").exists());// quando houver mais dados no bd
    }

    // Teste do método findById retornando uma Exceção
    @Test
    public void findByIdDeveriaRetornarUm404() throws Exception{
        ResultActions resultado = mockMvc.perform(get("/tarefas/{id}", idInexistente)
                .accept(MediaType.APPLICATION_JSON));
        resultado.andExpect(status().isNotFound());
    }

    // Teste do método save
    @Test
    public void insertDeveriaRetornarUm201() throws Exception{
        String jsonBody = objectMapper.writeValueAsString(dto);

        ResultActions resultado = mockMvc.perform(post("/tarefas")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        resultado.andExpect(status().isCreated());
//        resultado.andExpect(jsonPath("$.id").exists());
//        resultado.andExpect(jsonPath("$.nome").exists());//quando houver mais dados no bd
    }

    // Teste do método update
    @Test
    public void updateDeveriaRetornarUm200QuandoOIdExistir() throws Exception{
        String jsonBody = objectMapper.writeValueAsString(dto);

        ResultActions resultado = mockMvc.perform(put("/tarefas/{id}", idExistente)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        resultado.andExpect(status().isOk());
//        resultado.andExpect(jsonPath("$.id").exists());
//        resultado.andExpect(jsonPath("$.nome").exists());quando houver mais dados no bd
    }

    // Teste do método update com exceção
    @Test
    public void updateDeveriaRetornarUm404QuandoOIdNaoExistir() throws Exception{
        String jsonBody = objectMapper.writeValueAsString(dto);

        ResultActions resultado = mockMvc.perform(put("/tarefas/{id}", idInexistente)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        resultado.andExpect(status().isNotFound());
    }

    // Teste do método delete
    @Test
    public void deleteDeveriaRetornarUm204QuandoOIdExistir() throws Exception{
        ResultActions resultado = mockMvc.perform(delete("/tarefas/{id}", idExistente)
                .accept(MediaType.APPLICATION_JSON));
        resultado.andExpect(status().isNoContent());
    }

    // Teste do método delete com exceção de recurso não encontrado
    @Test
    public void deleteDeveriaRetornarUm404QuandoOIdNaoExistir() throws Exception{
        ResultActions resultado = mockMvc.perform(delete("/tarefas/{id}", idInexistente)
                .accept(MediaType.APPLICATION_JSON));
        resultado.andExpect(status().isNotFound());
    }

}
