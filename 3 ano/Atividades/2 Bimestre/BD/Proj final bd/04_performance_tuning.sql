-- ============================================================================
-- ARQUITETURA E TUNING DE PERFORMANCE - STREAMFLOW
-- SGBD: PostgreSQL
-- ============================================================================

-- ----------------------------------------------------------------------------
-- 1. Diagnóstico do Gargalo (Full Table Scan)
-- ----------------------------------------------------------------------------
-- Ao rodar a query do painel "Continuar Assistindo" em uma base massiva sem índices
-- adequados, o SGBD é obrigado a realizar um "Sequential Scan" (Full Table Scan)
-- na tabela 'historicos_reproducao', que é a maior tabela do sistema.
--
-- O custo da busca cresce linearmente O(N) com o número total de reproduções na base.
-- O plano de execução seria equivalente a:
--   -> Seq Scan on historicos_reproducao h (Filter: (perfil_id = 1 AND concluido = false))
--   -> Sort (Key: video_id, assistido_em DESC) -> Operação cara em disco/memória
--   -> Unique (Distinct ON)

-- ----------------------------------------------------------------------------
-- 2. Proposta de Solução: Criação de Índice Composto Parcial (Non-Clustered)
-- ----------------------------------------------------------------------------
-- Para reduzir o tempo de busca de segundos para milissegundos O(log N),
-- criaremos um índice específico com as seguintes características:
--
-- a) Composto: Ordena os dados fisicamente pelas colunas chave da busca.
-- b) Parcial (Filtro WHERE): Indexa APENAS registros onde 'concluido = FALSE'.
--
-- Vantagens do Índice Parcial:
-- - Redução drástica do tamanho físico do índice no disco.
-- - Menor consumo de memória RAM para cache (Buffer Pool).
-- - Inserções mais rápidas, já que o índice só é atualizado para vídeos em andamento.
-- ----------------------------------------------------------------------------

CREATE INDEX idx_historico_continuar_assistindo 
ON historicos_reproducao (perfil_id, video_id, assistido_em DESC)
WHERE concluido = FALSE;

-- ----------------------------------------------------------------------------
-- 3. Explicação Detalhada do Desenho do Índice
-- ----------------------------------------------------------------------------
-- * Cláusula WHERE (concluido = FALSE): 
--     Remove cerca de 80-90% das linhas do índice (pois a maioria das visualizações
--     eventualmente são concluídas).
-- * Primeira Coluna (perfil_id): 
--     Altamente seletiva. Permite um Index Seek direto para localizar apenas os
--     registros do perfil logado.
-- * Segunda e Terceira Colunas (video_id, assistido_em DESC):
--     Estão ordenadas exatamente no padrão exigido pelo DISTINCT ON e ORDER BY.
--     Isso elimina a necessidade de o banco fazer um Sort em memória (Avoids Sort Operation),
--     lendo as linhas na ordem exata e executando um Index Scan extremamente veloz.

-- ----------------------------------------------------------------------------
-- 4. Como Validar a Otimização (Plano de Execução)
-- ----------------------------------------------------------------------------
-- Execute o comando abaixo no console do PostgreSQL para validar:

-- EXPLAIN ANALYZE
-- SELECT * FROM (
--     SELECT DISTINCT ON (h.video_id)
--         h.video_id,
--         v.titulo,
--         h.tempo_assistido_segundos,
--         h.assistido_em
--     FROM historicos_reproducao h
--     JOIN videos v ON h.video_id = v.id
--     WHERE h.perfil_id = 1  -- Substituir pelo ID de teste
--       AND h.concluido = FALSE
--     ORDER BY h.video_id, h.assistido_em DESC
-- ) sub
-- ORDER BY assistido_em DESC;

-- Resultado esperado após o índice (Index Scan vs. Seq Scan):
-- O SGBD usará "Index Scan using idx_historico_continuar_assistindo".
-- O tempo de planejamento e execução (Execution Time) cairá para menos de 1ms.
