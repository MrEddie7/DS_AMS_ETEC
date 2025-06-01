


--
-- Estrutura da tabela `alunos`
--

CREATE TABLE `alunos` (
  `Matricula` varchar(5) NOT NULL,
  `Nome` varchar(50) NOT NULL,
  `Endereco` varchar(50) NOT NULL,
  `cidade` varchar(30) NOT NULL,
  `CodCurso` char(2) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `alunos`
--

INSERT INTO `alunos` (`Matricula`, `Nome`, `Endereco`, `cidade`, `CodCurso`) VALUES
('23434', 'Lucas', 'Rua da Programacao 22', 'Sao Paulo', '33'),
('56778', 'Roger', 'rua da discordia 33', 'Sao Paulo', '11'),
('56779', 'Ana', 'rua das flores 45', 'Sao Paulo', '27'),
('56780', 'Carlos', 'avenida Brasil 101', 'Sao Paulo', '10'),
('56781', 'Fernanda', 'rua do sol 78', 'Rio de Janeiro', '11'),
('56782', 'Paulo', 'rua do comercio 32', 'Sao Paulo', '33'),
('56783', 'Julia', 'rua dos ventos 56', 'Belo Horizonte', '27'),
('56784', 'Lucas', 'rua do lago 12', 'Curitiba', '10'),
('56785', 'Maria', 'rua do céu 67', 'Porto Alegre', '33'),
('56786', 'Gustavo', 'rua dos campos 89', 'Campinas', '27'),
('56787', 'Carla', 'avenida dos ipês 33', 'Sao Paulo', '11'),
('56788', 'Ricardo', 'rua da paz 90', 'Sao Paulo', '10'),
('56789', 'Beatriz', 'rua da alegria 22', 'Recife', '33'),
('56790', 'Eduardo', 'rua do futuro 44', 'Rio de Janeiro', '27'),
('56791', 'Isabela', 'avenida das estrelas 77', 'Fortaleza', '11'),
('56792', 'Felipe', 'rua do saber 11', 'Brasilia', '10'),
('56793', 'Tatiane', 'rua dos campos 55', 'Salvador', '33'),
('56794', 'André', 'rua das flores 99', 'Sao Paulo', '27'),
('56795', 'Patricia', 'rua do tesouro 34', 'Belo Horizonte', '33'),
('56796', 'Rodrigo', 'avenida Rio 65', 'Sao Paulo', '11');

-- --------------------------------------------------------

--
-- Estrutura da tabela `cursos`
--

CREATE TABLE `cursos` (
  `CodCurso` char(2) NOT NULL,
  `Nome` varchar(50) NOT NULL,
  `coddisc1` char(2) NOT NULL,
  `coddisc2` char(2) NOT NULL,
  `coddisc3` char(2) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `cursos`
--

INSERT INTO `cursos` (`CodCurso`, `Nome`, `coddisc1`, `coddisc2`, `coddisc3`) VALUES
('27', 'IA e Banco de Dados', '18', '26', '27'),
('10', 'Desenvolvimento Web', '07', '09', '17'),
('11', 'Sistemas Multiplataforma', '99', '20', '26'),
('33', 'Tecnicas de programacao', '07', '20', '27');

-- --------------------------------------------------------

--
-- Estrutura da tabela `disciplinas`
--

CREATE TABLE `disciplinas` (
  `CodDisciplina` char(2) NOT NULL,
  `NomeDisciplina` varchar(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `disciplinas`
--

INSERT INTO `disciplinas` (`CodDisciplina`, `NomeDisciplina`) VALUES
('27', 'Ia e Banco de Dados'),
('29', 'Automobilística'),
('07', 'Microbiologia'),
('20', 'Paleovirologia'),
('17', 'IA'),
('26', 'Design'),
('09', 'Manutenção de hardware'),
('22', 'Manutenção de software'),
('18', 'Introdução digital'),
('99', 'Marketing');

-- --------------------------------------------------------

CREATE TABLE `professores` (
  `CodProfessor` char(2) NOT NULL,
  `Nome` varchar(30) NOT NULL,
  `Endereco` varchar(50) NOT NULL,
  `Cidade` varchar(30) NOT NULL,
  `CodDisciplina` char(2) NOT NULL,
  `CodCurso` char(2) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

INSERT INTO `professores` (`CodProfessor`, `Nome`, `Endereco`, `Cidade`, `CodDisciplina`, `CodCurso`) VALUES
('01', 'Carlos', 'Rua das Flores 123', 'Sao Paulo', '07', '33'),
('02', 'Ana', 'Avenida Brasil 456', 'Rio de Janeiro', '20', '10'),
('03', 'Lucas', 'Rua do Sol 789', 'Belo Horizonte', '26', '27'),
('04', 'Fernanda', 'Rua do Lago 1011', 'Curitiba', '18', '11'),
('05', 'Paulo', 'Avenida dos Ipês 1213', 'Porto Alegre', '99', '33'),
('06', 'Tatiane', 'Rua do Comércio 1415', 'Salvador', '09', '10'),
('07', 'Ricardo', 'Avenida das Estrelas 1617', 'Fortaleza', '22', '27'),
('08', 'Eduardo', 'Rua do Saber 1819', 'Brasilia', '29', '11'),
('09', 'Isabela', 'Rua da Alegria 2021', 'Recife', '07', '33'),
('10', 'Gustavo', 'Avenida Rio 2223', 'Campinas', '20', '10'),
('11', 'Carla', 'Rua do Tesouro 2425', 'Sao Paulo', '18', '27'),
('12', 'André', 'Rua da Paz 2627', 'Sao Paulo', '26', '11'),
('13', 'Edna', 'Rua dos Campos 2829', 'Sao Paulo', '27', '11'),
('14', 'Rodrigo', 'Avenida Brasil 3031', 'Rio de Janeiro', '22', '10'),
('15', 'Beatriz', 'Rua do Futuro 3233', 'Recife', '29', '27'),
('16', 'Lucas', 'Rua da Programacao 3435', 'Sao Paulo', '07', '11'),
('17', 'Roger', 'Rua da Discórdia 3637', 'Sao Paulo', '20', '33'),
('18', 'Julia', 'Rua dos Ventos 3839', 'Belo Horizonte', '26', '10'),
('19', 'Maria', 'Rua do Céu 4041', 'Porto Alegre', '18', '27'),
('20', 'Carlos', 'Avenida Brasil 4243', 'Sao Paulo', '99', '11'),
('21', 'Fernanda', 'Rua do Sol 4445', 'Rio de Janeiro', '09', '33'),
('22', 'Paulo', 'Rua do Comércio 4647', 'Sao Paulo', '22', '10'),
('23', 'Tatiane', 'Avenida dos Ipês 4849', 'Salvador', '29', '27'),
('24', 'Ricardo', 'Rua da Alegria 5051', 'Recife', '07', '11'),
('25', 'Eduardo', 'Rua do Saber 5253', 'Brasilia', '20', '33'),
('26', 'Isabela', 'Rua da Paz 5455', 'Fortaleza', '18', '10'),
('27', 'Gustavo', 'Avenida Rio 5657', 'Campinas', '99', '27'),
('28', 'Carla', 'Rua do Tesouro 5859', 'Sao Paulo', '09', '11'),
('29', 'André', 'Rua dos Campos 6061', 'Sao Paulo', '22', '33'),
('30', 'Beatriz', 'Rua do Futuro 6263', 'Recife', '29', '10');

-- --------------------------------------------------------

CREATE TABLE `turmas` (
  `CodTurma` char(2) NOT NULL,
  `CodDisciplina` char(2) NOT NULL,
  `CodCurso` char(2) NOT NULL,
  `CodProfessor` char(2) NOT NULL,
  `Ano` int(4) NOT NULL,
  `Semestre` int(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

INSERT INTO `turmas` (`CodTurma`, `CodDisciplina`, `CodCurso`, `CodProfessor`, `Ano`, `Semestre`) VALUES
('01', '07', '33', '01', 2023, 1),
('02', '20', '10', '02', 2023, 1),
('03', '26', '27', '03', 2023, 1),
('04', '18', '11', '04', 2023, 1),
('05', '99', '33', '05', 2023, 1),
('06', '09', '10', '06', 2023, 1),
('07', '22', '27', '07', 2023, 1),
('08', '29', '11', '08', 2023, 1),
('09', '07', '33', '09', 2023, 1),
('10', '20', '10', '10', 2023, 1),
('11', '26', '27', '11', 2023, 1),
('12', '18', '11', '12', 2023, 1),
('13', '99', '33', '13', 2023, 1),
('14', '09', '10', '14', 2023, 1),
('15', '22', '27', '15', 2023, 1),
('16', '29', '11', '16', 2023, 1),
('17', '07', '33', '17', 2023, 1),
('18', '20', '10', '18', 2023, 1),
('19', '26', '27', '19', 2023, 1),
('20', '18', '11', '20', 2023, 1),
('21', '99', '33', '21', 2023, 1),
('22', '09', '10', '22', 2023, 1),
('23', '22', '27', '23', 2023, 1),
('24', '29', '11', '24', 2023, 1);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `alunos`
--
ALTER TABLE `alunos`
  ADD PRIMARY KEY (`Matricula`);

--
-- Índices para tabela `cursos`
--
ALTER TABLE `cursos`
  ADD PRIMARY KEY (`CodCurso`);

--
-- Índices para tabela `disciplinas`
--
ALTER TABLE `disciplinas`
  ADD PRIMARY KEY (`CodDisciplina`);
COMMIT;

