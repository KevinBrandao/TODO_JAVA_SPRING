package com.tarefas.listatarefas.service;

import com.tarefas.listatarefas.model.entity.Tarefa;
import java.util.List;
import java.util.Optional;

public interface TarefaService {
    Tarefa salvarTarefa(Tarefa tarefa);
    Tarefa atualizarTarefa(Tarefa tarefa);
    void deletarTarefa(Tarefa tarefa);
    Optional<Tarefa> buscarPorId(Long id);
    List<Tarefa> buscarTodas();
}