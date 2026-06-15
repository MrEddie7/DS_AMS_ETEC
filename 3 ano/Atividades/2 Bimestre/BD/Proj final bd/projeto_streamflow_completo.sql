-- ============================================================================
-- PROJETO BANCO DE DADOS STREAMFLOW (UNIFICADO E COMPLETO)
-- SGBD: MariaDB / MySQL (Compatível com MariaDB 10.2+ e MySQL 8.0+)
-- Autor: Arquiteto e Engenheiro de Dados
-- ============================================================================

-- Criação e inicialização do banco de dados em ambiente limpo
CREATE DATABASE IF NOT EXISTS streamflow;
USE streamflow;

-- Desativa checagem de FKs para permitir recriação das tabelas
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS historicos_reproducao;
DROP TABLE IF EXISTS filmes;
DROP TABLE IF EXISTS episodios;
DROP TABLE IF EXISTS series;
DROP TABLE IF EXISTS videos;
DROP TABLE IF EXISTS produtoras;
DROP TABLE IF EXISTS perfis;
DROP TABLE IF EXISTS assinantes;
DROP VIEW IF EXISTS vw_analise_engajamento_lgpd;
SET FOREIGN_KEY_CHECKS = 1;

-- ============================================================================
-- SEÇÃO 1: DEFINIÇÃO DE ESTRUTURA FÍSICA E INTEGRIDADE (DDL)
-- ============================================================================

-- Tabela de Assinantes (Contém dados principais de faturamento e cadastro)
CREATE TABLE assinantes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    data_nascimento DATE NOT NULL,
    uf CHAR(2) NOT NULL,
    dados_pagamento VARCHAR(255) NULL, -- Armazenamento mascarado ou tokenizado
    ativo BOOLEAN NOT NULL DEFAULT TRUE, -- Flag para Soft Delete do assinante
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_cpf_length CHECK (CHAR_LENGTH(cpf) = 11),
    CONSTRAINT chk_uf_length CHECK (CHAR_LENGTH(uf) = 2)
);

-- Tabela de Perfis (Permite o gerenciamento de até 5 perfis por conta)
CREATE TABLE perfis (
    id INT AUTO_INCREMENT PRIMARY KEY,
    assinante_id INT NOT NULL,
    nome_exibicao VARCHAR(50) NOT NULL,
    preferencias TEXT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE, -- Flag para Soft Delete de perfil
    CONSTRAINT fk_perfis_assinantes FOREIGN KEY (assinante_id) 
        REFERENCES assinantes(id) ON DELETE RESTRICT
);

-- Tabela de Produtoras (Parceiros de conteúdo para faturamento de royalties)
CREATE TABLE produtoras (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de Séries (Agrupadores de episódios)
CREATE TABLE series (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    sinopse TEXT NULL,
    produtora_id INT NOT NULL,
    CONSTRAINT fk_series_produtoras FOREIGN KEY (produtora_id) 
        REFERENCES produtoras(id) ON DELETE RESTRICT
);

-- Tabela Base de Vídeos (Estratégia: Class Table Inheritance - CTI)
-- Guarda as informações comuns a Filmes e Episódios de Séries
CREATE TABLE videos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    duracao_segundos INT NOT NULL,
    tipo VARCHAR(10) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE, -- Flag de exclusão lógica (Soft Delete) do catálogo
    CONSTRAINT chk_duracao_positiva CHECK (duracao_segundos > 0),
    CONSTRAINT chk_tipo_video CHECK (tipo IN ('filme', 'episodio'))
);

-- Tabela Especializada: Filmes
CREATE TABLE filmes (
    id INT PRIMARY KEY,
    produtora_id INT NOT NULL,
    sinopse TEXT NULL,
    CONSTRAINT fk_filmes_videos FOREIGN KEY (id) 
        REFERENCES videos(id) ON DELETE RESTRICT,
    CONSTRAINT fk_filmes_produtoras FOREIGN KEY (produtora_id) 
        REFERENCES produtoras(id) ON DELETE RESTRICT
);

-- Tabela Especializada: Episódios de Séries
CREATE TABLE episodios (
    id INT PRIMARY KEY,
    serie_id INT NOT NULL,
    numero_temporada INT NOT NULL,
    numero_episodio INT NOT NULL,
    sinopse TEXT NULL,
    CONSTRAINT fk_episodios_videos FOREIGN KEY (id) 
        REFERENCES videos(id) ON DELETE RESTRICT,
    CONSTRAINT fk_episodios_series FOREIGN KEY (serie_id) 
        REFERENCES series(id) ON DELETE RESTRICT,
    CONSTRAINT chk_temporada_positiva CHECK (numero_temporada > 0),
    CONSTRAINT chk_episodio_positivo CHECK (numero_episodio > 0)
);

