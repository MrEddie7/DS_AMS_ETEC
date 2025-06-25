CREATE DATABASE bd_empresa;
USE bd_empresa;

-- tabela fornecedores

CREATE TABLE `fornecedores` (
  `Cod_fornecedor` int(11) NOT NULL AUTO_INCREMENT,
  `Razao_social` varchar(100) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `CNPJ` char(14) NOT NULL,
  `endereco` varchar(255) NOT NULL,
  `num` int(11) NOT NULL,
  `bairro` varchar(100) NOT NULL,
  `cidade` varchar(100) NOT NULL,
  `fone` varchar(20) NOT NULL,
  `nome_contato` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `site` varchar(100) NOT NULL,
  PRIMARY KEY (`Cod_fornecedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- inserir dados em fonecedores

INSERT INTO `fornecedores` (`Cod_fornecedor`, `Razao_social`, `nome`, `CNPJ`, `endereco`, `num`, `bairro`, `cidade`, `fone`, `nome_contato`, `email`, `site`) VALUES
(1, 'ED7 serviços e manut', 'ED7', '12322343784646', 'rua arvore da judea', 33, 'sã carlos', 'são paulo', '11 98768765', 'Eduardo', 'ed7@ed7.com', 'ed7.com'),
(2, 'onclick', 'onclick', '123223437599', 'rua pindoba', 33, 'são matheus', 'são paulo', '11 98769979', 'Ed Carlos', 'onclick@gmail.com', 'onclick.com'),
(3, 'TechWorks Solutions', 'TechWorks', '98765432000199', 'av. paulista', 1000, 'centro', 'são paulo', '11 32323232', 'Carlos', 'techworks@tech.com', 'techworks.com'),
(4, 'Prime Infraestrutura', 'Prime', '12345678000155', 'rua dos engenheiros', 45, 'santa teresa', 'rio de janeiro', '21 33445566', 'Ana', 'primeinfra@prime.com', 'primeinfra.com'),
(5, 'Logistica Xpress', 'LogXpress', '43210987654321', 'rua do comercio', 210, 'pinheiros', 'são paulo', '11 66554433', 'Roberto', 'logxpress@log.com', 'logxpress.com'),
(6, 'BioTech Brasil', 'BioTech', '99988877700199', 'av. das nações', 555, 'jardim botanico', 'curitiba', '41 99887766', 'Mariana', 'biotech@biotech.com', 'biotech.com'),
(7, 'M2 Consultoria', 'M2 Consultoria', '76543210987654', 'rua da saúde', 20, 'vila progredior', 'porto alegre', '51 33669988', 'Lucas', 'm2consultoria@m2.com', 'm2consultoria.com'),
(8, 'AutoPeças Silva', 'AutoPeças', '34567890123456', 'rua dos carros', 78, 'bairro dos automóvei', 'belo horizonte', '31 77889922', 'Felipe', 'autopecas@silva.com', 'autopecas.com'),
(9, 'Construtora ABC', 'Construtora ABC', '11122233344455', 'av. brasil', 777, 'centro', 'salvador', '71 22334455', 'Fernanda', 'construtoraabc@abc.c', 'construtoraabc.com'),
(10, 'Vega Consultoria', 'Vega', '87654321009876', 'rua dos profissionai', 90, 'vila maria', 'fortaleza', '85 44332211', 'Gustavo', 'vega@vega.com', 'vega.com'),
(11, 'Soluções Digitais', 'Soluções', '56789012345678', 'rua digital', 150, 'santo amaro', 'são paulo', '11 55667788', 'Juliana', 'solucoesdigitais@sol', 'solucoesdigitais.com');

-- tabela produtos

CREATE TABLE `produtos` (
  `Cod_produto` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) NOT NULL,
  `unidade` varchar(10) NOT NULL,
  `Qtde_Estoque` double NOT NULL,
  `caracteristicas` varchar(255) NOT NULL,
  `Cod_fornecedor` int(11) NOT NULL,
  PRIMARY KEY (`Cod_produto`),
  FOREIGN KEY (`Cod_fornecedor`) REFERENCES `fornecedores`(`Cod_fornecedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- inserir dados produtos

INSERT INTO `produtos` (`Cod_produto`, `descricao`, `unidade`, `Qtde_Estoque`, `caracteristicas`, `Cod_fornecedor`) VALUES
(1, 'sabonete', 'cx', 150, 'framboesa', 1),
(2, 'tenis', 'cx', 90, 'vermelho', 2),
(3, 'monitor', 'un', 90, 'preto, lenovo', 3),
(4, 'mouse', 'un', 96, 'preto', 4),
(5, 'teclado', 'un', 99, 'preto', 5),
(6, 'pilha', 'un', 99, 'duracell', 6),
(7, 'cola', '1', 100, 'branca, bastão', 7),
(8, 'cola', '1', 100, 'branca, bastão', 8),
(9, 'pneu', '1', 100, 'bicicleta', 9),
(10, 'chave de fenda', '1', 100, 'kit de chaves', 10),
(11, 'fone', '1', 100, 'bluetooth', 11),
(12, 'fone', '1', 100, 'de fio', 12),
(13, 'celular', '1', 100, 'xiaomi', 13),
(14, 'celular', '1', 100, 'samsung', 14),
(15, 'notebook', '1', 100, 'macbook', 15),
(16, 'capa', '1', 100, 'telefone, preta', 16);
