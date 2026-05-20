@extends('layouts.app')

@section('title', 'Página não encontrada - SubmitArtigo')

@section('content')
<div class="error-container">
    <div class="error-code">404</div>
    <h2>Oooops! Página não encontrada</h2>
    <p>Desculpe, mas a página que você está procurando não existe ou foi movida. Verifique o endereço ou volte à página inicial.</p>
    <a href="{{ route('home') }}" class="btn btn-primary" id="btn-back-home">Voltar para a Home</a>
</div>
@endsection
