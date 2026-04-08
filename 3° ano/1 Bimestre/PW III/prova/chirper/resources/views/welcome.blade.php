<!DOCTYPE html>
<html lang="{{ str_replace('_', '-', app()->getLocale()) }}">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="csrf-token" content="{{ csrf_token() }}">

        <title>Corte & Barba Pro</title>

        <!-- Fonts -->
        <link rel="preconnect" href="https://fonts.bunny.net">
        <link href="https://fonts.bunny.net/css?family=instrument-sans:400,500,600" rel="stylesheet" />

        @vite(['resources/css/app.css', 'resources/js/app.js'])
    </head>
    <body class="bg-slate-50 text-slate-900 min-h-screen">
        <div class="max-w-7xl mx-auto p-6 lg:p-10">
            <header class="mb-10">
                <div class="flex flex-col gap-4 md:flex-row md:items-end md:justify-between">
                    <div>
                        <p class="text-sm font-semibold uppercase tracking-[0.3em] text-rose-600">Corte & Barba Pro</p>
                        <h1 class="mt-3 text-4xl font-semibold text-slate-950">Painel de gestão da barbearia</h1>
                        <p class="mt-3 max-w-2xl text-slate-600">Cadastre clientes, barbeiros, categorias, serviços e agendamentos em um único lugar. O sistema garante e-mail único, CPF único, preços não negativos e checa existência de cliente, barbeiro e serviço ao marcar um atendimento.</p>
                    </div>
                    <div class="flex flex-wrap gap-3">
                        <button data-tab="clients" class="rounded-lg bg-[#1b1b18] px-4 py-2 text-sm font-medium text-white transition">Clientes</button>
                        <button data-tab="barbers" class="rounded-lg border border-slate-300 bg-white px-4 py-2 text-sm font-medium text-[#1b1b18] transition hover:bg-slate-100">Barbeiros</button>
                        <button data-tab="categories" class="rounded-lg border border-slate-300 bg-white px-4 py-2 text-sm font-medium text-[#1b1b18] transition hover:bg-slate-100">Categorias</button>
                        <button data-tab="services" class="rounded-lg border border-slate-300 bg-white px-4 py-2 text-sm font-medium text-[#1b1b18] transition hover:bg-slate-100">Serviços</button>
                        <button data-tab="appointments" class="rounded-lg border border-slate-300 bg-white px-4 py-2 text-sm font-medium text-[#1b1b18] transition hover:bg-slate-100">Agendamentos</button>
                    </div>
                </div>
            </header>

            <div id="notification" class="hidden mb-8 rounded-md bg-emerald-500/10 border border-emerald-500 text-emerald-700 p-4 text-sm"></div>

            <main class="space-y-10">
                <section data-section="clients" class="bg-white rounded-3xl border border-slate-200 shadow-sm p-6">
                    <div class="grid gap-8 grid-cols-1 md:grid-cols-[1fr_1fr] xl:grid-cols-[1fr_1fr_1fr]">
                        <div>
                            <h2 class="text-xl font-semibold text-slate-900">Clientes</h2>
                            <p class="mt-2 text-sm text-slate-600">Cadastre os clientes com nome, telefone e e-mail único.</p>

                            <div class="mt-6 overflow-x-auto rounded-3xl border border-slate-200 bg-slate-50">
                                <table class="w-full text-left text-sm text-slate-800">
                                    <thead class="bg-slate-100 text-slate-500">
                                        <tr>
                                            <th class="px-4 py-3">Nome</th>
                                            <th class="px-4 py-3">Telefone</th>
                                            <th class="px-4 py-3">E-mail</th>
                                            <th class="px-4 py-3">Cadastro</th>
                                            <th class="px-4 py-3"></th>
                                        </tr>
                                    </thead>
                                    <tbody id="client-list"></tbody>
                                </table>
                            </div>
                        </div>
                        <div>
                            <h3 class="text-lg font-semibold text-slate-900">Clientes Cadastrados</h3>
                            <p class="mt-2 text-sm text-slate-600">Visualização dos clientes ativos.</p>

                            <div id="client-cards" class="mt-6 space-y-4 overflow-y-auto rounded-3xl border border-slate-200 bg-slate-50 p-4 min-h-[20rem]">
                                <!-- Cards dos clientes serão inseridos aqui via JavaScript -->
                            </div>
                        </div>
                        <form id="client-form" data-entity="clients" class="space-y-4 rounded-3xl border border-slate-200 bg-slate-50 p-6">
                            <h3 class="text-lg font-semibold text-slate-900">Novo cliente</h3>
                            <label class="block">
                                <span class="text-sm text-slate-700">Nome</span>
                                <input name="name" required class="mt-2 w-full rounded-2xl border border-slate-300 bg-white px-4 py-3 text-sm outline-none focus:border-slate-500" />
                            </label>
                            <label class="block">
                                <span class="text-sm text-slate-700">Telefone</span>
                                <input name="phone" required class="mt-2 w-full rounded-2xl border border-slate-300 bg-white px-4 py-3 text-sm outline-none focus:border-slate-500" />
                            </label>
                            <label class="block">
                                <span class="text-sm text-slate-700">E-mail</span>
                                <input name="email" type="email" required class="mt-2 w-full rounded-2xl border border-slate-300 bg-white px-4 py-3 text-sm outline-none focus:border-slate-500" />
                            </label>
                            <div class="flex flex-wrap gap-3">
                                <button type="submit" data-original-text="Salvar cliente" class="inline-flex items-center justify-center rounded-2xl bg-slate-950 px-4 py-3 text-sm font-semibold text-white transition hover:bg-slate-800">Salvar cliente</button>
                                <button type="button" data-cancel class="hidden inline-flex items-center justify-center rounded-2xl border border-slate-300 bg-white px-4 py-3 text-sm font-semibold text-slate-700 transition hover:bg-slate-100">Cancelar</button>
                            </div>
                        </form>
                    </div>
                </section>

                <section data-section="barbers" class="hidden bg-white rounded-3xl border border-slate-200 shadow-sm p-6">
                    <div class="grid gap-8 grid-cols-1 md:grid-cols-[1fr_1fr] xl:grid-cols-[1fr_1fr_1fr]">
                        <div>
                            <h2 class="text-xl font-semibold text-slate-900">Barbeiros</h2>
                            <p class="mt-2 text-sm text-slate-600">O CPF deve ter 11 dígitos e não pode se repetir.</p>

                            <div class="mt-6 overflow-x-auto rounded-3xl border border-slate-200 bg-slate-50">
                                <table class="w-full text-left text-sm text-slate-800">
                                    <thead class="bg-slate-100 text-slate-500">
                                        <tr>
                                            <th class="px-4 py-3">Nome</th>
                                            <th class="px-4 py-3">CPF</th>
                                            <th class="px-4 py-3">Cadastro</th>
                                            <th class="px-4 py-3"></th>
                                        </tr>
                                    </thead>
                                    <tbody id="barber-list"></tbody>
                                </table>
                            </div>
                        </div>
                        <div>
                            <h3 class="text-lg font-semibold text-slate-900">Barbeiros Cadastrados</h3>
                            <p class="mt-2 text-sm text-slate-600">Visualização dos barbeiros ativos.</p>

                            <div id="barber-cards" class="mt-6 space-y-4 max-h-96 overflow-y-auto rounded-3xl border border-slate-200 bg-slate-50 p-4 min-h-[20rem]">
                                <!-- Cards dos barbeiros serão inseridos aqui via JavaScript -->
                            </div>
                        </div>
                        <form id="barber-form" data-entity="barbers" class="space-y-4 rounded-3xl border border-slate-200 bg-slate-50 p-6">
                            <h3 class="text-lg font-semibold text-slate-900">Novo barbeiro</h3>
                            <label class="block">
                                <span class="text-sm text-slate-700">Nome</span>
                                <input name="name" required class="mt-2 w-full rounded-2xl border border-slate-300 bg-white px-4 py-3 text-sm outline-none focus:border-slate-500" />
                            </label>
                            <label class="block">
                                <span class="text-sm text-slate-700">CPF (11 dígitos)</span>
                                <input name="cpf" required maxlength="11" minlength="11" pattern="\d{11}" class="mt-2 w-full rounded-2xl border border-slate-300 bg-white px-4 py-3 text-sm outline-none focus:border-slate-500" />
                            </label>
                            <div class="flex flex-wrap gap-3">
                                <button type="submit" data-original-text="Salvar barbeiro" class="inline-flex items-center justify-center rounded-2xl bg-slate-950 px-4 py-3 text-sm font-semibold text-white transition hover:bg-slate-800">Salvar barbeiro</button>
                                <button type="button" data-cancel class="hidden inline-flex items-center justify-center rounded-2xl border border-slate-300 bg-white px-4 py-3 text-sm font-semibold text-slate-700 transition hover:bg-slate-100">Cancelar</button>
                            </div>
                        </form>
                    </div>
                </section>

                <section data-section="categories" class="hidden bg-white rounded-3xl border border-slate-200 shadow-sm p-6">
                    <div class="grid gap-8 grid-cols-1 md:grid-cols-[1fr_1fr] xl:grid-cols-[1fr_1fr_1fr]">
                        <div>
                            <h2 class="text-xl font-semibold text-slate-900">Categorias</h2>
                            <p class="mt-2 text-sm text-slate-600">Defina categorias como Cabelo, Barba ou Estética.</p>

                            <div class="mt-6 overflow-x-auto rounded-3xl border border-slate-200 bg-slate-50">
                                <table class="w-full text-left text-sm text-slate-800">
                                    <thead class="bg-slate-100 text-slate-500">
                                        <tr>
                                            <th class="px-4 py-3">Nome</th>
                                            <th class="px-4 py-3">Cadastro</th>
                                            <th class="px-4 py-3"></th>
                                        </tr>
                                    </thead>
                                    <tbody id="category-list"></tbody>
                                </table>
                            </div>
                        </div>
                        <div>
                            <h3 class="text-lg font-semibold text-slate-900">Categorias Cadastradas</h3>
                            <p class="mt-2 text-sm text-slate-600">Visualização das categorias disponíveis.</p>

                            <div id="category-cards" class="mt-6 space-y-4 max-h-96 overflow-y-auto rounded-3xl border border-slate-200 bg-slate-50 p-4 min-h-[20rem]">
                                <!-- Cards das categorias serão inseridos aqui via JavaScript -->
                            </div>
                        </div>
                        <form id="category-form" data-entity="categories" class="space-y-4 rounded-3xl border border-slate-200 bg-slate-50 p-6">
                            <h3 class="text-lg font-semibold text-slate-900">Nova categoria</h3>
                            <label class="block">
                                <span class="text-sm text-slate-700">Nome</span>
                                <input name="name" required class="mt-2 w-full rounded-2xl border border-slate-300 bg-white px-4 py-3 text-sm outline-none focus:border-slate-500" />
                            </label>
                            <div class="flex flex-wrap gap-3">
                                <button type="submit" data-original-text="Salvar categoria" class="inline-flex items-center justify-center rounded-2xl bg-slate-950 px-4 py-3 text-sm font-semibold text-white transition hover:bg-slate-800">Salvar categoria</button>
                                <button type="button" data-cancel class="hidden inline-flex items-center justify-center rounded-2xl border border-slate-300 bg-white px-4 py-3 text-sm font-semibold text-slate-700 transition hover:bg-slate-100">Cancelar</button>
                            </div>
                        </form>
                    </div>
                </section>

                <section data-section="services" class="hidden bg-white rounded-3xl border border-slate-200 shadow-sm p-6">
                    <div class="grid gap-8 grid-cols-1 md:grid-cols-[1fr_1fr] xl:grid-cols-[1fr_1fr_1fr]">
                        <div>
                            <h2 class="text-xl font-semibold text-slate-900">Serviços</h2>
                            <p class="mt-2 text-sm text-slate-600">Cadastre serviços com categoria e preço fixo.</p>

                            <div class="mt-6 overflow-x-auto rounded-3xl border border-slate-200 bg-slate-50">
                                <table class="w-full text-left text-sm text-slate-800">
                                    <thead class="bg-slate-100 text-slate-500">
                                        <tr>
                                            <th class="px-4 py-3">Serviço</th>
                                            <th class="px-4 py-3">Categoria</th>
                                            <th class="px-4 py-3">Preço</th>
                                            <th class="px-4 py-3">Cadastro</th>
                                            <th class="px-4 py-3"></th>
                                        </tr>
                                    </thead>
                                    <tbody id="service-list"></tbody>
                                </table>
                            </div>
                        </div>
                        <div>
                            <h3 class="text-lg font-semibold text-slate-900">Serviços Cadastrados</h3>
                            <p class="mt-2 text-sm text-slate-600">Visualização dos serviços disponíveis.</p>

                            <div id="service-cards" class="mt-6 space-y-4 max-h-96 overflow-y-auto rounded-3xl border border-slate-200 bg-slate-50 p-4 min-h-[20rem]">
                                <!-- Cards dos serviços serão inseridos aqui via JavaScript -->
                            </div>
                        </div>
                        <form id="service-form" data-entity="services" class="space-y-4 rounded-3xl border border-slate-200 bg-slate-50 p-6">
                            <h3 class="text-lg font-semibold text-slate-900">Novo serviço</h3>
                            <label class="block">
                                <span class="text-sm text-slate-700">Categoria do serviço</span>
                                <select id="service-category" name="category_id" required class="mt-2 w-full rounded-2xl border border-slate-300 bg-white px-4 py-3 text-sm outline-none focus:border-slate-500">
                                    <option value="">Selecione a categoria</option>
                                </select>
                            </label>
                            <label class="block">
                                <span class="text-sm text-slate-700">Nome do serviço</span>
                                <input name="name" required class="mt-2 w-full rounded-2xl border border-slate-300 bg-white px-4 py-3 text-sm outline-none focus:border-slate-500" />
                            </label>
                            <label class="block">
                                <span class="text-sm text-slate-700">Preço</span>
                                <input name="price" type="number" min="0" step="0.01" required class="mt-2 w-full rounded-2xl border border-slate-300 bg-white px-4 py-3 text-sm outline-none focus:border-slate-500" />
                            </label>
                            <div class="flex flex-wrap gap-3">
                                <button type="submit" data-original-text="Salvar serviço" class="inline-flex items-center justify-center rounded-2xl bg-slate-950 px-4 py-3 text-sm font-semibold text-white transition hover:bg-slate-800">Salvar serviço</button>
                                <button type="button" data-cancel class="hidden inline-flex items-center justify-center rounded-2xl border border-slate-300 bg-white px-4 py-3 text-sm font-semibold text-slate-700 transition hover:bg-slate-100">Cancelar</button>
                            </div>
                        </form>
                    </div>
                </section>

                <section data-section="appointments" class="hidden bg-white rounded-3xl border border-slate-200 shadow-sm p-6">
                    <div class="grid gap-8 grid-cols-1 md:grid-cols-[1fr_1fr] xl:grid-cols-[1fr_1fr_1fr]">
                        <div>
                            <h2 class="text-xl font-semibold text-slate-900">Agendamentos</h2>
                            <p class="mt-2 text-sm text-slate-600">Marque dia e hora, escolha cliente, barbeiro e serviço e registre o valor pago.</p>

                            <div class="mt-6 overflow-x-auto rounded-3xl border border-slate-200 bg-slate-50">
                                <table class="w-full text-left text-sm text-slate-800">
                                    <thead class="bg-slate-100 text-slate-500">
                                        <tr>
                                            <th class="px-4 py-3">Data</th>
                                            <th class="px-4 py-3">Cliente</th>
                                            <th class="px-4 py-3">Barbeiro</th>
                                            <th class="px-4 py-3">Serviço</th>
                                            <th class="px-4 py-3">Pago</th>
                                            <th class="px-4 py-3"></th>
                                        </tr>
                                    </thead>
                                    <tbody id="appointment-list"></tbody>
                                </table>
                            </div>
                        </div>
                        <div>
                            <h3 class="text-lg font-semibold text-slate-900">Agendamentos Ativos</h3>
                            <p class="mt-2 text-sm text-slate-600">Visualização dos próximos agendamentos.</p>

                            <div id="appointment-cards" class="mt-6 space-y-4 max-h-96 overflow-y-auto rounded-3xl border border-slate-200 bg-slate-50 p-4 min-h-[20rem]">
                                <!-- Cards dos agendamentos serão inseridos aqui via JavaScript -->
                            </div>
                        </div>
                        <form id="appointment-form" data-entity="appointments" class="space-y-4 rounded-3xl border border-slate-200 bg-slate-50 p-6">
                            <h3 class="text-lg font-semibold text-slate-900">Novo agendamento</h3>
                            <label class="block">
                                <span class="text-sm text-slate-700">Cliente</span>
                                <select id="appointment-client" name="client_id" required class="mt-2 w-full rounded-2xl border border-slate-300 bg-white px-4 py-3 text-sm outline-none focus:border-slate-500"></select>
                            </label>
                            <label class="block">
                                <span class="text-sm text-slate-700">Barbeiro</span>
                                <select id="appointment-barber" name="barber_id" required class="mt-2 w-full rounded-2xl border border-slate-300 bg-white px-4 py-3 text-sm outline-none focus:border-slate-500"></select>
                            </label>
                            <label class="block">
                                <span class="text-sm text-slate-700">Serviço</span>
                                <select id="appointment-service" name="service_id" required class="mt-2 w-full rounded-2xl border border-slate-300 bg-white px-4 py-3 text-sm outline-none focus:border-slate-500"></select>
                            </label>
                            <label class="block">
                                <span class="text-sm text-slate-700">Data e hora</span>
                                <input name="scheduled_at" type="datetime-local" required class="mt-2 w-full rounded-2xl border border-slate-300 bg-white px-4 py-3 text-sm outline-none focus:border-slate-500" />
                            </label>
                            <label class="block">
                                <span class="text-sm text-slate-700">Valor pago</span>
                                <input name="paid_amount" type="number" min="0" step="0.01" required class="mt-2 w-full rounded-2xl border border-slate-300 bg-white px-4 py-3 text-sm outline-none focus:border-slate-500" />
                            </label>
                            <div class="flex flex-wrap gap-3">
                                <button type="submit" data-original-text="Salvar agendamento" class="inline-flex items-center justify-center rounded-2xl bg-slate-950 px-4 py-3 text-sm font-semibold text-white transition hover:bg-slate-800">Salvar agendamento</button>
                                <button type="button" data-cancel class="hidden inline-flex items-center justify-center rounded-2xl border border-slate-300 bg-white px-4 py-3 text-sm font-semibold text-slate-700 transition hover:bg-slate-100">Cancelar</button>
                            </div>
                        </form>
                    </div>
                </section>
            </main>
        </div>
    </body>
</html>
