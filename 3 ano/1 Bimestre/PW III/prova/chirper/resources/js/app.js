import './bootstrap';

const csrfToken = document.head.querySelector('meta[name="csrf-token"]')?.content;
const apiBase = '/api';

const selectors = {
    clientList: '#client-list',
    barberList: '#barber-list',
    categoryList: '#category-list',
    serviceList: '#service-list',
    appointmentList: '#appointment-list',
    clientForm: '#client-form',
    barberForm: '#barber-form',
    categoryForm: '#category-form',
    serviceForm: '#service-form',
    appointmentForm: '#appointment-form',
    tabButtons: '[data-tab]',
    sectionPanels: '[data-section]',
    notification: '#notification',
};

const state = {
    clients: [],
    barbers: [],
    categories: [],
    services: [],
    appointments: [],
};

const editing = {
    entity: null,
    id: null,
};

const formatDateTimeLocal = (isoString) => {
    const date = new Date(isoString);
    if (Number.isNaN(date.getTime())) {
        return '';
    }
    const pad = (value) => String(value).padStart(2, '0');
    return `${date.getFullYear()}-${pad(date.getMonth() + 1)}-${pad(date.getDate())}T${pad(date.getHours())}:${pad(date.getMinutes())}`;
};

const setFormMode = (form, entity, id = null, values = null) => {
    editing.entity = entity;
    editing.id = id;
    form.dataset.editing = id ? 'true' : 'false';

    const submitButton = form.querySelector('button[type="submit"]');
    if (submitButton) {
        const originalText = submitButton.dataset.originalText || submitButton.textContent;
        submitButton.dataset.originalText = originalText;
        submitButton.textContent = id ? `Atualizar ${originalText.toLowerCase()}` : originalText;
    }

    const cancelButton = form.querySelector('[data-cancel]');
    if (cancelButton) {
        cancelButton.classList.toggle('hidden', !id);
    }

    if (values) {
        Object.entries(values).forEach(([key, value]) => {
            const field = form.querySelector(`[name="${key}"]`);
            if (field) {
                field.value = value ?? '';
            }
        });
    }
};

const resetFormMode = (form) => {
    setFormMode(form, null, null, null);
    form.reset();
};

const notify = (message, type = 'success') => {
    const notification = document.querySelector(selectors.notification);
    if (!notification) return;

    notification.textContent = message;
    notification.className = type === 'error'
        ? 'rounded-md bg-red-500/10 border border-red-500 text-red-700 p-4 text-sm'
        : 'rounded-md bg-emerald-500/10 border border-emerald-500 text-emerald-700 p-4 text-sm';
    notification.classList.remove('hidden');

    window.clearTimeout(window.__notificationTimeout);
    window.__notificationTimeout = window.setTimeout(() => {
        notification.classList.add('hidden');
    }, 4000);
};

const apiRequest = async (path, options = {}) => {
    const config = {
        headers: {
            'Accept': 'application/json',
            'X-CSRF-TOKEN': csrfToken,
            ...options.headers,
        },
        ...options,
    };

    if (config.body && typeof config.body !== 'string') {
        config.body = JSON.stringify(config.body);
        config.headers['Content-Type'] = 'application/json';
    }

    const response = await fetch(path, config);
    const payload = await response.json().catch(() => null);

    if (!response.ok) {
        throw payload?.message || payload || 'Erro ao conectar com o servidor.';
    }

    return payload;
};

const toggleSection = (section) => {
    document.querySelectorAll(selectors.sectionPanels).forEach(panel => {
        panel.classList.toggle('hidden', panel.dataset.section !== section);
    });

    document.querySelectorAll(selectors.tabButtons).forEach(button => {
        const isActive = button.dataset.tab === section;

        button.classList.toggle('bg-[#1b1b18]', isActive);
        button.classList.toggle('text-white', isActive);
        button.classList.toggle('bg-white', !isActive);
        button.classList.toggle('text-[#1b1b18]', !isActive);
        button.classList.toggle('border', !isActive);
    });
};

const renderRows = (items, mapper) => items.map(mapper).join('');

const refreshSelect = (selector, items, valueKey = 'id', labelKey = 'name') => {
    const select = document.querySelector(selector);
    if (!select) return;
    select.innerHTML = '<option value="">Selecione...</option>' + items.map(item => `<option value="${item[valueKey]}">${item[labelKey]}</option>`).join('');
};

const renderTable = (selector, rowsHtml) => {
    const table = document.querySelector(selector);
    if (table) table.innerHTML = rowsHtml;
};

