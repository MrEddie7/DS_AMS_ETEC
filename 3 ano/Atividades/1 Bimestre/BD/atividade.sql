CREATE DATABASE barbearia;

USE barbearia;

CREATE TABLE Clientes (
    ID_clientes int PRIMARY KEY AUTO_INCREMENT,
    Nome_cliente varchar(55) NOT NULL,
    Telefone varchar(16),
    email varchar(55) UNIQUE NOT NULL,
    Data_cadastro datetime DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Funcionarios (
    ID_func int PRIMARY KEY AUTO_INCREMENT,
    Nome_func varchar(55) NOT NULL,
    CPF varchar(11) UNIQUE NOT NULL CHECK (CHAR_LENGTH(CPF) = 11)
);

CREATE TABLE Tipo_servico (
    ID_tipo int PRIMARY KEY AUTO_INCREMENT,
    Desc_serv varchar(55) UNIQUE NOT NULL
);

CREATE TABLE Servicos (
    ID_serv int PRIMARY KEY AUTO_INCREMENT,
    Nome_serv varchar(55) NOT NULL,
    ID_tipo int NOT NULL,
    Preco decimal(10,2) NOT NULL CHECK (Preco >= 0),
    FOREIGN KEY (ID_tipo) REFERENCES Tipo_servico(ID_tipo)
);

CREATE TABLE Agendamento (
    ID_agendamento int PRIMARY KEY AUTO_INCREMENT,
    ID_clientes int NOT NULL,
    ID_func int NOT NULL,
    ID_serv int NOT NULL,
    Data_agendamento datetime NOT NULL,
    Valor_pago decimal(10,2) CHECK (Preco >= 0),
    FOREIGN KEY (ID_clientes) REFERENCES Clientes(ID_clientes),
    FOREIGN KEY (ID_func) REFERENCES Funcionarios(ID_func),
    FOREIGN KEY (ID_serv) REFERENCES Servicos(ID_serv)
);