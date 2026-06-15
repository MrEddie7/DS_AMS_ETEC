-- ============================================================================
-- SCRIPT DE CARGA DE DADOS, TESTES E SIMULAÇÕES
-- SGBD: PostgreSQL
-- ============================================================================

-- ----------------------------------------------------------------------------
-- 1. Inserção de Dados Fictícios (Seed Data)
-- ----------------------------------------------------------------------------

-- Limpa registros de testes anteriores (na ordem reversa de dependência)
TRUNCATE TABLE historicos_reproducao, filmes, episodios, series, videos, produtoras, perfis, assinantes RESTART IDENTITY CASCADE;

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

-- Perfis dos outros assinantes
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

-- Inserindo Vídeos na tabela base (Estratégia CTI)
-- Filmes
INSERT INTO videos (titulo, duracao_segundos, tipo) VALUES
('The Batman', 10800, 'filme'),     -- ID 1
('Inception', 8880, 'filme'),        -- ID 2
('O Hobbit', 11400, 'filme');        -- ID 3

-- Episódios de Séries
INSERT INTO videos (titulo, duracao_segundos, tipo) VALUES
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
-- Junho/2026: Warner acumula 18.050.000s (~5.013 horas) assistidas para simular o relatório de cobrança do BI
INSERT INTO historicos_reproducao (perfil_id, video_id, tempo_assistido_segundos, concluido, ip_conexao, dispositivo, assistido_em) VALUES
-- João Principal (ID 1) assistindo The Batman (ID 1) - Não concluído
(1, 1, 5000, FALSE, '192.168.1.10', 'SmartTV', '2026-06-10 20:00:00'),

-- João Principal assistindo Inception (ID 2) - Concluído
(1, 2, 8880, TRUE, '192.168.1.10', 'SmartTV', '2026-06-11 22:30:00'),

-- João Principal assistindo Stranger Things S01E01 (ID 4) - Progresso antigo (1500s)
(1, 4, 1500, FALSE, '192.168.1.15', 'Smartphone', '2026-06-12 14:00:00'),
-- João Principal assistindo Stranger Things S01E01 (ID 4) - Progresso mais recente (2500s)
(1, 4, 2500, FALSE, '192.168.1.10', 'SmartTV', '2026-06-14 18:00:00'),

-- Maria User (ID 6) assistindo Stranger Things S01E01 - Concluído
(6, 4, 3000, TRUE, '200.100.50.25', 'Web', '2026-06-15 09:15:00'),

-- Carga Massiva Warner Bros (ID 1) para testar o filtro do BI (> 5000 horas em Junho 2026)
-- 5013 horas de consumo acumulado no vídeo The Batman (ID 1) por Carlos Gamer (ID 7)
(7, 1, 18050000, FALSE, '177.80.20.10', 'Web', '2026-06-05 10:00:00'),

-- Carga menor de Netflix (ID 2) em Junho/2026 - Não deve passar no filtro de 5.000 horas do BI
-- Stranger Things S01E02 (ID 5) assistido por Ana (ID 2) - 1000 horas (3.600.000 segundos)
(2, 5, 3600000, FALSE, '192.168.1.20', 'Smartphone', '2026-06-06 15:00:00');


-- ----------------------------------------------------------------------------
-- 2. Cenários de Validação de Regras de Negócio e Integridade
-- ----------------------------------------------------------------------------

-- ============================================================================
-- TESTE A: Limite de 5 Perfis Ativos por Assinante
-- ============================================================================
-- João Silva já possui 5 perfis cadastrados. A inserção do 6º perfil deve falhar.

DO $$
BEGIN
    BEGIN
        INSERT INTO perfis (assinante_id, nome_exibicao, preferencias) 
        VALUES (1, 'Tio Patinhas', 'Finanças');
        RAISE NOTICE 'Falha no Teste: O banco permitiu inserir um 6º perfil.';
    EXCEPTION WHEN others THEN
        RAISE NOTICE 'Sucesso no Teste A: O trigger bloqueou a inserção do 6º perfil. Mensagem: %', SQLERRM;
    END;
END $$;


-- ============================================================================
-- TESTE B: Imutabilidade dos Logs de Reprodução (UPDATE/DELETE)
-- ============================================================================
-- Qualquer alteração ou remoção na tabela historicos_reproducao deve ser bloqueada.

