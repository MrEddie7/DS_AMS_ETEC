<script setup lang="ts">
import { Head, Link, useForm } from '@inertiajs/vue3';

defineProps({
    grades: Array,
});

// Form for adding a new grade
const form = useForm({
    student_name: '',
    subject: '',
    score: '',
});

const submit = () => {
    form.post(route('grades.store'), {
        onFinish: () => {
            if (!form.hasErrors) {
                form.reset();
            }
        },
    });
};

// Delete grade function
const deleteGrade = (gradeId: number) => {
    if (confirm('Tem certeza que deseja deletar esta nota?')) {
        useForm({}).delete(route('grades.destroy', gradeId));
    }
};
</script>

<template>
    <Head title="Gerenciar Notas" />

    <div class="min-h-screen bg-gray-50 dark:bg-gray-900">
        <!-- Navigation -->
        <nav class="fixed top-0 w-full z-50 bg-white dark:bg-gray-800 shadow-sm">
            <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                <div class="flex justify-between items-center h-16">
                    <Link href="/" class="text-2xl font-bold text-indigo-600 dark:text-indigo-400">
                        ETEC Zona Leste
                    </Link>
                    <div class="flex items-center gap-4">
                        <span class="text-gray-700 dark:text-gray-300">{{ $page.props.auth.user.name }}</span>
                        <Link
                            :href="route('logout')"
                            method="post"
                            as="button"
                            class="text-red-600 hover:text-red-700 font-medium"
                        >
                            Sair
                        </Link>
                    </div>
                </div>
            </div>
        </nav>

        <!-- Main Content -->
        <div class="pt-24 px-4 sm:px-6 lg:px-8 max-w-7xl mx-auto">
            <div class="flex justify-between items-center mb-8">
                <h1 class="text-4xl font-bold text-gray-900 dark:text-white">Gerenciar Notas</h1>
                <Link href="/dashboard" class="text-indigo-600 hover:text-indigo-700 dark:text-indigo-400">
                    ← Voltar ao Dashboard
                </Link>
            </div>

            <!-- Success Message -->
            <div v-if="$page.props.flash?.success" class="mb-6 p-4 bg-green-50 dark:bg-green-900 border border-green-200 dark:border-green-700 text-green-800 dark:text-green-200 rounded-lg">
                {{ $page.props.flash.success }}
            </div>

            <div class="grid lg:grid-cols-3 gap-8">
                <!-- Add Grade Form -->
                <div class="lg:col-span-1">
                    <div class="bg-white dark:bg-gray-800 rounded-lg shadow-md p-6">
                        <h2 class="text-2xl font-bold text-gray-900 dark:text-white mb-4">Adicionar Nova Nota</h2>
                        <form @submit.prevent="submit" class="space-y-4">
                            <!-- Student Name -->
                            <div>
                                <label for="student_name" class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                                    Nome do Aluno *
                                </label>
                                <input
                                    id="student_name"
                                    v-model="form.student_name"
                                    type="text"
                                    required
                                    class="w-full px-3 py-2 border border-gray-300 dark:border-gray-600 rounded-lg focus:outline-none focus:border-indigo-500 dark:bg-gray-700 dark:text-white text-sm"
                                    :class="{ 'border-red-500': form.errors.student_name }"
                                />
                                <p v-if="form.errors.student_name" class="mt-1 text-xs text-red-600 dark:text-red-400">
                                    {{ form.errors.student_name }}
                                </p>
                            </div>

                            <!-- Subject -->
                            <div>
                                <label for="subject" class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                                    Disciplina *
                                </label>
                                <input
                                    id="subject"
                                    v-model="form.subject"
                                    type="text"
                                    required
                                    class="w-full px-3 py-2 border border-gray-300 dark:border-gray-600 rounded-lg focus:outline-none focus:border-indigo-500 dark:bg-gray-700 dark:text-white text-sm"
                                    :class="{ 'border-red-500': form.errors.subject }"
                                />
                                <p v-if="form.errors.subject" class="mt-1 text-xs text-red-600 dark:text-red-400">
                                    {{ form.errors.subject }}
                                </p>
                            </div>

                            <!-- Score -->
                            <div>
                                <label for="score" class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                                    Nota (0-10) *
                                </label>
                                <input
                                    id="score"
                                    v-model.number="form.score"
                                    type="number"
                                    min="0"
                                    max="10"
                                    step="0.1"
                                    required
                                    class="w-full px-3 py-2 border border-gray-300 dark:border-gray-600 rounded-lg focus:outline-none focus:border-indigo-500 dark:bg-gray-700 dark:text-white text-sm"
                                    :class="{ 'border-red-500': form.errors.score }"
                                />
                                <p v-if="form.errors.score" class="mt-1 text-xs text-red-600 dark:text-red-400">
                                    {{ form.errors.score }}
                                </p>
                            </div>

                            <!-- Submit Button -->
                            <button
                                type="submit"
                                :disabled="form.processing"
                                class="w-full bg-indigo-600 hover:bg-indigo-700 disabled:bg-gray-400 text-white py-2 rounded-lg font-semibold transition text-sm"
                            >
                                {{ form.processing ? 'Salvando...' : 'Adicionar Nota' }}
                            </button>
                        </form>
                    </div>
                </div>

                <!-- Grades List -->
                <div class="lg:col-span-2">
                    <div class="bg-white dark:bg-gray-800 rounded-lg shadow-md overflow-hidden">
                        <div class="px-6 py-4 bg-indigo-600 text-white">
                            <h2 class="text-2xl font-bold">Minhas Notas ({{ grades.length }})</h2>
                        </div>

                        <div v-if="grades.length > 0" class="overflow-x-auto">
                            <table class="w-full">
                                <thead class="bg-gray-100 dark:bg-gray-700">
                                    <tr>
                                        <th class="px-6 py-3 text-left text-sm font-semibold text-gray-900 dark:text-white">
                                            Aluno
                                        </th>
                                        <th class="px-6 py-3 text-left text-sm font-semibold text-gray-900 dark:text-white">
                                            Disciplina
                                        </th>
                                        <th class="px-6 py-3 text-left text-sm font-semibold text-gray-900 dark:text-white">
                                            Nota
                                        </th>
                                        <th class="px-6 py-3 text-left text-sm font-semibold text-gray-900 dark:text-white">
                                            Ações
                                        </th>
                                    </tr>
                                </thead>
                                <tbody class="divide-y divide-gray-200 dark:divide-gray-700">
                                    <tr
                                        v-for="grade in grades"
                                        :key="grade.id"
                                        class="hover:bg-gray-50 dark:hover:bg-gray-700 transition"
                                    >
                                        <td class="px-6 py-4 text-sm text-gray-900 dark:text-white">
                                            {{ grade.student_name }}
                                        </td>
                                        <td class="px-6 py-4 text-sm text-gray-900 dark:text-white">
                                            {{ grade.subject }}
                                        </td>
                                        <td class="px-6 py-4 text-sm">
                                            <span
                                                class="inline-block px-3 py-1 rounded-full font-semibold text-white"
                                                :class="{
                                                    'bg-green-600': grade.score >= 7,
                                                    'bg-yellow-600': grade.score >= 5 && grade.score < 7,
                                                    'bg-red-600': grade.score < 5,
                                                }"
                                            >
                                                {{ parseFloat(grade.score).toFixed(1) }}
                                            </span>
                                        </td>
                                        <td class="px-6 py-4 text-sm">
                                            <button
                                                @click="deleteGrade(grade.id)"
                                                class="text-red-600 hover:text-red-800 dark:text-red-400 dark:hover:text-red-200 font-medium transition"
                                            >
                                                Deletar
                                            </button>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div v-else class="p-6 text-center">
                            <p class="text-gray-600 dark:text-gray-400">Nenhuma nota registrada. Crie uma nova nota para começar!</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
