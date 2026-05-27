@extends('layouts.app')

@section('title', 'Painel de Submissões - SubmitArtigo')

@section('content')
<div style="margin-bottom: 2rem;">
    <h1 class="section-title" style="text-align: left; margin-bottom: 0.5rem;">Painel de Acompanhamento</h1>
    <p style="color: var(--text-muted);">Visualize e gerencie todos os artigos científicos submetidos à revista.</p>
</div>

<!-- Barra de Filtros e Pesquisa -->
<div class="filter-bar">
    <form action="{{ route('submission.index') }}" method="GET" class="filter-form">
        <div class="filter-group" style="flex: 2;">
            <label for="search">Buscar Artigo</label>
            <input type="text" name="search" id="search" class="form-control" value="{{ request('search') }}" placeholder="Buscar por protocolo, título ou autor..." />
        </div>

        <div class="filter-group">
            <label for="area">Área Temática</label>
            <select name="area" id="area" class="form-control">
                <option value="">Todas as áreas</option>
                @foreach($areas as $area)
                    <option value="{{ $area }}" {{ request('area') == $area ? 'selected' : '' }}>{{ $area }}</option>
                @endforeach
            </select>
        </div>

        <div class="filter-group">
            <label for="status">Status</label>
            <select name="status" id="status" class="form-control">
                <option value="">Todos os status</option>
                @foreach($statuses as $status)
                    <option value="{{ $status }}" {{ request('status') == $status ? 'selected' : '' }}>{{ $status }}</option>
                @endforeach
            </select>
        </div>

        <div style="display: flex; gap: 0.5rem; margin-top: 1rem;">
            <button type="submit" class="btn btn-primary" style="padding: 0.85rem 1.5rem;">
                <i class="fa-solid fa-magnifying-glass"></i> Filtrar
            </button>
            @if(request()->anyFilled(['search', 'area', 'status']))
                <a href="{{ route('submission.index') }}" class="btn btn-secondary" style="padding: 0.85rem 1.5rem; display: flex; align-items: center; justify-content: center;">
                    Limpar
                </a>
            @endif
        </div>
    </form>
</div>

<!-- Tabela de Submissões -->
<div class="table-responsive">
    <table class="custom-table">
        <thead>
            <tr>
                <th style="width: 15%;">Protocolo</th>
                <th style="width: 35%;">Título do Artigo</th>
                <th style="width: 20%;">Autor Principal</th>
                <th style="width: 15%;">Área</th>
                <th style="width: 15%;">Status</th>
                <th style="width: 10%; text-align: center;">Ações</th>
            </tr>
        </thead>
        <tbody>
            @forelse($submissions as $sub)
                <tr>
                    <td style="font-family: monospace; font-weight: 700; color: var(--primary-color);">
                        {{ $sub->protocol }}
                    </td>
                    <td>
                        <strong style="color: var(--primary-dark); font-size: 0.95rem; display: block; max-width: 400px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">
                            {{ $sub->title }}
                        </strong>
                        <span style="font-size: 0.8rem; color: var(--text-muted);">
                            Submetido em {{ $sub->created_at->format('d/m/Y H:i') }}
                        </span>
                    </td>
                    <td>{{ $sub->author }}</td>
                    <td>{{ $sub->area }}</td>
                    <td>
                        @if($sub->status === 'Pendente')
                            <span class="badge badge-pending">{{ $sub->status }}</span>
                        @elseif($sub->status === 'Em Revisão')
                            <span class="badge badge-review">{{ $sub->status }}</span>
                        @elseif($sub->status === 'Aceito')
                            <span class="badge badge-accepted">{{ $sub->status }}</span>
                        @elseif($sub->status === 'Rejeitado')
                            <span class="badge badge-rejected">{{ $sub->status }}</span>
                        @endif
                    </td>
                    <td style="text-align: center;">
                        <a href="{{ route('submission.show', $sub->id) }}" class="btn btn-secondary" style="padding: 0.4rem 0.8rem; font-size: 0.8rem; display: inline-flex; align-items: center; gap: 0.25rem;">
                            <i class="fa-solid fa-eye"></i> Detalhes
                        </a>
                    </td>
                </tr>
            @empty
                <tr>
                    <td colspan="6" style="text-align: center; padding: 4rem 2rem; color: var(--text-muted);">
                        <i class="fa-solid fa-folder-open" style="font-size: 3rem; opacity: 0.3; margin-bottom: 1rem; display: block;"></i>
                        <p style="font-size: 1.1rem; font-weight: 500;">Nenhuma submissão encontrada.</p>
                        <p style="font-size: 0.9rem; margin-top: 0.25rem; margin-bottom: 1.5rem;">Os filtros aplicados não retornaram resultados ou ainda não há artigos enviados.</p>
                        <a href="{{ route('submission.create') }}" class="btn btn-primary" style="font-size: 0.9rem;">
                            <i class="fa-solid fa-paper-plane"></i> Submeter Primeiro Artigo
                        </a>
                    </td>
                </tr>
            @endforelse
        </tbody>
    </table>
</div>
@endsection
