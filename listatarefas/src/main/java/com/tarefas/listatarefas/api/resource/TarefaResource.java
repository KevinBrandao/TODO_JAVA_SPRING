package com.tarefas.listatarefas.api.resource;

import com.tarefas.listatarefas.api.dto.TarefaDTO;
import com.tarefas.listatarefas.model.entity.Tarefa;
import com.tarefas.listatarefas.service.TarefaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/tarefas")
public class TarefaResource {

    private final TarefaService service;

    public TarefaResource(TarefaService service) {
        this.service = service;
    }
    
    @GetMapping
    public ResponseEntity<List<TarefaDTO>> buscarTodas() {
        List<Tarefa> tarefas = service.buscarTodas();
        List<TarefaDTO> dtos = tarefas.stream().map(this::converterParaDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<Tarefa> salvar(@RequestBody TarefaDTO dto) {
        Tarefa tarefa = converter(dto);
        tarefa = service.salvarTarefa(tarefa);
        return new ResponseEntity<>(tarefa, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> atualizar(@PathVariable("id") Long id, @RequestBody TarefaDTO dto) {
        return service.buscarPorId(id).map(entity -> {
            try {
                Tarefa tarefa = converter(dto);
                tarefa.setId(entity.getId());
                service.atualizarTarefa(tarefa);
                return ResponseEntity.ok(tarefa);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }).orElseGet(() -> new ResponseEntity<>("Tarefa não encontrada na base de dados.", HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
        return service.buscarPorId(id).map(entidade -> {
            service.deletarTarefa(entidade);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity<>("Tarefa não encontrada na base de dados.", HttpStatus.BAD_REQUEST));
    }

    private Tarefa converter(TarefaDTO dto) {
        return Tarefa.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .status(dto.getStatus())
                .observacoes(dto.getObservacoes())
                .build();
    }
    
    private TarefaDTO converterParaDTO(Tarefa tarefa) {
        return TarefaDTO.builder()
            .id(tarefa.getId())
            .nome(tarefa.getNome())
            .descricao(tarefa.getDescricao())
            .status(tarefa.getStatus())
            .observacoes(tarefa.getObservacoes())
            .dataCriacao(tarefa.getDataCriacao())
            .dataAtualizacao(tarefa.getDataAtualizacao())
            .build();
}
}