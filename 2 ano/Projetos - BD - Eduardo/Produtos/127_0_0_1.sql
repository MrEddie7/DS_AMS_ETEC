
-- Banco de dados: `principal`
--
CREATE DATABASE IF NOT EXISTS `produtos` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `produtos`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `produtos`
--

CREATE TABLE `produtos` (
  `ID` int(11) NOT NULL,
  `Nome` varchar(13) NOT NULL,
  `Estoque` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `produtos`
--

INSERT INTO `produtos` (`ID`, `Nome`, `Estoque`) VALUES
(1, 'tenis', 12);

INSERT INTO `produtos` (`ID`, `Nome`, `Estoque`) VALUES
(2, 'salto', 32);

INSERT INTO `produtos` (`ID`, `Nome`, `Estoque`) VALUES
(3, 'caderno', 22);

INSERT INTO `produtos` (`ID`, `Nome`, `Estoque`) VALUES
(4, 'borracha', 12);

INSERT INTO `produtos` (`ID`, `Nome`, `Estoque`) VALUES
(5, 'pilha', 122);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `produtos`
--
ALTER TABLE `produtos`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `produtos`
--
ALTER TABLE `produtos`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Banco de dados: `test`
--
CREATE DATABASE IF NOT EXISTS `test` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `test`;
COMMIT;

