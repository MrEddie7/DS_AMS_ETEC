<?php

use App\Http\Controllers\GradeController;
use App\Http\Controllers\ProfileController;
use App\Http\Controllers\PublicController;
use Illuminate\Support\Facades\Route;
use Inertia\Inertia;

// Public routes
Route::get('/', [PublicController::class, 'home'])->name('home');
Route::get('/cursos', [PublicController::class, 'cursos'])->name('cursos');
Route::get('/eventos', [PublicController::class, 'eventos'])->name('eventos');
Route::get('/formulario', [PublicController::class, 'formulario'])->name('formulario');
Route::post('/formulario', [PublicController::class, 'submitForm'])->name('contact.submit');

// Dashboard
Route::get('/dashboard', function () {
    return view('dashboard');
})->middleware(['auth', 'verified'])->name('dashboard');

// Authenticated routes
Route::middleware('auth')->group(function () {
    Route::get('/profile', [ProfileController::class, 'edit'])->name('blade.profile.edit');
    Route::patch('/profile', [ProfileController::class, 'update'])->name('blade.profile.update');
    Route::delete('/profile', [ProfileController::class, 'destroy'])->name('blade.profile.destroy');

    // Grades (Notas) routes
    Route::get('/dashboard/grades', [GradeController::class, 'index'])->name('grades.index');
    Route::post('/dashboard/grades', [GradeController::class, 'store'])->name('grades.store');
    Route::delete('/dashboard/grades/{grade}', [GradeController::class, 'destroy'])->name('grades.destroy');
});

// Settings routes (import from settings.php)
require __DIR__.'/settings.php';

// 404 fallback route
Route::fallback(function () {
    return Inertia::render('Error404');
});

require __DIR__.'/auth.php';
