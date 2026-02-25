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


)