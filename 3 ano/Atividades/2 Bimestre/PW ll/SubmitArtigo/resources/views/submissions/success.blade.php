@extends('layouts.app')

@section('title', 'Submissão Recebida - SubmitArtigo')

@section('content')
<div class="success-container">
    <div class="success-icon"><i class="fa-solid fa-check"></i></div>
    <h1>Submissão concluída com sucesso!</h1>
    <p>Obrigado por submeter seu artigo. Em breve nossa equipe de revisão analisará o trabalho.</p>
    <div class="summary-card">
        <h3>Detalhes da Submissão</h3>
        <div class="summary-item">
            <span class="summary-label">Protocolo:</span>
            <span class="summary-value">{{ $protocol }}</span>
        </div>
        <div class="summary-item">
            <span class="summary-label">Título:</span>
            <span class="summary-value">{{ $submission['title'] }}</span>
        </div>
        <div class="summary-item">
            <span class="summary-label">Autor Principal:</span>
            <span class="summary-value">{{ $submission['author'] }}</span>
        </div>
        <div class="summary-item">
            <span class="summary-label">E‑mail:</span>
            <span class="summary-value">{{ $submission['email'] }}</span>
        </div>
        <div class="summary-item">
            <span class="summary-label">Área:</span>
            <span class="summary-value">{{ $submission['area'] }}</span>
        </div>
        <div class="summary-item">
            <span class="summary-label">Resumo:</span>
            <span class="summary-value">{{ $submission['abstract'] }}</span>
        </div>
    </div>
    <a href="{{ route('home') }}" class="btn btn-primary" style="margin-top: 2rem;">Voltar à Home</a>
</div>
@endsection
