-- DDL: Cria o contêiner lógico que armazenará todas as tabelas e dados
CREATE DATABASE hospital_db;

-- Define o banco 'hospital_db' como o contexto atual para os próximos comandos
USE hospital_db;

-- DDL: Define a estrutura da tabela de agendamentos (Dados Públicos/Operacionais)
CREATE TABLE agendamentos (
    id_agenda INT AUTO_INCREMENT PRIMARY KEY, -- Identificador único e automático
    paciente_nome VARCHAR(100),
    data_consulta DATE,
    hora_consulta TIME,
    medico_responsavel VARCHAR(100)
);

-- DDL: Define a estrutura da tabela de prontuários (Dados Sensíveis/Privados)
CREATE TABLE prontuarios (
    id_prontuario INT AUTO_INCREMENT PRIMARY KEY,
    paciente_nome VARCHAR(100),
    diagnostico TEXT,
    medicacao_prescrita TEXT,
    historico_familiar TEXT
);

-- DML: Insere registros iniciais para popular o banco e permitir testes de consulta
INSERT INTO agendamentos (paciente_nome, data_consulta, hora_consulta, medico_responsavel) VALUES
('Carlos Silva', '2026-05-10', '08:00', 'Dr. Arnaldo'),
('Maria Souza', '2026-05-10', '09:00', 'Dra. Beatriz');

INSERT INTO prontuarios (paciente_nome, diagnostico, medicacao_prescrita) VALUES
('Carlos Silva', 'Hipertensão Estágio 1', 'Enalapril 20mg'),
('Maria Souza', 'Enxaqueca Crônica', 'Sumatriptana 50mg');

-- DCL: Cria a identidade 'recepcao_central'. O '@localhost' limita o acesso apenas à máquina onde o banco reside.
CREATE USER 'recepcao_central'@'localhost' IDENTIFIED BY 'SenhaRec#123';

-- DCL: Cria a identidade do médico. 
CREATE USER 'medico_geral'@'localhost' IDENTIFIED BY 'Med@Secure!2026';

-- DCL: Concede apenas permissões de leitura e inserção em uma tabela específica (Princípio do Privilégio Mínimo)
GRANT SELECT ON hospital_db.agendamentos TO 'recepcao_central'@'localhost';
GRANT INSERT ON hospital_db.agendamentos TO 'recepcao_central'@'localhost';

-- DCL: Concede controle total sobre todos os objetos do banco 'hospital_db'
GRANT ALL PRIVILEGES ON hospital_db.* TO 'medico_geral'@'localhost';

-- DCL: Remove especificamente a permissão de deletar registros na tabela sensível
-- Isso impede que históricos médicos sejam apagados, garantindo a integridade dos dados.
REVOKE DELETE ON hospital_db.prontuarios FROM 'medico_geral'@'localhost';

-- COMANDO DE AUDITORIA: Exibe exatamente quais "crachás" de acesso a recepção possui
SHOW GRANTS FOR 'recepcao_central'@'localhost';

-- COMANDO DE AUDITORIA: Exibe as permissões do médico, incluindo a restrição do REVOKE
SHOW GRANTS FOR 'medico_geral'@'localhost';

-- Exibe os privilégios do usuário atualmente logado (geralmente o root)
SHOW GRANTS;
