<?php

use Illuminate\Support\Facades\Route;

Route::get('/', function () {
    return view('principal');
});

// opcional: rota explícita para página principal
Route::get('/principal', function () {
    return view('principal');
});

// rotas básicas para login e cadastro (páginas estáticas)
Route::view('/login', 'login');
Route::view('/cadastro', 'cadastro');
