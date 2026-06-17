<script setup lang="ts">
import { Head, Link, useForm } from '@inertiajs/vue3';

const form = useForm({
    name: '',
    email: '',
    message: '',
});

const submit = () => {
    form.post(route('contact.submit'), {
        onFinish: () => {
            if (!form.hasErrors) {
                form.reset();
            }
        },
    });
};
</script>

<template>
    <Head title="Contato - ETEC Zona Leste" />

    <div class="min-h-screen bg-gray-50 dark:bg-gray-900">
        <!-- Navigation -->
        <nav class="fixed top-0 w-full z-50 bg-white dark:bg-gray-800 shadow-sm">
            <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                <div class="flex justify-between items-center h-16">
                    <Link href="/" class="text-2xl font-bold text-indigo-600 dark:text-indigo-400">
                        ETEC Zona Leste
                    </Link>
                    <div class="hidden md:flex space-x-8">
                        <Link href="/" class="text-gray-700 dark:text-gray-300 hover:text-indigo-600 dark:hover:text-indigo-400 transition">
                            Home
                        </Link>
                        <Link href="/cursos" class="text-gray-700 dark:text-gray-300 hover:text-indigo-600 dark:hover:text-indigo-400 transition">
                            Cursos
                        </Link>
                        <Link href="/eventos" class="text-gray-700 dark:text-gray-300 hover:text-indigo-600 dark:hover:text-indigo-400 transition">
                            Eventos
                        </Link>
                        <Link href="/formulario" class="text-indigo-600 dark:text-indigo-400 font-semibold border-b-2 border-indigo-600">
                            Contato
                        </Link>
                    </div>
                </div>
            </div>
        </nav>

        <!-- Header -->
        <section class="pt-32 pb-16 px-4 sm:px-6 lg:px-8 bg-gradient-to-r from-green-600 to-indigo-600 text-white">
            <div class="max-w-7xl mx-auto">
                <h1 class="text-5xl font-bold mb-4">Entre em Contato</h1>
                <p class="text-xl text-green-100">Tire suas dúvidas e saiba mais sobre nossos cursos e programas</p>
            </div>
        </section>

        <!-- Contact Form Section -->
        <section class="py-20 px-4 sm:px-6 lg:px-8">
            <div class="max-w-2xl mx-auto">
                <!-- Success Message -->
                <div v-if="$page.props.flash?.success" class="mb-6 p-4 bg-green-50 dark:bg-green-900 border border-green-200 dark:border-green-700 text-green-800 dark:text-green-200 rounded-lg">
                    {{ $page.props.flash.success }}
                </div>

                <form @submit.prevent="submit" class="bg-white dark:bg-gray-800 rounded-lg shadow-md p-8">
                    <!-- CSRF Protection: This form is protected by Inertia's automatic CSRF token handling -->
                    <!-- The token is automatically included in POST requests -->

                    <!-- Name Field -->
                    <div class="mb-6">
                        <label for="name" class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">
                            Nome Completo *
                        </label>
                        <input
                            id="name"
                            v-model="form.name"
                            type="text"
                            required
                            class="w-full px-4 py-2 border border-gray-300 dark:border-gray-600 rounded-lg focus:outline-none focus:border-indigo-500 dark:bg-gray-700 dark:text-white"
                            :class="{ 'border-red-500': form.errors.name }"
                        />
                        <p v-if="form.errors.name" class="mt-1 text-sm text-red-600 dark:text-red-400">
                            {{ form.errors.name }}
                        </p>
                    </div>

                    <!-- Email Field -->
                    <div class="mb-6">
                        <label for="email" class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">
                            Email *
                        </label>
                        <input
                            id="email"
                            v-model="form.email"
                            type="email"
                            required
                            class="w-full px-4 py-2 border border-gray-300 dark:border-gray-600 rounded-lg focus:outline-none focus:border-indigo-500 dark:bg-gray-700 dark:text-white"
                            :class="{ 'border-red-500': form.errors.email }"
                        />
                        <p v-if="form.errors.email" class="mt-1 text-sm text-red-600 dark:text-red-400">
                            {{ form.errors.email }}
                        </p>
                    </div>

                    <!-- Message Field -->
                    <div class="mb-6">
                        <label for="message" class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">
                            Mensagem *
                        </label>
                        <textarea
                            id="message"
                            v-model="form.message"
                            required
                            rows="6"
                            class="w-full px-4 py-2 border border-gray-300 dark:border-gray-600 rounded-lg focus:outline-none focus:border-indigo-500 dark:bg-gray-700 dark:text-white"
                            :class="{ 'border-red-500': form.errors.message }"
                        ></textarea>
                        <p v-if="form.errors.message" class="mt-1 text-sm text-red-600 dark:text-red-400">
                            {{ form.errors.message }}
                        </p>
                    </div>

                    <!-- Submit Button -->
                    <button
                        type="submit"
                        :disabled="form.processing"
                        class="w-full bg-indigo-600 hover:bg-indigo-700 disabled:bg-gray-400 text-white py-3 rounded-lg font-semibold transition"
                    >
                        {{ form.processing ? 'Enviando...' : 'Enviar Mensagem' }}
                    </button>
                </form>

                <!-- Contact Info -->
                <div class="mt-12 grid md:grid-cols-3 gap-8">
                    <div class="text-center">
                        <div class="text-4xl mb-3">📞</div>
                        <h3 class="font-semibold text-gray-900 dark:text-white mb-1">Telefone</h3>
                        <p class="text-gray-600 dark:text-gray-400">(11) 3000-0000</p>
                    </div>
                    <div class="text-center">
                        <div class="text-4xl mb-3">📧</div>
                        <h3 class="font-semibold text-gray-900 dark:text-white mb-1">Email</h3>
                        <p class="text-gray-600 dark:text-gray-400">contato@eteczl.edu.br</p>
                    </div>
                    <div class="text-center">
                        <div class="text-4xl mb-3">📍</div>
                        <h3 class="font-semibold text-gray-900 dark:text-white mb-1">Endereço</h3>
                        <p class="text-gray-600 dark:text-gray-400">São Paulo, SP</p>
                    </div>
                </div>
            </div>
        </section>

        <!-- Footer -->
        <footer class="bg-gray-900 dark:bg-gray-950 text-white py-8">
            <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
                <p>&copy; 2026 ETEC Zona Leste. Todos os direitos reservados.</p>
            </div>
        </footer>
    </div>
</template>