-- Tabela de Logs de Reprodução (Histórico imutável de visualizações)
CREATE TABLE historicos_reproducao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    perfil_id INT NOT NULL,
    video_id INT NOT NULL,
    tempo_assistido_segundos INT NOT NULL DEFAULT 0,
    concluido BOOLEAN NOT NULL DEFAULT FALSE,
    ip_conexao VARCHAR(45) NOT NULL,
    dispositivo VARCHAR(20) NOT NULL,
    assistido_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_historico_perfis FOREIGN KEY (perfil_id) 
        REFERENCES perfis(id) ON DELETE RESTRICT,
    CONSTRAINT fk_historico_videos FOREIGN KEY (video_id) 
        REFERENCES videos(id) ON DELETE RESTRICT,
    CONSTRAINT chk_tempo_assistido CHECK (tempo_assistido_segundos >= 0),
    CONSTRAINT chk_dispositivo CHECK (dispositivo IN ('SmartTV', 'Smartphone', 'Web'))
);


-- ============================================================================
-- SEÇÃO 2: REGRAS DE NEGÓCIO E IMUTABILIDADE VIA TRIGGERS
-- ============================================================================

DELIMITER //

-- A. Limite de Perfis: Bloqueia inserção de mais de 5 perfis por assinante
CREATE TRIGGER trg_limite_perfis_insert
BEFORE INSERT ON perfis
FOR EACH ROW
BEGIN
    DECLARE total_perfis_ativos INT;
    
    SELECT COUNT(*) INTO total_perfis_ativos
    FROM perfis
    WHERE assinante_id = NEW.assinante_id AND ativo = TRUE;

    IF total_perfis_ativos >= 5 AND NEW.ativo = TRUE THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Regra de Negócio Violada: O limite máximo de 5 perfis ativos por conta de assinante foi atingido.';
    END IF;
END; //

-- B. Limite de Perfis: Bloqueia reativação se já existirem 5 perfis ativos
CREATE TRIGGER trg_limite_perfis_update
BEFORE UPDATE ON perfis
FOR EACH ROW
BEGIN
    DECLARE total_perfis_ativos INT;
    
    IF NEW.ativo = TRUE AND OLD.ativo = FALSE THEN
        SELECT COUNT(*) INTO total_perfis_ativos
        FROM perfis
        WHERE assinante_id = NEW.assinante_id AND ativo = TRUE;

        IF total_perfis_ativos >= 5 THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Regra de Negócio Violada: O limite máximo de 5 perfis ativos por conta de assinante foi atingido.';
        END IF;
    END IF;
END; //

-- C. Imutabilidade dos Logs: Bloqueia atualizações (UPDATE)
CREATE TRIGGER trg_impedir_update_historico
BEFORE UPDATE ON historicos_reproducao
FOR EACH ROW
BEGIN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Segurança e Auditoria: Não é permitido modificar (UPDATE) registros da tabela de históricos de reprodução.';
END; //

-- D. Imutabilidade dos Logs: Bloqueia deleções físicas (DELETE)
CREATE TRIGGER trg_impedir_delete_historico
BEFORE DELETE ON historicos_reproducao
FOR EACH ROW
BEGIN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Segurança e Auditoria: Não é permitido remover (DELETE) registros da tabela de históricos de reprodução.';
END; //

DELIMITER ;


-- ============================================================================
-- SEÇÃO 3: OTIMIZAÇÃO DE DESEMPENHO (ÍNDICES)
-- ============================================================================

-- Índice Composto projetado para eliminar o gargalo do painel "Continuar Assistindo".
-- Ordena os dados por perfil, status e data para otimizar busca e evitar Filesort.
CREATE INDEX idx_historico_continuar_assistindo 
ON historicos_reproducao (perfil_id, concluido, video_id, assistido_em DESC);


-- ============================================================================
-- SEÇÃO 4: CONFORMIDADE LGPD (VISÕES)
-- ============================================================================

