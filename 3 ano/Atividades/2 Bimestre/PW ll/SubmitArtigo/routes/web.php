<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\PageController;
use App\Http\Controllers\SubmissionController;

// Página inicial
Route::get('/', [PageController::class, 'home'])->name('home');

// Página Sobre
Route::get('/sobre', [PageController::class, 'about'])->name('about');

// Página Contato
Route::get('/contato', [PageController::class, 'contact'])->name('contact');

// Formulário de Submissão (GET e POST)
Route::get('/submeter', [SubmissionController::class, 'create'])->name('submission.create');
Route::post('/submeter', [SubmissionController::class, 'store'])->name('submission.store');

// Dashboard/Painel de Submissões (Visualização, detalhe, download e controle de status)
Route::get('/submissoes', [SubmissionController::class, 'index'])->name('submission.index');
Route::get('/submissoes/{id}', [SubmissionController::class, 'show'])->name('submission.show');
Route::patch('/submissoes/{id}/status', [SubmissionController::class, 'updateStatus'])->name('submission.updateStatus');
Route::get('/submissoes/download/{id}', [SubmissionController::class, 'download'])->name('submission.download');

// Rota de fallback personalizada (404)
Route::fallback(function () {
    return response()->view('errors.404', [], 404);
});
