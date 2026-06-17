<script setup lang="ts">
import { Head, Link } from '@inertiajs/vue3';

defineProps({
    courses: Array,
});
</script>

<template>
    <Head title="Cursos - ETEC Zona Leste" />

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
                        <Link href="/cursos" class="text-indigo-600 dark:text-indigo-400 font-semibold border-b-2 border-indigo-600">
                            Cursos
                        </Link>
                        <Link href="/eventos" class="text-gray-700 dark:text-gray-300 hover:text-indigo-600 dark:hover:text-indigo-400 transition">
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
        <section class="pt-32 pb-16 px-4 sm:px-6 lg:px-8 bg-gradient-to-r from-indigo-600 to-blue-600 text-white">
            <div class="max-w-7xl mx-auto">
                <h1 class="text-5xl font-bold mb-4">Nossos Cursos</h1>
                <p class="text-xl text-indigo-100">Conheça as oportunidades de formação técnica oferecidas pela ETEC Zona Leste</p>
            </div>
        </section>

        <!-- Courses Grid -->
        <section class="py-20 px-4 sm:px-6 lg:px-8">
            <div class="max-w-7xl mx-auto">
                <div v-if="courses.length > 0" class="grid md:grid-cols-2 lg:grid-cols-3 gap-8">
                    <div
                        v-for="course in courses"
                        :key="course.id"
                        class="bg-white dark:bg-gray-800 rounded-lg shadow-md hover:shadow-xl transition overflow-hidden"
                    >
                        <div class="h-2 bg-gradient-to-r from-indigo-600 to-blue-600"></div>
                        <div class="p-6">
                            <h3 class="text-2xl font-bold text-gray-900 dark:text-white mb-2">
                                {{ course.name }}
                            </h3>
                            <div class="flex gap-2 mb-4">
                                <span class="inline-block bg-indigo-100 dark:bg-indigo-900 text-indigo-800 dark:text-indigo-200 px-3 py-1 rounded-full text-sm">
                                    {{ course.period }}
                                </span>
                                <span class="inline-block bg-blue-100 dark:bg-blue-900 text-blue-800 dark:text-blue-200 px-3 py-1 rounded-full text-sm">
                                    {{ course.duration }} semestres
                                </span>
                            </div>
                            <p class="text-gray-700 dark:text-gray-300 mb-6">
                                {{ course.description.substring(0, 100) }}...
                            </p>
                            <button class="w-full bg-indigo-600 hover:bg-indigo-700 text-white py-2 rounded-lg font-semibold transition">
                                Saiba Mais
                            </button>
                        </div>
                    </div>
                </div>
                <div v-else class="text-center py-12">
                    <p class="text-gray-600 dark:text-gray-400 text-lg">Nenhum curso disponível no momento.</p>
                </div>
            </div>
        </section>

        <!-- CTA Section -->
        <section class="py-16 px-4 sm:px-6 lg:px-8 bg-indigo-600 text-white">
            <div class="max-w-7xl mx-auto text-center">
                <h2 class="text-3xl font-bold mb-4">Interessado em um de nossos cursos?</h2>
                <p class="text-indigo-100 mb-8">Entre em contato conosco para mais informações e inscrições.</p>
                <Link
                    href="/formulario"
                    class="inline-block bg-white text-indigo-600 px-8 py-3 rounded-lg font-semibold hover:bg-indigo-50 transition"
                >
                    Entre em Contato
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
