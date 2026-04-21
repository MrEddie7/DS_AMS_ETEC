CREATE DATABASE Biblioteca;
USE Biblioteca;

CREATE TABLE Usuario (
    ID_User INT PRIMARY KEY,
    Nome VARCHAR(25),
    Telefone VARCHAR(15),
    num_emp INT
);

CREATE TABLE tipo_cat (
    tipe_cat INT PRIMARY KEY,
    desc_cat VARCHAR(20)
);

CREATE TABLE Categoria (
    ID_cat INT PRIMARY KEY,
    Nome VARCHAR(20),
    tipe_cat INT,
    FOREIGN KEY (tipe_cat) REFERENCES tipo_cat(tipe_cat)
);

CREATE TABLE Autor (
    ID_Autor INT PRIMARY KEY,
    nome VARCHAR(25),
    Telefone VARCHAR(15)
);

CREATE TABLE Livros (
    ID_Livro INT PRIMARY KEY,
    Nome VARCHAR(40),
    ID_cat INT,
    num_dis INT,
    FOREIGN KEY (ID_cat) REFERENCES Categoria(ID_cat)
);

CREATE TABLE Autores_livros (
    ID_Autor INT,
    ID_Livro INT,
    PRIMARY KEY (ID_Autor, ID_Livro),
    FOREIGN KEY (ID_Autor) REFERENCES Autor(ID_Autor),
    FOREIGN KEY (ID_Livro) REFERENCES Livros(ID_Livro)
);

CREATE TABLE Emprestimo (
    ID_emp INT PRIMARY KEY,
    ID_User INT,
    ID_Livro INT,
    date_return DATE,
    date_ret DATE,
    FOREIGN KEY (ID_User) REFERENCES Usuario(ID_User),
    FOREIGN KEY (ID_Livro) REFERENCES Livros(ID_Livro)
);