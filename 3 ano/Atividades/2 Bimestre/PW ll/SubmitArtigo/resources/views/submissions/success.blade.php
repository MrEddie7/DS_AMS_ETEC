@extends('layouts.app')

@section('title', 'Submissão Recebida - SubmitArtigo')

@section('content')
<div class="success-container">
    <div class="success-icon"><i class="fa-solid fa-check"></i></div>
    <h1>Submissão Concluída com Sucesso!</h1>
    <p>Agradecemos o envio do seu manuscrito. Guarde o protocolo gerado para acompanhar o andamento da revisão cega pelos pares.</p>
    
    <div class="summary-card">
        <h3>Dados Registrados no Sistema</h3>
        
        <div class="summary-item">
            <span class="summary-label">Número de Protocolo</span>
            <span class="summary-value" style="font-family: monospace; font-size: 1.15rem; font-weight: 700; color: var(--primary-color);">
                {{ $submission->protocol }}
            </span>
        </div>
        
        <div class="summary-item">
            <span class="summary-label">Título do Trabalho</span>
            <span class="summary-value">{{ $submission->title }}</span>
        </div>
        
        <div class="summary-item">
            <span class="summary-label">Autor Principal</span>
            <span class="summary-value">{{ $submission->author }}</span>
        </div>
        
        @if($submission->coauthors)
            <div class="summary-item">
                <span class="summary-label">Co-autores</span>
                <span class="summary-value">{{ $submission->coauthors }}</span>
            </div>
        @endif
        
        <div class="summary-item">
            <span class="summary-label">Área de Pesquisa</span>
            <span class="summary-value"><span class="badge badge-review" style="padding: 0.2rem 0.6rem;">{{ $submission->area }}</span></span>
        </div>
        
        <div class="summary-item">
            <span class="summary-label">E-mail de Contato</span>
            <span class="summary-value">{{ $submission->email }}</span>
        </div>

        <div class="summary-item" style="border-top: 1px solid var(--border-color); padding-top: 1rem; margin-top: 1rem;">
            <span class="summary-label">Arquivo Submetido</span>
            <span class="summary-value">
                <a href="{{ route('submission.download', $submission->id) }}" class="btn btn-secondary" style="padding: 0.4rem 0.8rem; font-size: 0.85rem; margin-top: 0.25rem;">
                    <i class="fa-solid fa-file-arrow-down"></i> Baixar Documento Enviado
                </a>
            </span>
        </div>
    </div>
    
    <div style="display: flex; gap: 1rem; justify-content: center; margin-top: 2rem;">
        <a href="{{ route('home') }}" class="btn btn-secondary">Voltar ao Início</a>
        <a href="{{ route('submission.show', $submission->id) }}" class="btn btn-primary">
            <i class="fa-solid fa-circle-info"></i> Acompanhar Submissão
        </a>
    </div>
</div>
@endsection
