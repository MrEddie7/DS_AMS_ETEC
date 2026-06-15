-- ============================================================================
-- CONFORMIDADE LGPD (SEGURANÇA DA INFORMAÇÃO) - STREAMFLOW
-- SGBD: PostgreSQL
-- ============================================================================

-- ----------------------------------------------------------------------------
-- 1. Criação da View Mascarada (vw_analise_engajamento_lgpd)
-- ----------------------------------------------------------------------------
-- Objetivo: Ocultar dados pessoais e sensíveis (CPF, Nome, E-mail, data_nascimento)
-- expor apenas Idade, Região (UF) e Métricas de Engajamento para o time de
-- marketing e analistas de dados.
--
-- O ID original é mascarado por meio de uma função Hash MD5 para permitir que
-- os analistas realizem cruzamentos/agrupamentos de dados históricos sem
-- conseguirem identificar a pessoa física correspondente.
-- ----------------------------------------------------------------------------

CREATE OR REPLACE VIEW vw_analise_engajamento_lgpd AS
SELECT
    -- ID mascarado via hash de sentido único para joins analíticos seguros
    md5(a.id::text) AS assinante_hash_id,
    
    -- Idade calculada dinamicamente, ocultando a data de nascimento real
    EXTRACT(YEAR FROM AGE(CURRENT_DATE, a.data_nascimento)) AS idade,
    
    -- UF do assinante para segmentação regional
    a.uf AS estado,
    
    -- Métricas de engajamento agregadas
    COUNT(DISTINCT p.id) AS total_perfis_ativos,
    COUNT(h.id) AS total_visualizacoes_iniciadas,
    COALESCE(ROUND(SUM(h.tempo_assistido_segundos) / 60.0, 2), 0.0) AS total_tempo_assistido_minutos,
    COALESCE(ROUND(AVG(h.tempo_assistido_segundos) / 60.0, 2), 0.0) AS tempo_medio_sessao_minutos
FROM assinantes a
LEFT JOIN perfis p ON a.id = p.assinante_id AND p.ativo = TRUE
LEFT JOIN historicos_reproducao h ON p.id = h.perfil_id
WHERE a.ativo = TRUE -- Apenas assinantes ativos na plataforma
GROUP BY a.id, a.data_nascimento, a.uf;

-- ----------------------------------------------------------------------------
-- 2. Criação do Perfil de Analista de Dados (Role & Grants)
-- ----------------------------------------------------------------------------
-- Cria a role específica para analistas de BI e marketing e concede acesso
-- estritamente à View, bloqueando qualquer acesso à tabela física 'assinantes'.

-- Criação da Role para Analistas de Dados
CREATE ROLE analista_dados_user WITH LOGIN PASSWORD 'StreamFlow_Analyst_Secure_2026';

-- Permissão de uso do schema público
GRANT USAGE ON SCHEMA public TO analista_dados_user;

-- Concessão de SELECT apenas na view de compliance LGPD
GRANT SELECT ON vw_analise_engajamento_lgpd TO analista_dados_user;

-- Garantia de leitura de tabelas de metadados não sensíveis (Catálogo)
GRANT SELECT ON videos TO analista_dados_user;
GRANT SELECT ON filmes TO analista_dados_user;
GRANT SELECT ON episodios TO analista_dados_user;
GRANT SELECT ON series TO analista_dados_user;
GRANT SELECT ON produtoras TO analista_dados_user;

-- BLOQUEIO EXPLÍCITO: Revoga explicitamente acesso à tabela original de assinantes
REVOKE ALL PRIVILEGES ON assinantes FROM analista_dados_user;
REVOKE ALL PRIVILEGES ON perfis FROM analista_dados_user; -- Oculta nomes de perfis individuais
