-- ============================================================================
-- SCRIPT DE SEGURANÇA E POLÍTICA DE PRIVILÉGIOS (RBAC)
-- SGBD: PostgreSQL
-- ============================================================================

-- 1. Criação das Roles (Papéis / Usuários)
-- Nota: Em ambiente real, senhas devem ser gerenciadas via Secrets Manager ou variáveis de ambiente.

-- Usuário para a Aplicação Web (Leitura e Escrita das tabelas operacionais)
CREATE ROLE app_user WITH LOGIN PASSWORD 'StreamFlow_App_Secure_2026';

-- Usuário para o Analista de Auditoria (Leitura apenas de dados históricos e logs)
CREATE ROLE auditor_user WITH LOGIN PASSWORD 'StreamFlow_Audit_Read_2026';

-- 2. Concessão de Privilégios Básicos de Schema
GRANT USAGE ON SCHEMA public TO app_user;
GRANT USAGE ON SCHEMA public TO auditor_user;

-- 3. Configuração de Privilégios do Usuário da Aplicação (app_user)

-- O app_user precisa gerenciar assinantes, perfis, catálogo e séries (CRUD)
GRANT SELECT, INSERT, UPDATE, DELETE ON assinantes TO app_user;
GRANT SELECT, INSERT, UPDATE, DELETE ON perfis TO app_user;
GRANT SELECT, INSERT, UPDATE, DELETE ON produtoras TO app_user;
GRANT SELECT, INSERT, UPDATE, DELETE ON series TO app_user;
GRANT SELECT, INSERT, UPDATE, DELETE ON videos TO app_user;
GRANT SELECT, INSERT, UPDATE, DELETE ON filmes TO app_user;
GRANT SELECT, INSERT, UPDATE, DELETE ON episodios TO app_user;

-- O app_user deve APENAS ler e inserir no histórico de reprodução.
-- NENHUMA permissão de UPDATE ou DELETE é concedida nesta tabela para evitar manipulação de dados.
GRANT SELECT, INSERT ON historicos_reproducao TO app_user;

-- Permissão para utilizar as Sequences (necessário para os campos SERIAL auto-incremento)
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO app_user;


-- 4. Configuração de Privilégios do Analista de Auditoria (auditor_user)

-- O auditor deve ter permissão de LEITURA APENAS nos logs de reprodução.
GRANT SELECT ON historicos_reproducao TO auditor_user;

-- Para fins de auditoria, ele pode ler tabelas auxiliares do catálogo (vídeos, filmes, episódios, séries e produtoras)
GRANT SELECT ON videos TO auditor_user;
GRANT SELECT ON filmes TO auditor_user;
GRANT SELECT ON episodios TO auditor_user;
GRANT SELECT ON series TO auditor_user;
GRANT SELECT ON produtoras TO auditor_user;

-- Ele pode ver perfis para saber qual perfil assistiu a qual vídeo
GRANT SELECT ON perfis TO auditor_user;

-- SEGURANÇA E LGPD: Revoga explicitamente qualquer privilégio de leitura sobre a tabela de Assinantes.
-- O analista de auditoria NÃO deve visualizar dados pessoais sensíveis diretamente (CPF, E-mail, Nome).
-- Ele deverá acessar essas informações agregadas apenas através da View LGPD autorizada.
REVOKE ALL ON assinantes FROM auditor_user;
