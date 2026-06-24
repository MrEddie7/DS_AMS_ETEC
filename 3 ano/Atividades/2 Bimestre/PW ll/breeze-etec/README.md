# BreezeEtec

Aplicação Laravel 12 com Inertia.js e Vue 3, criada como um projeto de front-end e back-end integrado. O projeto inclui rotas públicas, autenticação, dashboard, formulários, notas e gerenciamento de perfil.

## Visão geral

- Base Laravel 12 com PHP 8.2.
- Front-end construído com Inertia.js, Vue 3 e Tailwind CSS.
- Bundle de ativos com Vite.
- Autenticação pronta usando Breeze.
- Rotas públicas para home, cursos, eventos e formulário de contato.
- Rotas protegidas para dashboard, perfil e gerenciamento de notas.

## Requisitos

- PHP 8.2+
- Composer
- Node.js / npm
- MySQL ou outra conexão compatível configurada em `.env`

## Instalação

1. Copie o arquivo de ambiente:

   ```bash
   cp .env.example .env
   ```

2. Instale dependências PHP:

   ```bash
   composer install
   ```

3. Instale dependências Node:

   ```bash
   npm install
   ```

4. Gere a chave de aplicativo Laravel:

   ```bash
   php artisan key:generate
   ```

5. Configure o banco de dados em `.env`:

   - `DB_CONNECTION`
   - `DB_HOST`
   - `DB_PORT`
   - `DB_DATABASE`
   - `DB_USERNAME`
   - `DB_PASSWORD`

6. Execute as migrações:

   ```bash
   php artisan migrate
   ```

## Inicialização

### Iniciar o servidor Laravel

```bash
php artisan serve --host=127.0.0.1 --port=8000
```

### Construir os ativos front-end

```bash
npm run build
```

### Iniciar em modo de desenvolvimento

```bash
npm run dev
```

## Dependências importantes

- `laravel/framework` — estrutura principal Laravel.
- `inertiajs/inertia-laravel` — integração Inertia para Laravel.
- `@inertiajs/vue3` — adaptador Inertia para Vue 3.
- `vite` e `laravel-vite-plugin` — bundler e plugin Vite.
- `tailwindcss` — utilitários CSS.
- `laravel/breeze` — autenticação e scaffold de frontend.

## Rotas principais

- `/` — página inicial.
- `/cursos` — listagem de cursos.
- `/eventos` — listagem de eventos.
- `/formulario` — formulário de contato.
- `/dashboard` — painel protegido.
- `/profile` — edição de perfil (autenticado).
- `/dashboard/grades` — gerenciamento de notas (autenticado).

## Estrutura de arquivos

- `app/Http/Controllers` — controladores do Laravel.
- `routes/web.php` — rotas web principais.
- `resources/js` — código Vue e Inertia.
- `resources/views/app.blade.php` — layout principal que injeta o Inertia.
- `public/build` — arquivos gerados pelo Vite.
- `database/migrations` — migrações do banco de dados.

## Notas

- Se `@vite(['resources/js/app.ts'])` falhar com `Vite manifest not found`, execute `npm run build`.
- Se ocorrer erro de chave de aplicativo, execute `php artisan key:generate`.
- Mantenha o servidor rodando e a pasta `public/build` atualizada ao trabalhar no front-end.

## Comandos úteis

- `composer install` — instala dependências PHP.
- `npm install` — instala dependências Node.
- `npm run build` — compila o front-end para produção.
- `npm run dev` — inicia o servidor Vite em modo de desenvolvimento.
- `php artisan migrate` — executa migrações.
- `php artisan serve` — inicia o servidor Laravel.
- `php artisan key:generate` — gera chave de criptografia.