-- View que expõe métricas agregadas ocultando dados cadastrais reais dos clientes
CREATE OR REPLACE VIEW vw_analise_engajamento_lgpd AS
SELECT
    -- ID mascarado via hash de sentido único para joins analíticos seguros
    MD5(a.id) AS assinante_hash_id,
    
    -- Idade calculada dinamicamente, mantendo a privacidade da data de nascimento real
    TIMESTAMPDIFF(YEAR, a.data_nascimento, CURDATE()) AS idade,
    
    -- UF do assinante para segmentação geográfica
    a.uf AS estado,
    
    -- Métricas de engajamento acumuladas
    COUNT(DISTINCT p.id) AS total_perfis_ativos,
    COUNT(h.id) AS total_visualizacoes_iniciadas,
    COALESCE(ROUND(SUM(h.tempo_assistido_segundos) / 60.0, 2), 0.0) AS total_tempo_assistido_minutos,
    COALESCE(ROUND(AVG(h.tempo_assistido_segundos) / 60.0, 2), 0.0) AS tempo_medio_sessao_minutos
FROM assinantes a
LEFT JOIN perfis p ON a.id = p.assinante_id AND p.ativo = TRUE
LEFT JOIN historicos_reproducao h ON p.id = h.perfil_id
WHERE a.ativo = TRUE
GROUP BY a.id, a.data_nascimento, a.uf;


-- ============================================================================
-- SEÇÃO 5: POLÍTICA DE SEGURANÇA E PRIVILÉGIOS (RBAC)
-- ============================================================================

-- Criação de Perfis/Usuários se não existirem
CREATE USER IF NOT EXISTS 'app_user'@'%' IDENTIFIED BY 'StreamFlow_App_Secure_2026';
CREATE USER IF NOT EXISTS 'auditor_user'@'%' IDENTIFIED BY 'StreamFlow_Audit_Read_2026';
CREATE USER IF NOT EXISTS 'analista_dados_user'@'%' IDENTIFIED BY 'StreamFlow_Analyst_Secure_2026';

-- Concessão de Privilégios para 'app_user' (CRUD operacional)
GRANT SELECT, INSERT, UPDATE, DELETE ON assinantes TO 'app_user'@'%';
GRANT SELECT, INSERT, UPDATE, DELETE ON perfis TO 'app_user'@'%';
GRANT SELECT, INSERT, UPDATE, DELETE ON produtoras TO 'app_user'@'%';
GRANT SELECT, INSERT, UPDATE, DELETE ON series TO 'app_user'@'%';
GRANT SELECT, INSERT, UPDATE, DELETE ON videos TO 'app_user'@'%';
GRANT SELECT, INSERT, UPDATE, DELETE ON filmes TO 'app_user'@'%';
GRANT SELECT, INSERT, UPDATE, DELETE ON episodios TO 'app_user'@'%';
-- Permissão Restrita nos Logs: Sem UPDATE ou DELETE
GRANT SELECT, INSERT ON historicos_reproducao TO 'app_user'@'%';

-- Concessão de Privilégios para 'auditor_user' (Leitura restrita de logs)
GRANT SELECT ON historicos_reproducao TO 'auditor_user'@'%';
GRANT SELECT ON videos TO 'auditor_user'@'%';
GRANT SELECT ON filmes TO 'auditor_user'@'%';
GRANT SELECT ON episodios TO 'auditor_user'@'%';
GRANT SELECT ON series TO 'auditor_user'@'%';
GRANT SELECT ON produtoras TO 'auditor_user'@'%';
GRANT SELECT ON perfis TO 'auditor_user'@'%';
REVOKE ALL PRIVILEGES ON assinantes FROM 'auditor_user'@'%';

-- Concessão de Privilégios para 'analista_dados_user' (BI com restrição LGPD)
GRANT SELECT ON vw_analise_engajamento_lgpd TO 'analista_dados_user'@'%';
GRANT SELECT ON videos TO 'analista_dados_user'@'%';
GRANT SELECT ON filmes TO 'analista_dados_user'@'%';
GRANT SELECT ON episodios TO 'analista_dados_user'@'%';
GRANT SELECT ON series TO 'analista_dados_user'@'%';
GRANT SELECT ON produtoras TO 'analista_dados_user'@'%';
REVOKE ALL PRIVILEGES ON assinantes FROM 'analista_dados_user'@'%';
REVOKE ALL PRIVILEGES ON perfis FROM 'analista_dados_user'@'%';

FLUSH PRIVILEGES;


-- ============================================================================
-- SEÇÃO 6: CARGA DE DADOS FICTÍCIOS (SEED DATA)
-- ============================================================================

-- Inserindo Assinantes
INSERT INTO assinantes (nome, email, cpf, data_nascimento, uf, dados_pagamento) VALUES
('João Silva', 'joao.silva@email.com', '11122233344', '1985-04-12', 'SP', 'Cartão Visa **** 1234'),
('Maria Oliveira', 'maria.oliveira@email.com', '55566677788', '1999-09-15', 'RJ', 'Pix Chave Aleatória'),
('Carlos Souza', 'carlos.souza@email.com', '99988877766', '2005-02-28', 'MG', 'Boleto Bancário');