const renderCards = (selector, items, cardMapper) => {
    const container = document.querySelector(selector);
    if (!container) return;

    if (items.length === 0) {
        container.innerHTML = `
            <div class="text-center py-8 text-slate-500">
                <svg class="w-12 h-12 mx-auto mb-4 text-slate-300" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M20 13V6a2 2 0 00-2-2H6a2 2 0 00-2 2v7m16 0v5a2 2 0 01-2 2H6a2 2 0 01-2-2v-5m16 0h-5.586a1 1 0 00-.707.293l-2.414 2.414a1 1 0 01-.707.293h-3.172a1 1 0 01-.707-.293l-2.414-2.414A1 1 0 006.586 13H4"></path>
                </svg>
                <p class="text-sm">Nenhum item cadastrado ainda.</p>
                <p class="text-xs mt-1">Use o formulário ao lado para adicionar o primeiro item.</p>
            </div>
        `;
    } else {
        container.innerHTML = items.map(cardMapper).join('');
    }
};

const loadClients = async () => {
    state.clients = await apiRequest(`${apiBase}/clients`);
    renderTable(selectors.clientList, renderRows(state.clients, client => `
        <tr class="border-b last:border-b-0">
            <td class="px-4 py-3">${client.name}</td>
            <td class="px-4 py-3">${client.phone}</td>
            <td class="px-4 py-3">${client.email}</td>
            <td class="px-4 py-3">${new Date(client.created_at).toLocaleString()}</td>
            <td class="px-4 py-3 text-right space-x-2">
                <button data-edit="client:${client.id}" class="text-slate-600 hover:text-slate-900">Editar</button>
                <button data-delete="client:${client.id}" class="text-red-600 hover:text-red-800">Excluir</button>
            </td>
        </tr>
    `));
    renderCards('#client-cards', state.clients, client => `
        <div class="bg-white rounded-lg border border-slate-200 p-4 shadow-sm hover:shadow-md transition-shadow">
            <div class="flex items-start justify-between">
                <div class="flex-1 min-w-0">
                    <h4 class="font-semibold text-slate-900 truncate">${client.name}</h4>
                    <p class="text-sm text-slate-600 mt-1 truncate">${client.email}</p>
                    <p class="text-sm text-slate-500 truncate">${client.phone}</p>
                </div>
                <div class="flex space-x-1 ml-2">
                    <button data-edit="client:${client.id}" class="text-slate-600 hover:text-slate-900 text-sm p-1" title="Editar">
                        <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z"></path>
                        </svg>
                    </button>
                    <button data-delete="client:${client.id}" class="text-red-600 hover:text-red-800 text-sm p-1" title="Excluir">
                        <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"></path>
                        </svg>
                    </button>
                </div>
            </div>
            <div class="mt-3 text-xs text-slate-400 border-t border-slate-100 pt-2">
                Cadastrado em ${new Date(client.created_at).toLocaleDateString('pt-BR')}
            </div>
        </div>
    `);
};

const loadBarbers = async () => {
    state.barbers = await apiRequest(`${apiBase}/barbers`);
    renderTable(selectors.barberList, renderRows(state.barbers, barber => `
        <tr class="border-b last:border-b-0">
            <td class="px-4 py-3">${barber.name}</td>
            <td class="px-4 py-3">${barber.cpf}</td>
            <td class="px-4 py-3">${new Date(barber.created_at).toLocaleString()}</td>
            <td class="px-4 py-3 text-right space-x-2">
                <button data-edit="barber:${barber.id}" class="text-slate-600 hover:text-slate-900">Editar</button>
                <button data-delete="barber:${barber.id}" class="text-red-600 hover:text-red-800">Excluir</button>
            </td>
        </tr>
    `));
    renderCards('#barber-cards', state.barbers, barber => `
        <div class="bg-white rounded-lg border border-slate-200 p-4 shadow-sm hover:shadow-md transition-shadow">
            <div class="flex items-start justify-between">
                <div class="flex-1 min-w-0">
                    <h4 class="font-semibold text-slate-900 truncate">${barber.name}</h4>
                    <p class="text-sm text-slate-600 mt-1">CPF: ${barber.cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4')}</p>
                    <div class="flex items-center mt-2">
                        <span class="inline-flex items-center px-2 py-1 rounded-full text-xs font-medium bg-blue-100 text-blue-800">
                            <svg class="w-3 h-3 mr-1" fill="currentColor" viewBox="0 0 20 20">
                                <path fill-rule="evenodd" d="M10 9a3 3 0 100-6 3 3 0 000 6zm-7 9a7 7 0 1114 0H3z" clip-rule="evenodd"></path>
                            </svg>
                            Barbeiro
                        </span>
                    </div>
                </div>
                <div class="flex space-x-1 ml-2">
                    <button data-edit="barber:${barber.id}" class="text-slate-600 hover:text-slate-900 text-sm p-1" title="Editar">
                        <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z"></path>
                        </svg>
                    </button>
                    <button data-delete="barber:${barber.id}" class="text-red-600 hover:text-red-800 text-sm p-1" title="Excluir">
                        <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"></path>
                        </svg>
                    </button>
                </div>
            </div>
            <div class="mt-3 text-xs text-slate-400 border-t border-slate-100 pt-2">
                Cadastrado em ${new Date(barber.created_at).toLocaleDateString('pt-BR')}
            </div>
        </div>
    `);
};

