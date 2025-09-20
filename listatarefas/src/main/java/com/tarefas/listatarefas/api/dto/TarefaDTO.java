package com.tarefas.listatarefas.api.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class TarefaDTO {
    private Long id;
    private String nome;
    private String descricao;
    private String status;
    private String observacoes;
    private LocalDate dataCriacao;
    private LocalDate dataAtualizacao;
}