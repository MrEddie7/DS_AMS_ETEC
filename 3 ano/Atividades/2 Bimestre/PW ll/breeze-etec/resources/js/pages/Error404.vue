<script setup lang="ts">
import { Head, Link } from '@inertiajs/vue3';
</script>

<template>
    <Head title="404 - Página não encontrada" />

    <div class="min-h-screen flex items-center justify-center bg-gradient-to-br from-indigo-600 to-purple-600 px-4">
        <div class="text-center">
            <h1 class="text-9xl font-bold text-white mb-4">404</h1>
            <h2 class="text-4xl font-bold text-white mb-4">Página não encontrada</h2>
            <p class="text-xl text-indigo-100 mb-8">
                Desculpe, a página que você está procurando não existe ou foi removida.
            </p>
            <div class="space-x-4">
                <Link
                    href="/"
                    class="inline-block bg-white text-indigo-600 px-8 py-3 rounded-lg font-semibold hover:bg-indigo-50 transition"
                >
                    Voltar ao Início
                </Link>
                <Link
                    href="/cursos"
                    class="inline-block bg-indigo-500 hover:bg-indigo-700 text-white px-8 py-3 rounded-lg font-semibold transition"
                >
                    Explorar Cursos
                </Link>
            </div>
        </div>
    </div>
</template>