const loadCategories = async () => {
    state.categories = await apiRequest(`${apiBase}/categories`);
    renderTable(selectors.categoryList, renderRows(state.categories, category => `
        <tr class="border-b last:border-b-0">
            <td class="px-4 py-3">${category.name}</td>
            <td class="px-4 py-3">${new Date(category.created_at).toLocaleString()}</td>
            <td class="px-4 py-3 text-right space-x-2">
                <button data-edit="category:${category.id}" class="text-slate-600 hover:text-slate-900">Editar</button>
                <button data-delete="category:${category.id}" class="text-red-600 hover:text-red-800">Excluir</button>
            </td>
        </tr>
    `));
    renderCards('#category-cards', state.categories, category => `
        <div class="bg-white rounded-lg border border-slate-200 p-4 shadow-sm hover:shadow-md transition-shadow">
            <div class="flex items-start justify-between">
                <div class="flex-1 min-w-0">
                    <h4 class="font-semibold text-slate-900 truncate">${category.name}</h4>
                    <p class="text-sm text-slate-600 mt-1">Categoria de serviços</p>
                    <div class="flex items-center mt-2">
                        <span class="inline-flex items-center px-2 py-1 rounded-full text-xs font-medium bg-purple-100 text-purple-800">
                            <svg class="w-3 h-3 mr-1" fill="currentColor" viewBox="0 0 20 20">
                                <path d="M7 3a1 1 0 000 2h6a1 1 0 100-2H7zM4 7a1 1 0 011-1h10a1 1 0 110 2H5a1 1 0 01-1-1zM2 11a2 2 0 012-2h12a2 2 0 012 2v4a2 2 0 01-2 2H4a2 2 0 01-2-2v-4z"></path>
                            </svg>
                            Categoria
                        </span>
                    </div>
                </div>
                <div class="flex space-x-1 ml-2">
                    <button data-edit="category:${category.id}" class="text-slate-600 hover:text-slate-900 text-sm p-1" title="Editar">
                        <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z"></path>
                        </svg>
                    </button>
                    <button data-delete="category:${category.id}" class="text-red-600 hover:text-red-800 text-sm p-1" title="Excluir">
                        <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"></path>
                        </svg>
                    </button>
                </div>
            </div>
            <div class="mt-3 text-xs text-slate-400 border-t border-slate-100 pt-2">
                Criada em ${new Date(category.created_at).toLocaleDateString('pt-BR')}
            </div>
        </div>
    `);
};

const loadServices = async () => {
    state.services = await apiRequest(`${apiBase}/services`);
    renderTable(selectors.serviceList, renderRows(state.services, service => `
        <tr class="border-b last:border-b-0">
            <td class="px-4 py-3">${service.name}</td>
            <td class="px-4 py-3">${service.category?.name || '—'}</td>
            <td class="px-4 py-3">R$ ${parseFloat(service.price).toFixed(2)}</td>
            <td class="px-4 py-3">${new Date(service.created_at).toLocaleString()}</td>
            <td class="px-4 py-3 text-right space-x-2">
                <button data-edit="service:${service.id}" class="text-slate-600 hover:text-slate-900">Editar</button>
                <button data-delete="service:${service.id}" class="text-red-600 hover:text-red-800">Excluir</button>
            </td>
        </tr>
    `));
    renderCards('#service-cards', state.services, service => `
        <div class="bg-white rounded-lg border border-slate-200 p-4 shadow-sm hover:shadow-md transition-shadow">
            <div class="flex items-start justify-between">
                <div class="flex-1 min-w-0">
                    <h4 class="font-semibold text-slate-900 truncate">${service.name}</h4>
                    <p class="text-sm text-slate-600 mt-1 truncate">${service.category?.name || 'Sem categoria'}</p>
                    <div class="flex items-center justify-between mt-2">
                        <span class="text-lg font-bold text-green-600">R$ ${parseFloat(service.price).toFixed(2)}</span>
                        <span class="inline-flex items-center px-2 py-1 rounded-full text-xs font-medium bg-emerald-100 text-emerald-800">
                            <svg class="w-3 h-3 mr-1" fill="currentColor" viewBox="0 0 20 20">
                                <path fill-rule="evenodd" d="M4 4a2 2 0 00-2 2v4a2 2 0 002 2V6h10a2 2 0 00-2-2H4zm2 6a2 2 0 012-2h8a2 2 0 012 2v4a2 2 0 01-2 2H8a2 2 0 01-2-2v-4zm6 4a2 2 0 100-4 2 2 0 000 4z" clip-rule="evenodd"></path>
                            </svg>
                            Serviço
                        </span>
                    </div>
                </div>
                <div class="flex space-x-1 ml-2">
                    <button data-edit="service:${service.id}" class="text-slate-600 hover:text-slate-900 text-sm p-1" title="Editar">
                        <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z"></path>
                        </svg>
                    </button>
                    <button data-delete="service:${service.id}" class="text-red-600 hover:text-red-800 text-sm p-1" title="Excluir">
                        <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"></path>
                        </svg>
                    </button>
                </div>
            </div>
            <div class="mt-3 text-xs text-slate-400 border-t border-slate-100 pt-2">
                Cadastrado em ${new Date(service.created_at).toLocaleDateString('pt-BR')}
            </div>
        </div>
    `);
};

