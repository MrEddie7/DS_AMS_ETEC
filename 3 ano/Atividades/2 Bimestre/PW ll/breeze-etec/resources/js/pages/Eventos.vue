<script setup lang="ts">
import { Head, Link } from '@inertiajs/vue3';

defineProps({
    events: Array,
});

const formatDate = (date: string) => {
    return new Date(date).toLocaleDateString('pt-BR', {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
    });
};
</script>

<template>
    <Head title="Eventos - ETEC Zona Leste" />

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
                        <Link href="/eventos" class="text-indigo-600 dark:text-indigo-400 font-semibold border-b-2 border-indigo-600">
                            Eventos
                        </Link>
                        <Link href="/formulario" class="text-gray-700 dark:text-gray-300 hover:text-indigo-600 dark:hover:text-indigo-400 transition">
                            Contato
                        </Link>
                    </div>
                </div>
            </div>
        </nav>

        <!-- Header -->
        <section class="pt-32 pb-16 px-4 sm:px-6 lg:px-8 bg-gradient-to-r from-purple-600 to-indigo-600 text-white">
            <div class="max-w-7xl mx-auto">
                <h1 class="text-5xl font-bold mb-4">Eventos</h1>
                <p class="text-xl text-purple-100">Acompanhe os principais eventos e datas importantes da ETEC Zona Leste</p>
            </div>
        </section>

        <!-- Events Timeline -->
        <section class="py-20 px-4 sm:px-6 lg:px-8">
            <div class="max-w-4xl mx-auto">
                <div v-if="events.length > 0" class="space-y-8">
                    <div
                        v-for="(event, index) in events"
                        :key="event.id"
                        class="relative"
                    >
                        <!-- Timeline Line -->
                        <div v-if="index !== events.length - 1" class="absolute left-8 top-24 w-1 h-12 bg-indigo-300 dark:bg-indigo-700"></div>

                        <!-- Event Card -->
                        <div class="flex gap-6">
                            <div class="flex-shrink-0 flex flex-col items-center">
                                <div class="flex items-center justify-center h-16 w-16 rounded-full bg-indigo-600 text-white">
                                    📅
                                </div>
                            </div>
                            <div class="flex-grow bg-white dark:bg-gray-800 rounded-lg shadow-md p-6 hover:shadow-lg transition">
                                <div class="flex justify-between items-start mb-2">
                                    <h3 class="text-2xl font-bold text-gray-900 dark:text-white">
                                        {{ event.title }}
                                    </h3>
                                    <span class="text-sm bg-indigo-100 dark:bg-indigo-900 text-indigo-800 dark:text-indigo-200 px-3 py-1 rounded-full">
                                        {{ formatDate(event.date) }}
                                    </span>
                                </div>
                                <div class="flex items-center gap-2 text-gray-600 dark:text-gray-400 mb-3">
                                    <span>📍</span>
                                    <span>{{ event.location }}</span>
                                </div>
                                <p class="text-gray-700 dark:text-gray-300">
                                    {{ event.description }}
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div v-else class="text-center py-12">
                    <p class="text-gray-600 dark:text-gray-400 text-lg">Nenhum evento agendado no momento.</p>
                </div>
            </div>
        </section>

        <!-- Newsletter CTA -->
        <section class="py-16 px-4 sm:px-6 lg:px-8 bg-indigo-600 text-white">
            <div class="max-w-7xl mx-auto text-center">
                <h2 class="text-3xl font-bold mb-4">Fique atualizado com nossos eventos</h2>
                <p class="text-indigo-100 mb-8">Entre em contato para receber notificações sobre novos eventos.</p>
                <Link
                    href="/formulario"
                    class="inline-block bg-white text-indigo-600 px-8 py-3 rounded-lg font-semibold hover:bg-indigo-50 transition"
                >
                    Cadastre-se
                </Link>
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
