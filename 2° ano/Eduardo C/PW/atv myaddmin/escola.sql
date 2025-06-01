


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

