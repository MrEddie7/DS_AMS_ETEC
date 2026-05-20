<!-- resources/views/submissions/create.blade.php -->
@extends('layouts.app')

@section('content')
<div class="container mx-auto p-6 bg-white rounded-lg shadow-lg">
    <h1 class="text-2xl font-bold mb-4">Submeter Artigo Científico</h1>
    <form method="POST" action="{{ route('submission.store') }}">
        @csrf
        <div class="grid grid-cols-1 gap-4">
            <div>
                <label class="block font-medium" for="title">Título</label>
                <input class="w-full border rounded px-3 py-2" type="text" name="title" id="title" required minlength="5" maxlength="255" />
            </div>
            <div>
                <label class="block font-medium" for="author">Autor Principal</label>
                <input class="w-full border rounded px-3 py-2" type="text" name="author" id="author" required minlength="3" maxlength="100" />
            </div>
            <div>
                <label class="block font-medium" for="email">E‑mail de Contato</label>
                <input class="w-full border rounded px-3 py-2" type="email" name="email" id="email" required maxlength="150" />
            </div>
            <div>
                <label class="block font-medium" for="area">Área de Conhecimento</label>
                <select class="w-full border rounded px-3 py-2" name="area" id="area" required>
                    @foreach($areas as $area)
                        <option value="{{ $area }}">{{ $area }}</option>
                    @endforeach
                </select>
            </div>
            <div>
                <label class="block font-medium" for="coauthors">Co‑autores (opcional)</label>
                <input class="w-full border rounded px-3 py-2" type="text" name="coauthors" id="coauthors" maxlength="255" />
            </div>
            <div>
                <label class="block font-medium" for="abstract">Resumo</label>
                <textarea class="w-full border rounded px-3 py-2" name="abstract" id="abstract" rows="5" required minlength="10" maxlength="3000"></textarea>
            </div>
            <div class="pt-4">
                <button type="submit" class="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700 transition">
                    Enviar Submissão
                </button>
            </div>
        </div>
    </form>
</div>
@endsection
