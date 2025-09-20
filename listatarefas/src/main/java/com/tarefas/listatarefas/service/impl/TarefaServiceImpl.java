package com.tarefas.listatarefas.service.impl;

import com.tarefas.listatarefas.model.entity.Tarefa;
import com.tarefas.listatarefas.model.repository.TarefaRepository;
import com.tarefas.listatarefas.service.TarefaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TarefaServiceImpl implements TarefaService {

    private final TarefaRepository repository;

    public TarefaServiceImpl(TarefaRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Tarefa salvarTarefa(Tarefa tarefa) {
        tarefa.setDataCriacao(LocalDate.now());
        return repository.save(tarefa);
    }

    @Override
    @Transactional
    public Tarefa atualizarTarefa(Tarefa tarefa) {
        Objects.requireNonNull(tarefa.getId());
        tarefa.setDataAtualizacao(LocalDate.now());
        return repository.save(tarefa);
    }

    @Override
    @Transactional
    public void deletarTarefa(Tarefa tarefa) {
        Objects.requireNonNull(tarefa.getId());
        repository.delete(tarefa);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Tarefa> buscarPorId(Long id) {
        return repository.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Tarefa> buscarTodas() {
        return repository.findAll();
    }
}