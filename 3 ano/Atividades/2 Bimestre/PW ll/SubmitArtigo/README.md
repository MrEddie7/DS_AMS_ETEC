# 🎓 SubmitArtigo - Plataforma Científica

<p align="center">
  <img src="https://raw.githubusercontent.com/laravel/art/master/logo-lockup/5%20SVG/2%20CMYK/1%20Full%20Color/laravel-logolockup-cmyk-red.svg" width="300" alt="Laravel Logo">
</p>

**SubmitArtigo** é uma plataforma web desenvolvida em PHP utilizando o framework **Laravel 12** para gerenciar submissões de artigos científicos. O sistema foi projetado para simular o fluxo de trabalho de uma revista acadêmica ou conferência científica, permitindo o cadastro de artigos por autores e o gerenciamento dos status de revisão por parte dos administradores/editores.

Este projeto foi desenvolvido como parte das atividades acadêmicas do 2º Bimestre na disciplina de **Programação Web II** do 3º ano do curso técnico de **Desenvolvimento de Sistemas** da **ETEC**.

---

## 🚀 Funcionalidades Principais

- **Páginas Institucionais**:
  - **Início**: Apresenta estatísticas acadêmicas simuladas e áreas científicas em destaque.
  - **Sobre a Revista**: Informações sobre o conselho editorial e a missão da revista.
  - **Contato**: Formulário de fale conosco e informações de contato.
- **Submissão de Artigos**:
  - Formulário completo para envio do trabalho científico.
  - Geração de protocolo único no formato: `SUB-YYYYMMDD-XXXX` (ex: `SUB-20260527-4821`).
  - Upload seguro de documentos (formatos aceitos: `PDF`, `DOC`, `DOCX`, com limite de até `10 MB`).
  - Validação rigorosa dos campos de entrada (título, autor principal, e-mail, resumo, etc.).
- **Painel Administrativo de Submissões**:
  - Listagem completa das submissões recebidas.
  - Busca integrada por título, autor principal ou número de protocolo.
  - Filtros avançados por **Área Científica** e **Status de Revisão**.
  - Visualização detalhada de cada artigo submetido.
  - Controle de fluxo e alteração de status do artigo: `Pendente`, `Em Revisão`, `Aceito` e `Rejeitado`.
  - Download seguro dos arquivos enviados, com renomeação organizada com base no protocolo e título do trabalho.

---

## 🛠️ Tecnologias Utilizadas

