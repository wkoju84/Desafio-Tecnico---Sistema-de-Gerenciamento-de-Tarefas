package com.example.demo.services;

import com.example.demo.dtos.TarefaDTO;
import com.example.demo.entities.Tarefa;
import com.example.demo.repositories.TarefaRepository;
import com.example.demo.services.exceptions.BancoDeDadosException;
import com.example.demo.services.exceptions.EntidadeNaoEncontradaException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    private TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    @Transactional(readOnly = true)
    public List<TarefaDTO> buscarTodos() {
        List<Tarefa> tarefas = tarefaRepository.findAll();
        return tarefas.stream().sorted(Comparator.comparing(Tarefa::getPrioridade)
                        .reversed() //Ordena as tarefas pela prioridade em ordem decrescente
                        .thenComparing(Tarefa::getNome))//Ordena as tarefas por nome em ordem crescente
                .map(TarefaDTO::new)//Converte as entidades
                .collect(Collectors.toList());//Coleta os resultados em uma lista
    }

    @Transactional(readOnly = true)
    public TarefaDTO buscarPorId(Long id){
        Optional<Tarefa> objeto = tarefaRepository.findById(id);
        Tarefa tarefa = objeto.orElseThrow(() -> new EntidadeNaoEncontradaException(
                "Registro " + id + " não encontrado em sua base de dados!"
        ));
        return new TarefaDTO(tarefa);
    }

    public void excluir(Long id){
        try {
            tarefaRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(
                    "Exclusão impossível: Registro " + id + " não encontrado em sua base de dados!"
            );
        }
        catch (DataIntegrityViolationException e){
            throw new BancoDeDadosException(
                    "Violação de integridade: Registro " + id + " está inserido em outro registro!"
            );
        }
    }

    @Transactional
    public TarefaDTO inserir(TarefaDTO dto){
        Tarefa tarefa = new Tarefa();
        copiarDtoParaEntidade(dto, tarefa);
        tarefa = tarefaRepository.save(tarefa);
        return new TarefaDTO(tarefa);
    }

    @Transactional
    public TarefaDTO atualizar(Long id, TarefaDTO dto){
        try {
            Tarefa tarefa = tarefaRepository.getReferenceById(id);
            copiarDtoParaEntidade(dto, tarefa);
            tarefa = tarefaRepository.save(tarefa);
            return new TarefaDTO(tarefa);
        }
        catch (EntityNotFoundException e){
            throw new EntidadeNaoEncontradaException(
                    "Registro " + id + " não encontrado em sua base de dados!"
            );
        }
    }

    private void copiarDtoParaEntidade(TarefaDTO dto, Tarefa tarefa){
        tarefa.setNome(dto.getNome());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setRealizado(dto.isRealizado());
        tarefa.setPrioridade(dto.getPrioridade());
    }

}