-- Inserindo Perfis (João Silva adiciona 5 perfis)
INSERT INTO perfis (assinante_id, nome_exibicao, preferencias) VALUES
(1, 'João Principal', 'Sci-Fi, Ação'),
(1, 'Ana (Esposa)', 'Drama, Romance'),
(1, 'Pedro (Filho)', 'Desenhos, Animação'),
(1, 'Clara (Filha)', 'Desenhos, Musicais'),
(1, 'Vovó Geralda', 'Novelas, Documentários');

-- Perfis adicionais para outros assinantes
INSERT INTO perfis (assinante_id, nome_exibicao, preferencias) VALUES
(2, 'Maria User', 'Terror, Suspense'),
(3, 'Carlos Gamer', 'Anime, Aventura');

-- Inserindo Produtoras
INSERT INTO produtoras (nome) VALUES
('Warner Bros. Pictures'),
('Netflix Studios'),
('Universal Pictures');

-- Inserindo Séries
INSERT INTO series (titulo, sinopse, produtora_id) VALUES
('Stranger Things', 'Grupo de amigos se envolve em mistérios sobrenaturais.', 2),
('Breaking Bad', 'Professor de química entra para o mundo do crime.', 3);

-- Inserindo Vídeos na tabela base (CTI)
INSERT INTO videos (titulo, duracao_segundos, tipo) VALUES
('The Batman', 10800, 'filme'),     -- ID 1
('Inception', 8880, 'filme'),        -- ID 2
('O Hobbit', 11400, 'filme'),        -- ID 3
('Stranger Things S01E01', 3000, 'episodio'), -- ID 4
('Stranger Things S01E02', 3300, 'episodio'), -- ID 5
('Breaking Bad S01E01', 3500, 'episodio');     -- ID 6

-- Vinculando a tabela especializada: Filmes
INSERT INTO filmes (id, produtora_id, sinopse) VALUES
(1, 1, 'Batman investiga o submundo de Gotham City.'),
(2, 1, 'Ladrão de sonhos invade mentes industriais.'),
(3, 3, 'Aventura épica de Bilbo Bolseiro.');

-- Vinculando a tabela especializada: Episódios
INSERT INTO episodios (id, serie_id, numero_temporada, numero_episodio, sinopse) VALUES
(4, 1, 1, 1, 'O desaparecimento de Will Byers.'),
(5, 1, 1, 2, 'A aparição de uma garota misteriosa.'),
(6, 2, 1, 1, 'Walter White recebe um diagnóstico chocante.');

-- Inserindo Histórico de Reprodução
-- Dados que acumulam visualizações normais e simulação de consumo massivo
INSERT INTO historicos_reproducao (perfil_id, video_id, tempo_assistido_segundos, concluido, ip_conexao, dispositivo, assistido_em) VALUES
-- João Principal (ID 1) assistindo The Batman (ID 1) - Não concluído
(1, 1, 5000, FALSE, '192.168.1.10', 'SmartTV', '2026-06-10 20:00:00'),

-- João Principal assistindo Inception (ID 2) - Concluído
(1, 2, 8880, TRUE, '192.168.1.10', 'SmartTV', '2026-06-11 22:30:00'),

-- João Principal assistindo Stranger Things S01E01 (ID 4) - Progresso antigo
(1, 4, 1500, FALSE, '192.168.1.15', 'Smartphone', '2026-06-12 14:00:00'),
-- João Principal assistindo Stranger Things S01E01 (ID 4) - Progresso mais recente (2500s)
(1, 4, 2500, FALSE, '192.168.1.10', 'SmartTV', '2026-06-14 18:00:00'),

-- Maria User (ID 6) assistindo Stranger Things S01E01 - Concluído
(6, 4, 3000, TRUE, '200.100.50.25', 'Web', '2026-06-15 09:15:00'),

-- Simulação de 5013 horas de consumo Warner Bros (ID 1) em Junho/2026
(7, 1, 18050000, FALSE, '177.80.20.10', 'Web', '2026-06-05 10:00:00'),

-- Simulação de 1000 horas de consumo Netflix (ID 2) em Junho/2026
(2, 5, 3600000, FALSE, '192.168.1.20', 'Smartphone', '2026-06-06 15:00:00');