const loadAppointments = async () => {
    state.appointments = await apiRequest(`${apiBase}/appointments`);
    renderTable(selectors.appointmentList, renderRows(state.appointments, appointment => `
        <tr class="border-b last:border-b-0">
            <td class="px-4 py-3">${new Date(appointment.scheduled_at).toLocaleString()}</td>
            <td class="px-4 py-3">${appointment.client?.name || '—'}</td>
            <td class="px-4 py-3">${appointment.barber?.name || '—'}</td>
            <td class="px-4 py-3">${appointment.service?.name || '—'}</td>
            <td class="px-4 py-3">R$ ${parseFloat(appointment.paid_amount).toFixed(2)}</td>
            <td class="px-4 py-3 text-right space-x-2">
                <button data-edit="appointment:${appointment.id}" class="text-slate-600 hover:text-slate-900">Editar</button>
                <button data-delete="appointment:${appointment.id}" class="text-red-600 hover:text-red-800">Excluir</button>
            </td>
        </tr>
    `));
    renderCards('#appointment-cards', state.appointments, appointment => `
        <div class="bg-white rounded-lg border border-slate-200 p-4 shadow-sm hover:shadow-md transition-shadow">
            <div class="flex items-start justify-between">
                <div class="flex-1 min-w-0">
                    <h4 class="font-semibold text-slate-900 truncate">${appointment.service?.name || 'Serviço não definido'}</h4>
                    <div class="space-y-1 mt-2">
                        <p class="text-sm text-slate-600 truncate">
                            <span class="font-medium">Cliente:</span> ${appointment.client?.name || 'Não definido'}
                        </p>
                        <p class="text-sm text-slate-600 truncate">
                            <span class="font-medium">Barbeiro:</span> ${appointment.barber?.name || 'Não definido'}
                        </p>
                        <p class="text-sm font-medium text-blue-600 flex items-center">
                            <svg class="w-4 h-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z"></path>
                            </svg>
                            ${new Date(appointment.scheduled_at).toLocaleString('pt-BR')}
                        </p>
                        <div class="flex items-center justify-between mt-2">
                            <span class="text-lg font-bold text-green-600">R$ ${parseFloat(appointment.paid_amount).toFixed(2)}</span>
                            <span class="inline-flex items-center px-2 py-1 rounded-full text-xs font-medium bg-orange-100 text-orange-800">
                                <svg class="w-3 h-3 mr-1" fill="currentColor" viewBox="0 0 20 20">
                                    <path fill-rule="evenodd" d="M6 2a1 1 0 00-1 1v1H4a2 2 0 00-2 2v10a2 2 0 002 2h12a2 2 0 002-2V6a2 2 0 00-2-2h-1V3a1 1 0 10-2 0v1H7V3a1 1 0 00-1-1zm0 5a1 1 0 000 2h8a1 1 0 100-2H6z" clip-rule="evenodd"></path>
                                </svg>
                                Agendamento
                            </span>
                        </div>
                    </div>
                </div>
                <div class="flex space-x-1 ml-2">
                    <button data-edit="appointment:${appointment.id}" class="text-slate-600 hover:text-slate-900 text-sm p-1" title="Editar">
                        <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z"></path>
                        </svg>
                    </button>
                    <button data-delete="appointment:${appointment.id}" class="text-red-600 hover:text-red-800 text-sm p-1" title="Excluir">
                        <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"></path>
                        </svg>
                    </button>
                </div>
            </div>
        </div>
    `);
};

