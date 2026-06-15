-- ============================================================================
-- CONSULTAS ANALÍTICAS PARA BI - STREAMFLOW
-- SGBD: PostgreSQL
-- ============================================================================

-- ----------------------------------------------------------------------------
-- Relatório 1: Painel "Continuar Assistindo" da Tela Inicial
-- ----------------------------------------------------------------------------
-- Objetivo: Recuperar os vídeos que um perfil específico começou a assistir,
-- mas não concluiu, ordenando da visualização mais recente para a mais antiga.
-- Se o usuário iniciou o mesmo vídeo múltiplas vezes, exibe apenas o progresso
-- mais recente para evitar duplicatas.
-- ----------------------------------------------------------------------------

-- NOTA: Substitua o valor 1 pelo ID do perfil logado dinamicamente no backend.
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
    WHERE h.perfil_id = 1  -- Filtro de perfil dinâmico
      AND h.concluido = FALSE -- Filtra apenas reproduções não terminadas
    ORDER BY h.video_id, h.assistido_em DESC -- DISTINCT ON exige ordenar primeiro pelo campo de agrupamento
) sub
ORDER BY sub.assistido_em DESC; -- Ordena do acesso mais recente para o mais antigo


-- ----------------------------------------------------------------------------
-- Relatório 2: Relatório de Cobrança dos Estúdios/Produtoras (BI)
-- ----------------------------------------------------------------------------
-- Objetivo: Listar o nome de todas as produtoras parceiras, a soma de minutos
-- assistidos de seus vídeos num mês específico, filtrando apenas produtoras
-- com consumo acumulado acima de 5.000 horas.
-- ----------------------------------------------------------------------------

-- NOTA: Filtro de data parametrizável. Usando '2026-06-01' a '2026-07-01' como exemplo.
-- Otimização: O uso do COALESCE(f.produtora_id, s.produtora_id) permite um JOIN único
-- de alto desempenho com a tabela produtoras, sem usar cláusulas OR lentas.
SELECT 
    p.nome AS produtora_parceira,
    ROUND(SUM(h.tempo_assistido_segundos) / 60.0, 2) AS total_minutos_consumidos,
    ROUND(SUM(h.tempo_assistido_segundos) / 3600.0, 2) AS total_horas_consumidas
FROM historicos_reproducao h
JOIN videos v ON h.video_id = v.id
-- Joins com tabelas de especialização CTI
LEFT JOIN filmes f ON v.id = f.id
LEFT JOIN episodios e ON v.id = e.id
LEFT JOIN series s ON e.serie_id = s.id
-- Resolve a produtora dependendo se o vídeo é um filme ou episódio de série
JOIN produtoras p ON p.id = COALESCE(f.produtora_id, s.produtora_id)
WHERE h.assistido_em >= '2026-06-01 00:00:00' 
  AND h.assistido_em < '2026-07-01 00:00:00'
GROUP BY p.id, p.nome
HAVING SUM(h.tempo_assistido_segundos) / 3600.0 > 5000 -- Filtro de corte de 5.000 horas
ORDER BY total_horas_consumidas DESC;


-- ----------------------------------------------------------------------------
-- Relatório 3: Auditoria de Tráfego por Região
-- ----------------------------------------------------------------------------
-- Objetivo: Listar o tráfego total de acessos segmentado por Estado (UF) do
-- assinante e tipo de dispositivo.
-- Útil para o time de infraestrutura redimensionar CDNs e servidores regionais.
-- ----------------------------------------------------------------------------

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
