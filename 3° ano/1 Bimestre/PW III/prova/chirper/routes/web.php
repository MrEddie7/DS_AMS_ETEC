<?php

use App\Http\Controllers\AppointmentController;
use App\Http\Controllers\BarberController;
use App\Http\Controllers\CategoryController;
use App\Http\Controllers\ClientController;
use App\Http\Controllers\ServiceController;
use Illuminate\Support\Facades\Route;

Route::get('/', function () {
    return view('welcome');
});

Route::prefix('api')->group(function () {
    Route::apiResource('clients', ClientController::class);
    Route::apiResource('barbers', BarberController::class);
    Route::apiResource('categories', CategoryController::class);
    Route::apiResource('services', ServiceController::class);
    Route::apiResource('appointments', AppointmentController::class);
});
