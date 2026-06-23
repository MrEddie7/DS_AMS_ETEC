<script setup lang="ts">
import { Head, Link } from '@inertiajs/vue3';

defineProps({
    auth: Object,
});
</script>

<template>
    <Head title="ETEC Zona Leste - Home" />

    <div class="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 dark:from-gray-900 dark:to-gray-800">
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
                        <Link href="/formulario" class="text-gray-700 dark:text-gray-300 hover:text-indigo-600 dark:hover:text-indigo-400 transition">
                            Contato
                        </Link>
                    </div>
                    <div class="flex gap-4">
                        <Link
                            v-if="auth.user"
                            :href="route('dashboard')"
                            class="bg-indigo-600 hover:bg-indigo-700 text-white px-4 py-2 rounded-lg transition"
                        >
                            Dashboard
                        </Link>
                        <template v-else>
                            <Link
                                :href="route('login')"
                                class="text-indigo-600 dark:text-indigo-400 hover:text-indigo-700 transition font-medium"
                            >
                                Login
                            </Link>
                            <Link
                                :href="route('register')"
                                class="bg-indigo-600 hover:bg-indigo-700 text-white px-4 py-2 rounded-lg transition"
                            >
                                Registrar
                            </Link>
                        </template>
                    </div>
                </div>
            </div>
        </nav>

        <!-- Hero Section -->
        <section class="pt-32 pb-20 px-4 sm:px-6 lg:px-8 max-w-7xl mx-auto">
            <div class="text-center">
                <h1 class="text-5xl md:text-6xl font-bold text-gray-900 dark:text-white mb-6">
                    Bem-vindo à ETEC Zona Leste
                </h1>
                <p class="text-xl text-gray-600 dark:text-gray-300 mb-8 max-w-2xl mx-auto">
                    Transformando o futuro através da educação técnica de excelência. Conheça nossos cursos e oportunidades.
                </p>
                <div class="flex gap-4 justify-center">
                    <Link
                        href="/cursos"
                        class="bg-indigo-600 hover:bg-indigo-700 text-white px-8 py-3 rounded-lg font-semibold transition transform hover:scale-105"
                    >
                        Explorar Cursos
                    </Link>
                    <Link
                        href="/formulario"
                        class="bg-white dark:bg-gray-700 text-indigo-600 dark:text-indigo-400 border-2 border-indigo-600 px-8 py-3 rounded-lg font-semibold hover:bg-indigo-50 dark:hover:bg-gray-600 transition"
                    >
                        Entrar em Contato
                    </Link>
                </div>
            </div>
        </section>

        <!-- Features Section -->
        <section class="py-20 bg-white dark:bg-gray-800">
            <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                <h2 class="text-4xl font-bold text-center text-gray-900 dark:text-white mb-12">
                    Por que escolher ETEC Zona Leste?
                </h2>
                <div class="grid md:grid-cols-3 gap-8">
                    <div class="p-8 bg-gradient-to-br from-blue-50 to-blue-100 dark:from-gray-700 dark:to-gray-600 rounded-xl hover:shadow-lg transition">
                        <div class="text-4xl mb-4">🎓</div>
                        <h3 class="text-xl font-bold text-gray-900 dark:text-white mb-3">Excelência Acadêmica</h3>
                        <p class="text-gray-700 dark:text-gray-300">
                            Cursos técnicos de qualidade com professores experientes na indústria.
                        </p>
                    </div>
                    <div class="p-8 bg-gradient-to-br from-purple-50 to-purple-100 dark:from-gray-700 dark:to-gray-600 rounded-xl hover:shadow-lg transition">
                        <div class="text-4xl mb-4">💼</div>
                        <h3 class="text-xl font-bold text-gray-900 dark:text-white mb-3">Oportunidades de Carreira</h3>
                        <p class="text-gray-700 dark:text-gray-300">
                            Inserção no mercado de trabalho com parcerias estratégicas.
                        </p>
                    </div>
                    <div class="p-8 bg-gradient-to-br from-green-50 to-green-100 dark:from-gray-700 dark:to-gray-600 rounded-xl hover:shadow-lg transition">
                        <div class="text-4xl mb-4">🚀</div>
                        <h3 class="text-xl font-bold text-gray-900 dark:text-white mb-3">Tecnologia Avançada</h3>
                        <p class="text-gray-700 dark:text-gray-300">
                            Laboratórios equipados com tecnologia de ponta para prática profissional.
                        </p>
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
