@extends('layouts.app')

@section('content')
    <div class="max-w-md mx-auto bg-white shadow-lg rounded-lg p-6">
        <h1 class="text-2xl font-bold mb-4 text-center">Cadastro</h1>
        <form method="POST" action="{{ url('/register') }}" class="space-y-4">
            @csrf
            <div>
                <label class="block text-sm font-medium text-gray-700" for="name">Nome</label>
                <input id="name" name="name" type="text" required class="mt-1 block w-full border-gray-300 rounded-md shadow-sm" />
            </div>
            <div>
                <label class="block text-sm font-medium text-gray-700" for="email">Email</label>
                <input id="email" name="email" type="email" required class="mt-1 block w-full border-gray-300 rounded-md shadow-sm" />
            </div>
            <div>
                <label class="block text-sm font-medium text-gray-700" for="password">Senha</label>
                <input id="password" name="password" type="password" required class="mt-1 block w-full border-gray-300 rounded-md shadow-sm" />
            </div>
            <div>
                <label class="block text-sm font-medium text-gray-700" for="password_confirmation">Confirmar senha</label>
                <input id="password_confirmation" name="password_confirmation" type="password" required class="mt-1 block w-full border-gray-300 rounded-md shadow-sm" />
            </div>
            <div class="flex items-center justify-between">
                <button type="submit" class="px-4 py-2 bg-red-600 text-white rounded hover:bg-red-700">Cadastrar</button>
            </div>
        </form>
    </div>
@endsection