-- SQL em mariadb do exercicio 3

-- Criação do banco de dados
CREATE DATABASE exerc3maria;

-- Uso do banco de dados
USE exerc3maria;

-- Tabela funcionario
CREATE TABLE funcionario (
  ID_Funcionario INT PRIMARY KEY NOT NULL,
  nome VARCHAR(15) NOT NULL,
  cargo VARCHAR(15) NOT NULL,
  departamento VARCHAR(15) NOT NULL,
  admissao DATETIME NOT NULL
);

-- Tabela fornecedor
CREATE TABLE fornecedor (
  ID_forn INT PRIMARY KEY NOT NULL,
  nome VARCHAR(10) NOT NULL,
  CNPJ VARCHAR(18) NOT NULL UNIQUE,
  telefone VARCHAR(12) NOT NULL,
  email VARCHAR(20) NOT NULL
);

-- Tabela departamento
CREATE TABLE departamento (
  ID_dep INT PRIMARY KEY NOT NULL,
  nome VARCHAR(10) NOT NULL,
  setor VARCHAR(17) NOT NULL,
  n_func INT NOT NULL
);

-- Tabela papelaria
CREATE TABLE papelaria (
  ID_produto INT PRIMARY KEY NOT NULL,
  tipo VARCHAR(10) NOT NULL,
  valor DOUBLE NOT NULL,
  marca VARCHAR(10) NOT NULL,
  detalhes VARCHAR(20) NOT NULL
);
