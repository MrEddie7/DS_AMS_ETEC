CREATE DATABASE IF NOT EXISTS painel_solar
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_unicode_ci;
USE painel_solar;

-- Usuários do sistema
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    tipo ENUM('admin', 'usuario') DEFAULT 'usuario',
    criado_em DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Projetos cadastrados
CREATE TABLE projetos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    localizacao VARCHAR(255),
    criado_por INT,
    criado_em DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (criado_por) REFERENCES usuarios(id)
      ON DELETE SET NULL
      ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dados de medições dos painéis solares
CREATE TABLE medicoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    projeto_id INT,
    energia_gerada DECIMAL(10,2), -- kWh
    irradiancia DECIMAL(10,2),    -- W/m²
    temperatura DECIMAL(5,2),     -- °C
    data_medicao DATETIME NOT NULL,
    FOREIGN KEY (projeto_id) REFERENCES projetos(id)
      ON DELETE CASCADE
      ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Histórico de eventos ou alterações
CREATE TABLE historico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    projeto_id INT,
    acao VARCHAR(255),
    detalhes TEXT,
    data_evento DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
      ON DELETE SET NULL
      ON UPDATE CASCADE,
    FOREIGN KEY (projeto_id) REFERENCES projetos(id)
      ON DELETE CASCADE
      ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dados genéricos coletados pela API
CREATE TABLE dados_api (
    id INT AUTO_INCREMENT PRIMARY KEY,
    chave VARCHAR(100),
    valor VARCHAR(255),
    projeto_id INT,
    data_coleta DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (projeto_id) REFERENCES projetos(id)
      ON DELETE CASCADE
      ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
-- Índices para otimização de consultas
CREATE INDEX idx_projeto_id ON medicoes(projeto_id);
CREATE INDEX idx_usuario_id ON historico(usuario_id);
CREATE INDEX idx_projeto_historico ON historico(projeto_id);
CREATE INDEX idx_projeto_dados_api ON dados_api(projeto_id);
CREATE INDEX idx_data_medicao ON medicoes(data_medicao);
CREATE INDEX idx_data_evento ON historico(data_evento);

    