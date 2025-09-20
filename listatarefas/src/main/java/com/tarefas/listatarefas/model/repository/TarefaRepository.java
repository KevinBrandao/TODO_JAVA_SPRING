package com.tarefas.listatarefas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tarefas.listatarefas.model.entity.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}