-- Testando UPDATE
DO $$
BEGIN
    BEGIN
        UPDATE historicos_reproducao 
        SET tempo_assistido_segundos = 100 
        WHERE id = 1;
        RAISE NOTICE 'Falha no Teste: O banco permitiu alterar registros do histórico.';
    EXCEPTION WHEN others THEN
        RAISE NOTICE 'Sucesso no Teste B (UPDATE): O trigger bloqueou a modificação. Mensagem: %', SQLERRM;
    END;
END $$;

-- Testando DELETE
DO $$
BEGIN
    BEGIN
        DELETE FROM historicos_reproducao 
        WHERE id = 1;
        RAISE NOTICE 'Falha no Teste: O banco permitiu excluir registros do histórico.';
    EXCEPTION WHEN others THEN
        RAISE NOTICE 'Sucesso no Teste B (DELETE): O trigger bloqueou a exclusão. Mensagem: %', SQLERRM;
    END;
END $$;


-- ============================================================================
-- TESTE C: Proteção contra Exclusão do Catálogo (Integridade de Auditoria)
-- ============================================================================
-- Tentar excluir o vídeo 'The Batman' (ID 1) fisicamente deve ser impedido, 
-- pois há histórico de reprodução associado a ele.

DO $$
BEGIN
    BEGIN
        DELETE FROM videos WHERE id = 1;
        RAISE NOTICE 'Falha no Teste: O banco permitiu a exclusão física do vídeo com histórico.';
    EXCEPTION WHEN others THEN
        RAISE NOTICE 'Sucesso no Teste C (Exclusão Física): A integridade referencial bloqueou a exclusão física do vídeo. Mensagem: %', SQLERRM;
    END;
END $$;

-- Demonstração de Soft Delete elegante para expiração de licença:
-- Desativa o vídeo sem quebrar as FKs e preservando os dados históricos intactos.
UPDATE videos SET ativo = FALSE WHERE id = 1;


-- ----------------------------------------------------------------------------
-- 3. Execução e Validação das Queries de BI e Views
-- ----------------------------------------------------------------------------

-- A. Execução da Query do Painel "Continuar Assistindo" para João Principal (ID 1)
-- Esperado:
--  - "Stranger Things S01E01" (ID 4) com progresso 2500s (e não o de 1500s, pois agrupamos pelo mais recente)
--  - "The Batman" (ID 1) com progresso 5000s
--  - O filme "Inception" (ID 2) NÃO deve aparecer pois foi concluído.
RAISE NOTICE 'Executando Query: Painel Continuar Assistindo...';
SELECT 
    sub.video_id,
    sub.titulo,
    sub.tipo_conteudo,
    sub.duracao_segundos,
    sub.tempo_assistido_segundos,
    sub.porcentagem_conclusao,
    sub.assistido_em AS ultimo_acesso
FROM (
    SELECT DISTINCT ON (h.video_id)
        h.video_id,
        v.titulo,
        v.tipo AS tipo_conteudo,
        v.duracao_segundos,
        h.tempo_assistido_segundos,
        ROUND((h.tempo_assistido_segundos::float / v.duracao_segundos::float) * 100, 2) AS porcentagem_conclusao,
        h.assistido_em
    FROM historicos_reproducao h
    JOIN videos v ON h.video_id = v.id
    WHERE h.perfil_id = 1
      AND h.concluido = FALSE
    ORDER BY h.video_id, h.assistido_em DESC
) sub
ORDER BY sub.assistido_em DESC;


-- B. Execução da Query de Cobrança das Produtoras (Junho/2026)
-- Esperado:
--  - Apenas 'Warner Bros. Pictures' deve aparecer (consumo acumulado de ~5013 horas)
--  - 'Netflix Studios' NÃO deve aparecer porque tem apenas 1000 horas consumidas.
RAISE NOTICE 'Executando Query: Cobrança dos Estúdios (Junho/2026)...';
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


-- C. Execução da Auditoria de Tráfego por Região
RAISE NOTICE 'Executando Query: Auditoria de Tráfego por Região...';
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


-- D. Execução da View de Conformidade com a LGPD
-- Esperado:
--  - IDs dos assinantes mascarados como Hash MD5 de 32 caracteres.
--  - Idades calculadas (ex: João Silva nasceu em 1985, deve ter 41 anos em 2026).
--  - CPF, Email e Nome Real ausentes da visualização.
RAISE NOTICE 'Executando Query: Seleção da View de Conformidade LGPD...';
SELECT * FROM vw_analise_engajamento_lgpd;
