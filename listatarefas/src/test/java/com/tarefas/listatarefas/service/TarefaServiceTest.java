package com.tarefas.listatarefas.service;

import com.tarefas.listatarefas.model.entity.Tarefa;
import com.tarefas.listatarefas.model.repository.TarefaRepository;
import com.tarefas.listatarefas.model.repository.TarefaRepositoryTest;
import com.tarefas.listatarefas.service.impl.TarefaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TarefaServiceTest {

    TarefaService service;
    TarefaRepository repository;

    @BeforeEach
    public void setUp() {
        repository = Mockito.mock(TarefaRepository.class);
        service = new TarefaServiceImpl(repository);
    }

    @Test
    public void deveSalvarUmaTarefa() {
        // cenário
        Tarefa tarefa = TarefaRepositoryTest.criarTarefa();
        when(repository.save(tarefa)).thenReturn(tarefa);

        // ação
        Tarefa tarefaSalva = service.salvarTarefa(tarefa);

        // verificação
        assertThat(tarefaSalva).isNotNull();
        Mockito.verify(repository, Mockito.times(1)).save(tarefa);
    }

    @Test
    public void deveAtualizarUmaTarefa() {
        // cenário
        Tarefa tarefa = TarefaRepositoryTest.criarTarefa();
        tarefa.setId(1L);
        when(repository.save(tarefa)).thenReturn(tarefa);

        // ação
        Tarefa tarefaAtualizada = service.atualizarTarefa(tarefa);

        // verificação
        assertThat(tarefaAtualizada).isNotNull();
        Mockito.verify(repository, Mockito.times(1)).save(tarefa);
    }

    @Test
    public void deveDeletarUmaTarefa() {
        // Cenário
        Tarefa tarefa = TarefaRepositoryTest.criarTarefa();
        tarefa.setId(1L);

        // Ação
        service.deletarTarefa(tarefa);

        // Verificação
        Mockito.verify(repository, Mockito.times(1)).delete(tarefa);
    }

    @Test
    public void deveBuscarTarefaPorId() {
        // Cenário
        Tarefa tarefa = TarefaRepositoryTest.criarTarefa();
        tarefa.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(tarefa));

        // Ação
        Optional<Tarefa> resultado = service.buscarPorId(1L);

        // Verificação
        assertThat(resultado.isPresent()).isTrue();
    }
}