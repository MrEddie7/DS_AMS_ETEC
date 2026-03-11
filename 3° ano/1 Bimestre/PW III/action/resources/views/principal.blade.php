@extends('layouts.app')

@section('content')
    <div class="max-w-2xl mx-auto bg-white shadow-lg rounded-lg p-6">
        <h1 class="text-3xl font-bold mb-4 text-center">Página Principal</h1>
        <p class="text-gray-700">Bem-vindo ao sistema. Utilize o menu acima para navegar entre as seções.</p>
        <div class="mt-6">
            <img src="{{ asset(path: 'images/teste2.png') }}" alt="Imagem Principal" class="rounded-lg shadow-md w-full object-cover" />
        </div>
    </div>
@endsection