-- ============================================================================
-- SEÇÃO 7: CONSULTAS DE BUSINESS INTELLIGENCE (BI)
-- ============================================================================

-- A. Relatório 1: Painel "Continuar Assistindo" da Tela Inicial
-- Seleciona apenas a linha mais recente de cada vídeo que o perfil 1 não terminou
SELECT 
    sub.video_id,
    sub.titulo,
    sub.tipo_conteudo,
    sub.duracao_segundos,
    sub.tempo_assistido_segundos,
    sub.porcentagem_conclusao,
    sub.ultimo_acesso
FROM (
    SELECT 
        h.video_id,
        v.titulo,
        v.tipo AS tipo_conteudo,
        v.duracao_segundos,
        h.tempo_assistido_segundos,
        ROUND((h.tempo_assistido_segundos / v.duracao_segundos) * 100, 2) AS porcentagem_conclusao,
        h.assistido_em AS ultimo_acesso,
        ROW_NUMBER() OVER (PARTITION BY h.video_id ORDER BY h.assistido_em DESC) AS rn
    FROM historicos_reproducao h
    JOIN videos v ON h.video_id = v.id
    WHERE h.perfil_id = 1
      AND h.concluido = FALSE
) sub
WHERE sub.rn = 1
ORDER BY sub.ultimo_acesso DESC;

-- B. Relatório 2: Relatório de Cobrança dos Estúdios/Produtoras (BI)
-- Filtra o total assistido das produtoras em Junho/2026 que ultrapassou 5000 horas
SELECT 
    p.nome AS produtora_parceira,
    ROUND(SUM(h.tempo_assistido_segundos) / 60.0, 2) AS total_minutos_consumidos,
    ROUND(SUM(h.tempo_assistido_segundos) / 3600.0, 2) AS total_horas_consumidas
FROM historicos_reproducao h
JOIN videos v ON h.video_id = v.id
LEFT JOIN filmes f ON v.id = f.id
LEFT JOIN episodios e ON v.id = e.id
LEFT JOIN series s ON e.serie_id = s.id
JOIN produtoras p ON p.id = COALESCE(f.produtora_id, s.produtora_id)
WHERE h.assistido_em >= '2026-06-01 00:00:00' 
  AND h.assistido_em < '2026-07-01 00:00:00'
GROUP BY p.id, p.nome
HAVING SUM(h.tempo_assistido_segundos) / 3600.0 > 5000
ORDER BY total_horas_consumidas DESC;

-- C. Relatório 3: Auditoria de Tráfego por Região
SELECT 
    a.uf AS estado,
    h.dispositivo AS tipo_dispositivo,
    COUNT(h.id) AS total_visualizacoes_iniciadas,
    ROUND(AVG(h.tempo_assistido_segundos) / 60.0, 2) AS tempo_medio_sessao_minutos
FROM historicos_reproducao h
JOIN perfis p ON h.perfil_id = p.id
JOIN assinantes a ON p.assinante_id = a.id
GROUP BY a.uf, h.dispositivo
ORDER BY a.uf ASC, total_visualizacoes_iniciadas DESC;

-- D. Visualização da View LGPD
SELECT * FROM vw_analise_engajamento_lgpd;


-- ============================================================================
-- SEÇÃO 8: DIRETRIZES PARA CENÁRIOS DE TESTE E AUDITORIA (COMENTADOS)
-- ============================================================================
-- As instruções abaixo validam as restrições e triggers criados.
-- Para executar cada teste, copie o respectivo comando e rode no console do banco.

-- TESTE 1: Limite de 5 Perfis Ativos por Assinante (DEVE FALHAR)
-- O assinante 1 já tem 5 perfis. A query abaixo deve disparar erro SQLSTATE 45000.
-- INSERT INTO perfis (assinante_id, nome_exibicao, preferencias) VALUES (1, 'Tio Patinhas', 'Finanças');

-- TESTE 2: Imutabilidade do Histórico (DEVE FALHAR)
-- A alteração ou exclusão de histórico deve disparar erro SQLSTATE 45000.
-- UPDATE historicos_reproducao SET tempo_assistido_segundos = 500 WHERE id = 1;
-- DELETE FROM historicos_reproducao WHERE id = 1;

-- TESTE 3: Integridade referencial sobre catálogo (DEVE FALHAR)
-- Não é permitido deletar fisicamente um vídeo que já possui histórico associado.
-- DELETE FROM videos WHERE id = 1;

-- TESTE 4: Procedimento correto de Soft Delete (DEVE EXECUTAR COM SUCESSO)
-- UPDATE videos SET ativo = FALSE WHERE id = 1;
