CREATE DATABASE Biblioteca
use Biblioteca

CREATE TABLE Usuario (

ID_User int, PRIMARY KEY
Nome varchar(25),
Telefone varchar (15),
num_emp int,

);

CREATE TABLE Categoria (

ID_cat int, PRIMARY KEY
Nome varchar(20),
tipe_cat int,

);

CREATE TABLE tipo_cat (

tipe_cat int, PRIMARY KEY
desc_cat varchar (20),

)

CREATE TABLE Emprestimo(

ID_emp int, PRIMARY KEY
ID_User int,
ID_Livro int,
date_return date,
date_ret date,

)

CREATE TABLE Livros (

ID_Livro int, PRIMARY KEY,
Nome varchar(40),
ID_cat int,
ID_Autor int,
num_dis int,

)

CREATE TABLE Autores_livros (


    
)