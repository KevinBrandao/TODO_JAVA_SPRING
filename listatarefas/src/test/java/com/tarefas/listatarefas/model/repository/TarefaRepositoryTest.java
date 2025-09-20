package com.tarefas.listatarefas.model.repository;

import com.tarefas.listatarefas.model.entity.Tarefa;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class TarefaRepositoryTest {

    @Autowired
    TarefaRepository repository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void devePersistirUmaTarefaNaBaseDeDados() {
        // cenário
        Tarefa tarefa = criarTarefa();

        // ação
        Tarefa tarefaSalva = repository.save(tarefa);

        // verificação
        assertThat(tarefaSalva.getId()).isNotNull();
    }

    @Test
    public void deveBuscarUmaTarefaPorId() {
        // cenário
        Tarefa tarefa = criarTarefa();
        entityManager.persist(tarefa);

        // ação
        Optional<Tarefa> result = repository.findById(tarefa.getId());

        // verificação
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    public void deveDeletarUmaTarefa() {
        // cenário
        Tarefa tarefa = criarTarefa();
        entityManager.persist(tarefa);
        Tarefa tarefaEncontrada = entityManager.find(Tarefa.class, tarefa.getId());

        // ação
        repository.delete(tarefaEncontrada);
        Tarefa tarefaDeletada = entityManager.find(Tarefa.class, tarefa.getId());

        // verificação
        assertThat(tarefaDeletada).isNull();
    }

    public static Tarefa criarTarefa() {
        return Tarefa.builder()
                .nome("Estudar Spring")
                .descricao("Ver testes unitários")
                .status("PENDENTE")
                .observacoes("Revisar anotações")
                .dataCriacao(LocalDate.now())
                .build();
    }
}