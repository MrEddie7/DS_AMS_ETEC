-- ============================================================================
-- SCRIPT DE DEFINIÇÃO DE DADOS (DDL) - STREAMFLOW
-- SGBD: PostgreSQL
-- ============================================================================

-- 1. Criação das Tabelas Principais (Semântica no Plural, PKs estritamente como 'id')

-- Tabela de Assinantes (Clientes principais da plataforma)
CREATE TABLE assinantes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    cpf VARCHAR(11) NOT NULL UNIQUE CONSTRAINT chk_cpf_length CHECK (LENGTH(cpf) = 11),
    data_nascimento DATE NOT NULL,
    uf CHAR(2) NOT NULL CONSTRAINT chk_uf_length CHECK (LENGTH(uf) = 2),
    dados_pagamento VARCHAR(255) NULL, -- Guardado mascarado ou tokenizado
    ativo BOOLEAN NOT NULL DEFAULT TRUE, -- Controle de Soft Delete para Assinante
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de Perfis (Cada assinante pode gerenciar até 5 perfis)
CREATE TABLE perfis (
    id SERIAL PRIMARY KEY,
    assinante_id INT NOT NULL,
    nome_exibicao VARCHAR(50) NOT NULL,
    preferencias TEXT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE, -- Controle de Soft Delete para Perfil
    CONSTRAINT fk_perfis_assinantes FOREIGN KEY (assinante_id) 
        REFERENCES assinantes(id) ON DELETE RESTRICT
);

-- Tabela de Produtoras (Estúdios de cinema e criadores de conteúdo)
CREATE TABLE produtoras (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de Séries (Agrupador de episódios)
CREATE TABLE series (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    sinopse TEXT NULL,
    produtora_id INT NOT NULL,
    CONSTRAINT fk_series_produtoras FOREIGN KEY (produtora_id) 
        REFERENCES produtoras(id) ON DELETE RESTRICT
);

-- Tabela Base de Vídeos (Estratégia Class Table Inheritance - CTI)
-- Contém os atributos comuns a filmes e episódios
CREATE TABLE videos (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    duracao_segundos INT NOT NULL CONSTRAINT chk_duracao_positiva CHECK (duracao_segundos > 0),
    tipo VARCHAR(10) NOT NULL CONSTRAINT chk_tipo_video CHECK (tipo IN ('filme', 'episodio')),
    ativo BOOLEAN NOT NULL DEFAULT TRUE -- Soft Delete para remoção lógica do catálogo
);

-- Tabela de Especialização: Filmes
CREATE TABLE filmes (
    id INT PRIMARY KEY,
    produtora_id INT NOT NULL,
    sinopse TEXT NULL,
    CONSTRAINT fk_filmes_videos FOREIGN KEY (id) 
        REFERENCES videos(id) ON DELETE RESTRICT,
    CONSTRAINT fk_filmes_produtoras FOREIGN KEY (produtora_id) 
        REFERENCES produtoras(id) ON DELETE RESTRICT
);

-- Tabela de Especialização: Episódios de Séries
CREATE TABLE episodios (
    id INT PRIMARY KEY,
    serie_id INT NOT NULL,
    numero_temporada INT NOT NULL CONSTRAINT chk_temporada_positiva CHECK (numero_temporada > 0),
    numero_episodio INT NOT NULL CONSTRAINT chk_episodio_positivo CHECK (numero_episodio > 0),
    sinopse TEXT NULL,
    CONSTRAINT fk_episodios_videos FOREIGN KEY (id) 
        REFERENCES videos(id) ON DELETE RESTRICT,
    CONSTRAINT fk_episodios_series FOREIGN KEY (serie_id) 
        REFERENCES series(id) ON DELETE RESTRICT
);

-- Tabela de Logs de Reprodução (Imutável, auditoria e BI)
CREATE TABLE historicos_reproducao (
    id SERIAL PRIMARY KEY,
    perfil_id INT NOT NULL,
    video_id INT NOT NULL,
    tempo_assistido_segundos INT NOT NULL DEFAULT 0 CONSTRAINT chk_tempo_assistido CHECK (tempo_assistido_segundos >= 0),
    concluido BOOLEAN NOT NULL DEFAULT FALSE,
    ip_conexao VARCHAR(45) NOT NULL, -- Suporta IPv4 e IPv6
    dispositivo VARCHAR(20) NOT NULL CONSTRAINT chk_dispositivo CHECK (dispositivo IN ('SmartTV', 'Smartphone', 'Web')),
    assistido_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_historico_perfis FOREIGN KEY (perfil_id) 
        REFERENCES perfis(id) ON DELETE RESTRICT,
    CONSTRAINT fk_historico_videos FOREIGN KEY (video_id) 
        REFERENCES videos(id) ON DELETE RESTRICT
);

-- ============================================================================
-- 2. Implementação das Regras de Negócio via Triggers
-- ============================================================================

-- A. Limite de Perfis: Máximo de 5 perfis ativos por assinante
CREATE OR REPLACE FUNCTION fn_verificar_limite_perfis()
RETURNS TRIGGER AS $$
DECLARE
    total_perfis_ativos INT;
BEGIN
    -- Conta quantos perfis ativos o assinante possui
    SELECT COUNT(*) INTO total_perfis_ativos
    FROM perfis
    WHERE assinante_id = NEW.assinante_id AND ativo = TRUE;

    IF total_perfis_ativos >= 5 AND NEW.ativo = TRUE THEN
        RAISE EXCEPTION 'Regra de Negócio Violada: O limite máximo de 5 perfis ativos por conta de assinante foi atingido.';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_limite_perfis
BEFORE INSERT OR UPDATE OF ativo ON perfis
FOR EACH ROW
EXECUTE FUNCTION fn_verificar_limite_perfis();


-- B. Imutabilidade dos Logs de Reprodução: Bloqueio de UPDATE e DELETE
CREATE OR REPLACE FUNCTION fn_impedir_alteracao_historico()
RETURNS TRIGGER AS $$
BEGIN
    RAISE EXCEPTION 'Segurança e Auditoria: Não é permitido modificar (UPDATE) ou remover (DELETE) registros da tabela de históricos de reprodução.';
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_impedir_alteracao_historico
BEFORE UPDATE OR DELETE ON historicos_reproducao
FOR EACH ROW
EXECUTE FUNCTION fn_impedir_alteracao_historico();