- **Backend**: [Laravel 12](https://laravel.com/) (PHP ^8.2)
- **Frontend**: Blade Template Engine, Tailwind CSS v4.0 e Estilos Customizados (`style.css`)
- **Banco de Dados**: SQLite (configuração local ágil e simplificada)
- **Ícones**: Font Awesome 6
- **Ferramentas de Build**: Vite v7

---

## 📂 Estrutura Principal do Projeto

Os principais arquivos e diretórios que compõem a lógica da plataforma são:

- 📝 [routes/web.php](file:///c:/Users/Admin/Documents/GitHub/DS_AMS_ETEC/3%20ano/Atividades/2%20Bimestre/PW%20ll/SubmitArtigo/routes/web.php) - Definição de rotas da aplicação e fallback 404.
- 📂 [app/Models/Submission.php](file:///c:/Users/Admin/Documents/GitHub/DS_AMS_ETEC/3%20ano/Atividades/2%20Bimestre/PW%20ll/SubmitArtigo/app/Models/Submission.php) - Modelo Eloquent da tabela de submissões.
- ⚙️ [app/Http/Controllers/SubmissionController.php](file:///c:/Users/Admin/Documents/GitHub/DS_AMS_ETEC/3%20ano/Atividades/2%20Bimestre/PW%20ll/SubmitArtigo/app/Http/Controllers/SubmissionController.php) - Lógica de negócio das submissões (cadastro, listagem, filtros, atualização de status e download).
- ⚙️ [app/Http/Controllers/PageController.php](file:///c:/Users/Admin/Documents/GitHub/DS_AMS_ETEC/3%20ano/Atividades/2%20Bimestre/PW%20ll/SubmitArtigo/app/Http/Controllers/PageController.php) - Gerenciamento de rotas e dados das páginas estáticas/institucionais.
- 🗄️ [database/migrations/..._create_submissions_table.php](file:///c:/Users/Admin/Documents/GitHub/DS_AMS_ETEC/3%20ano/Atividades/2%20Bimestre/PW%20ll/SubmitArtigo/database/migrations/2026_05_27_000000_create_submissions_table.php) - Migração estruturando os campos da tabela SQLite.
- 🎨 [resources/views/](file:///c:/Users/Admin/Documents/GitHub/DS_AMS_ETEC/3%20ano/Atividades/2%20Bimestre/PW%20ll/SubmitArtigo/resources/views) - Templates Blade divididos entre layouts, errors e submissões.
- 🎨 [public/css/style.css](file:///c:/Users/Admin/Documents/GitHub/DS_AMS_ETEC/3%20ano/Atividades/2%20Bimestre/PW%20ll/SubmitArtigo/public/css/style.css) - Estilos e regras de design customizados da plataforma.

---

## 💻 Como Instalar e Rodar o Projeto

Siga as etapas para configurar e rodar o projeto localmente:

### 1. Pré-requisitos
Certifique-se de possuir instalado em seu ambiente:
- **PHP** (Versão 8.2 ou superior)
- **Composer**
- **Node.js** e **NPM**

### 2. Configurar o Ambiente `.env`
O arquivo `.env` já deve estar criado com a conexão SQLite configurada:
```env
DB_CONNECTION=sqlite
```
O Laravel cria e gerencia o banco SQLite automaticamente (se não existir, o próprio Laravel se encarrega de criar o arquivo `database/database.sqlite` no primeiro comando).

### 3. Instalar Dependências e Executar Setup (Simples)
Você pode usar o comando de setup automatizado pelo composer:
```bash
composer setup
```
Este script configurado no projeto executa de forma sequencial:
1. `composer install` - Instala pacotes do PHP.
2. Cria o arquivo `.env` (caso inexistente).
3. `php artisan key:generate` - Cria a chave da aplicação.
4. `php artisan migrate --force` - Roda as migrations no banco de dados.
5. `npm install` - Instala pacotes frontend.
6. `npm run build` - Compila os assets iniciais.

### 4. Iniciar o Servidor de Desenvolvimento
Para iniciar todos os servidores necessários simultaneamente (Vite, Laravel Serve, etc.):
```bash
composer dev
```
Caso queira subir manualmente:
```bash
# Terminal 1: Servidor PHP Laravel
php artisan serve

# Terminal 2: Hot reload de Assets com Vite
npm run dev
```

Acesse o portal no seu navegador: [http://localhost:8000](http://localhost:8000) (ou a porta que o artisan disponibilizar).

---

## 🗄️ Modelo do Banco de Dados (`submissions`)

| Campo | Tipo | Descrição |
| :--- | :--- | :--- |
| `id` | BigInt (Autoincrement) | Chave primária do registro |
| `protocol` | String (Unique) | Número de protocolo gerado no formato `SUB-YYYYMMDD-XXXX` |
| `title` | String | Título do artigo científico |
| `author` | String | Nome do autor principal |
| `email` | String | E-mail para contato com o autor |
| `area` | String | Área científica selecionada |
| `coauthors` | String (Nullable) | Nome dos coautores do artigo |
| `abstract` | Text | Resumo científico completo |
| `file_path` | String | Caminho do arquivo armazenado no servidor |
| `status` | String | Status da revisão (`Pendente`, `Em Revisão`, `Aceito`, `Rejeitado`) |
| `created_at` / `updated_at` | Timestamps | Carimbo de data/hora de criação e alteração do registro |

---

## 🔒 Segurança e Tratamento de Arquivos

- **Arquivos Privados**: Para evitar vazamentos ou acessos não autorizados de artigos em revisão, os documentos são armazenados no disco local no diretório `storage/app/private/submissions`. O acesso é feito de maneira segura através de rotas do controller que conferem o banco de dados e fazem o download binário.
- **Validação Robustecida**: O projeto implementa regras estritas de MIME-type e tamanho de arquivos diretamente nas requests, prevenindo o upload de scripts maliciosos.
