@extends('layouts.app')

@section('title', 'Detalhes da Submissão - SubmitArtigo')

@section('content')
<div style="margin-bottom: 2.5rem; display: flex; align-items: center; justify-content: space-between; flex-wrap: wrap; gap: 1rem;">
    <div>
        <a href="{{ route('submission.index') }}" class="btn btn-secondary" style="padding: 0.5rem 1rem; font-size: 0.875rem; margin-bottom: 1rem;">
            <i class="fa-solid fa-arrow-left"></i> Voltar ao Painel
        </a>
        <h1 style="font-family: var(--font-heading); color: var(--primary-dark); font-size: 2rem; font-weight: 800; line-height: 1.2;">
            Detalhes do Artigo Científico
        </h1>
    </div>
    
    <div>
        @if($submission->status === 'Pendente')
            <span class="badge badge-pending" style="font-size: 1rem; padding: 0.5rem 1.25rem;">{{ $submission->status }}</span>
        @elseif($submission->status === 'Em Revisão')
            <span class="badge badge-review" style="font-size: 1rem; padding: 0.5rem 1.25rem;">{{ $submission->status }}</span>
        @elseif($submission->status === 'Aceito')
            <span class="badge badge-accepted" style="font-size: 1rem; padding: 0.5rem 1.25rem;">{{ $submission->status }}</span>
        @elseif($submission->status === 'Rejeitado')
            <span class="badge badge-rejected" style="font-size: 1rem; padding: 0.5rem 1.25rem;">{{ $submission->status }}</span>
        @endif
    </div>
</div>

@if(session('success'))
    <div class="alert alert-success">
        <i class="fa-solid fa-circle-check"></i> {{ session('success') }}
    </div>
@endif

<div class="detail-grid">
    <!-- Conteúdo do Artigo -->
    <div class="detail-main">
        <div class="detail-field">
            <span class="detail-label" style="font-size: 0.9rem;">Título Completo</span>
            <h2 style="font-family: var(--font-heading); font-size: 1.5rem; color: var(--primary-dark); font-weight: 700; margin-top: 0.5rem;">
                {{ $submission->title }}
            </h2>
        </div>

        <div class="detail-field">
            <span class="detail-label" style="font-size: 0.9rem;">Área de Conhecimento</span>
            <div style="margin-top: 0.5rem;">
                <span class="badge badge-review" style="font-size: 0.85rem; padding: 0.3rem 0.85rem;">{{ $submission->area }}</span>
            </div>
        </div>

        <div class="detail-field">
            <span class="detail-label" style="font-size: 0.9rem;">Resumo / Abstract</span>
            <div class="detail-abstract-box" style="margin-top: 0.75rem;">
                {{ $submission->abstract }}
            </div>
        </div>
    </div>

    <!-- Metadados & Ações do Artigo -->
    <div class="detail-sidebar">
        <h3 style="font-family: var(--font-heading); font-size: 1.25rem; color: var(--primary-dark); border-bottom: 2px solid var(--border-color); padding-bottom: 0.75rem; margin-bottom: 1.5rem;">
            Informações
        </h3>

        <div class="detail-field">
            <div class="detail-label">Protocolo</div>
            <div class="detail-val" style="font-family: monospace; font-weight: 700; font-size: 1.15rem; color: var(--primary-color);">
                {{ $submission->protocol }}
            </div>
        </div>

        <div class="detail-field">
            <div class="detail-label">Submetido Em</div>
            <div class="detail-val">
                {{ $submission->created_at->format('d/m/Y \à\s H:i') }}
            </div>
        </div>

        <div class="detail-field">
            <div class="detail-label">Autor Principal</div>
            <div class="detail-val" style="font-weight: 600;">
                {{ $submission->author }}
            </div>
        </div>

        @if($submission->coauthors)
            <div class="detail-field">
                <div class="detail-label">Co-autores</div>
                <div class="detail-val">
                    {{ $submission->coauthors }}
                </div>
            </div>
        @endif

        <div class="detail-field">
            <div class="detail-label">E-mail do Autor</div>
            <div class="detail-val" style="font-size: 0.95rem; word-break: break-all;">
                <a href="mailto:{{ $submission->email }}" style="color: var(--primary-light); text-decoration: none;">
                    {{ $submission->email }}
                </a>
            </div>
        </div>

        <!-- Download do Documento -->
        <div style="margin-top: 2.5rem; margin-bottom: 2.5rem;">
            <a href="{{ route('submission.download', $submission->id) }}" class="btn btn-primary" style="width: 100%; text-align: center; display: block; padding: 0.85rem 1.25rem;">
                <i class="fa-solid fa-file-arrow-down"></i> Baixar Artigo Original
            </a>
        </div>

        <!-- Alteração de Status da Revisão -->
        <div class="action-box">
            <h4 style="font-family: var(--font-heading); color: var(--primary-dark); font-size: 1.05rem; margin-bottom: 0.75rem;">
                Controle Editorial
            </h4>
            <p style="font-size: 0.8rem; color: var(--text-muted); margin-bottom: 1rem;">
                Altere o status para acompanhar a revisão deste artigo.
            </p>

            <form action="{{ route('submission.updateStatus', $submission->id) }}" method="POST">
                @csrf
                @method('PATCH')
                
                <div class="status-select-wrapper">
                    <select name="status" class="form-control" style="flex: 1;" required>
                        @foreach($statuses as $status)
                            <option value="{{ $status }}" {{ $submission->status === $status ? 'selected' : '' }}>
                                {{ $status }}
                            </option>
                        @endforeach
                    </select>
                    
                    <button type="submit" class="btn btn-secondary" style="padding: 0.85rem 1rem;">
                        Salvar
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
@endsection
