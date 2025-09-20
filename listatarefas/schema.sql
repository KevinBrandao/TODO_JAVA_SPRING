CREATE DATABASE tarefas;

CREATE SCHEMA listatarefas;

CREATE TABLE listatarefas.tarefa (
  id bigserial NOT NULL PRIMARY KEY,
  nome character varying(150),
  descricao character varying(255),
  status character varying(50),
  observacoes text,
  data_criacao date DEFAULT NOW(),
  data_atualizacao date
);