const refreshAll = async () => {
    await Promise.all([
        loadClients(),
        loadBarbers(),
        loadCategories(),
        loadServices(),
        loadAppointments(),
    ]);
    refreshSelect('#service-category', state.categories);
    refreshSelect('#appointment-client', state.clients, 'id', 'name');
    refreshSelect('#appointment-barber', state.barbers, 'id', 'name');
    refreshSelect('#appointment-service', state.services, 'id', 'name');
};

const bindForm = (formId, endpoint, formatData = data => data) => {
    const form = document.querySelector(formId);
    if (!form) return;

    form.addEventListener('submit', async (event) => {
        event.preventDefault();
        const formData = Object.fromEntries(new FormData(form).entries());
        const data = formatData(formData);
        const isEditing = editing.entity === endpoint && editing.id;
        const path = isEditing ? `${apiBase}/${endpoint}/${editing.id}` : `${apiBase}/${endpoint}`;
        const method = isEditing ? 'PATCH' : 'POST';

        try {
            await apiRequest(path, {
                method,
                body: data,
            });
            resetFormMode(form);
            notify(isEditing ? 'Registro atualizado com sucesso.' : 'Registro salvo com sucesso.');
            await refreshAll();
        } catch (error) {
            notify(error?.errors ? Object.values(error.errors).flat()[0] : error, 'error');
        }
    });

    const cancelButton = form.querySelector('[data-cancel]');
    if (cancelButton) {
        cancelButton.addEventListener('click', () => resetFormMode(form));
    }
};

const handleDelete = async (entity, id) => {
    if (!confirm('Deseja excluir este item?')) return;
    try {
        await apiRequest(`${apiBase}/${entity}/${id}`, { method: 'DELETE' });
        notify('Item excluído com sucesso.');
        await refreshAll();
    } catch (error) {
        notify(error, 'error');
    }
};

const delegateDeleteButtons = () => {
    document.body.addEventListener('click', async (event) => {
        const button = event.target.closest('[data-delete]');
        if (!button) return;
        const [entity, id] = button.dataset.delete.split(':');
        const routes = {
            client: 'clients',
            barber: 'barbers',
            category: 'categories',
            service: 'services',
            appointment: 'appointments',
        };

        await handleDelete(routes[entity] ?? `${entity}s`, id);
    });
};

const delegateEditButtons = () => {
    document.body.addEventListener('click', (event) => {
        const button = event.target.closest('[data-edit]');
        if (!button) return;
        const [entity, id] = button.dataset.edit.split(':');
        const routeState = {
            client: 'clients',
            barber: 'barbers',
            category: 'categories',
            service: 'services',
            appointment: 'appointments',
        };
        const stateKey = routeState[entity];
        if (!stateKey) return;

        const item = state[stateKey].find((record) => String(record.id) === String(id));
        if (!item) return;

        const form = document.querySelector(`#${entity}-form`);
        if (!form) return;

        const values = {
            name: item.name,
            phone: item.phone,
            email: item.email,
            cpf: item.cpf,
            category_id: item.category_id ?? item.category?.id ?? '',
            price: item.price,
            client_id: item.client_id ?? item.client?.id ?? '',
            barber_id: item.barber_id ?? item.barber?.id ?? '',
            service_id: item.service_id ?? item.service?.id ?? '',
            scheduled_at: item.scheduled_at ? formatDateTimeLocal(item.scheduled_at) : '',
            paid_amount: item.paid_amount,
        };

        setFormMode(form, routeState[entity], item.id, values);
    });
};

const init = async () => {
    delegateDeleteButtons();

    document.querySelectorAll(selectors.tabButtons).forEach(button => {
        button.addEventListener('click', () => toggleSection(button.dataset.tab));
    });

    toggleSection('clients');
    bindForm(selectors.clientForm, 'clients');
    bindForm(selectors.barberForm, 'barbers');
    bindForm(selectors.categoryForm, 'categories');
    bindForm(selectors.serviceForm, 'services', (data) => ({
        category_id: data.category_id,
        name: data.name,
        price: data.price,
    }));
    bindForm(selectors.appointmentForm, 'appointments', (data) => ({
        client_id: data.client_id,
        barber_id: data.barber_id,
        service_id: data.service_id,
        scheduled_at: data.scheduled_at,
        paid_amount: data.paid_amount,
    }));

    await refreshAll();
};

window.addEventListener('DOMContentLoaded', init);
