-- criando um BD
create database ExercBD1
create database ExercBD2
create database ExercBD3

-- abrindo os BDs 
use ExercBD1
use ExercBD2
use ExercBD3
-- Exercico BD 1 

drop table pet
create table pet
(

NumRegistro int primary key Identity,
Nome varchar (80),
Especie varchar (25),
Raca varchar (50),
Nascimento date,
Sexo varchar (9)
);

-- inserindo dados

INSERT INTO pet (Nome,Especie,Raca,Nascimento,Sexo)
VALUES ('cleyton','gato','vira-lata','07/08/2000','masculino');

INSERT INTO pet (Nome,Especie,Raca,Nascimento,Sexo)
VALUES ('cleber','cachorro','caramelo','07/10/2000','masculino');

INSERT INTO pet (Nome,Especie,Raca,Nascimento,Sexo)
VALUES ('mimo','gato','vira-lata','07/08/2015','masculino');

INSERT INTO pet (Nome,Especie,Raca,Nascimento,Sexo)
VALUES ('Robert','gato','europeu','07/06/2009','masculino');

INSERT INTO pet (Nome,Especie,Raca,Nascimento,Sexo)
VALUES ('clinton','peixe','dourado','12/08/2021','masculino');

INSERT INTO pet (Nome,Especie,Raca,Nascimento,Sexo)
VALUES ('Carlos','gato','europeu','07/08/2000','masculino');

INSERT INTO pet (Nome,Especie,Raca,Nascimento,Sexo)
VALUES ('cleyton','piriquito','albino','07/08/2018','masculino');

INSERT INTO pet (Nome,Especie,Raca,Nascimento,Sexo)
VALUES ('jurema','gato','vira-lata','07/08/2000','feminino');

INSERT INTO pet (Nome,Especie,Raca,Nascimento,Sexo)
VALUES ('Rogerio','Cachorro','albino','07/08/2000','masculino');

INSERT INTO pet (Nome,Especie,Raca,Nascimento,Sexo)
VALUES ('Irene','gato','vira-lata','22/08/2010','feminino');


SELECT * from pet

-- Exercicio BD 2

drop table Departamento
create table Departamento(
CodDep int primary key identity,
CodFunc int ,
NomeDep varchar (50)
)

-- inserindo dados

INSERT INTO Departamento(CodFunc,NomeDep)
VALUES (01,'Relaçoes publicas');


INSERT INTO Departamento(CodFunc,NomeDep)
VALUES (02,'recursos Humanos');


INSERT INTO Departamento(CodFunc,NomeDep)
VALUES (03,'Segurança');


INSERT INTO Departamento(CodFunc,NomeDep)
VALUES (04,'Financeiro');


INSERT INTO Departamento(CodFunc,NomeDep)
VALUES (05,'Transporte');




create table funcionario (

CodFunc int primary key not null,
NomeFunc varchar (50) not null,
CodDep int not null,
Ramal int,
Salario float,
DataAd datetime,
DataCad datetime,
Sexo varchar (9) 
)

INSERT INTO funcionario (CodFunc,NomeFunc,CodDep,Ramal,Salario,DataAd,DataCad,Sexo)
VALUES (01,'cleyton',01,315,5.000,07/12/2000,07/12/2000,'masculino');

INSERT INTO funcionario (CodFunc,NomeFunc,CodDep,Ramal,Salario,DataAd,DataCad,Sexo)
VALUES (02,'Simone',01,316,6.000,17/12/2010,17/12/2010,'feminino');

INSERT INTO funcionario (CodFunc,NomeFunc,CodDep,Ramal,Salario,DataAd,DataCad,Sexo)
VALUES (03,'Roger',02,320,18.000,09/02/2010,09/02/2010,'masculino');

INSERT INTO funcionario (CodFunc,NomeFunc,CodDep,Ramal,Salario,DataAd,DataCad,Sexo)
VALUES (04,'Yago',01,318,10.000,07/12/2020,07/12/2020,'masculino');

INSERT INTO funcionario (CodFunc,NomeFunc,CodDep,Ramal,Salario,DataAd,DataCad,Sexo)
VALUES (05,'Jurema',01,355,5.000,07/12/2020,07/12/2020,'feminino');

INSERT INTO funcionario (CodFunc,NomeFunc,CodDep,Ramal,Salario,DataAd,DataCad,Sexo)
VALUES (06,'Antonio',05,345,2.300,07/02/2020,07/02/2020,'masculino');

INSERT INTO funcionario (CodFunc,NomeFunc,CodDep,Ramal,Salario,DataAd,DataCad,Sexo)
VALUES (07,'Carlos',06,365,22.000,17/08/2022,17/08/2022,'masculino');

INSERT INTO funcionario (CodFunc,NomeFunc,CodDep,Ramal,Salario,DataAd,DataCad,Sexo)
VALUES (08,'Cesar',01,395,5.000,20/12/2023,20/12/2023,'masculino');

INSERT INTO funcionario (CodFunc,NomeFunc,CodDep,Ramal,Salario,DataAd,DataCad,Sexo)
VALUES (09,'Rafael',06,348,15.000,30/12/2019,30/12/2019,'masculino');

INSERT INTO funcionario (CodFunc,NomeFunc,CodDep,Ramal,Salario,DataAd,DataCad,Sexo)
VALUES (10,'Edna',01,375,30.000,07/12/2020,07/12/2020,'feminino');

-- o Exercicio 3 estará aqui dentro do database ExercBD3

create table papelaria 
(

ID_produto int primary key not null,
tipo varchar (15) not null,
preco float not null,
marca varchar (15),
detalhes varchar (15),

)

create table func (

ID_funcionario int primary key not null,
nome varchar (10) not null,
cargo varchar (15) not null,
dep varchar (10) not null,
dateadm datetime not null,
salario float not null,

)

create table fornecedor (

ID_fornecedor int primary key not null,
nome varchar (10) not  null,
CNPJ varchar (18) not null,
telefone varchar (15) not null,
email varchar (20) not null,

)

create table dep (

ID_dep int primary key not null,
nome varchar (15) not null,
setor varchar (15) not null,
N_func int not null,

)





