@extends('layouts.app')

@section('title', 'Submeter Artigo - SubmitArtigo')

@section('content')
<div class="form-container">
    <div class="form-header">
        <h1>Submeter Artigo Científico</h1>
        <p>Preencha os campos abaixo com os dados do seu trabalho acadêmico e envie o documento para avaliação.</p>
    </div>

    @if ($errors->any())
        <div class="alert alert-danger">
            <div style="display: flex; flex-direction: column; gap: 0.25rem;">
                <strong><i class="fa-solid fa-triangle-exclamation"></i> Por favor, corrija os erros abaixo:</strong>
                <ul style="margin-left: 1.5rem; margin-top: 0.5rem; padding-left: 0;">
                    @foreach ($errors->all() as $error)
                        <li>{{ $error }}</li>
                    @endforeach
                </ul>
            </div>
        </div>
    @endif

    <form method="POST" action="{{ route('submission.store') }}" enctype="multipart/form-data">
        @csrf

        <div class="form-group">
            <label for="title">Título do Artigo *</label>
            <input type="text" name="title" id="title" class="form-control" value="{{ old('title') }}" required minlength="5" maxlength="255" placeholder="Ex: Impactos da Inteligência Artificial na Educação Superior" />
        </div>

        <div class="form-group">
            <label for="author">Autor Principal *</label>
            <input type="text" name="author" id="author" class="form-control" value="{{ old('author') }}" required minlength="3" maxlength="100" placeholder="Nome completo do autor principal" />
        </div>

        <div class="form-group">
            <label for="email">E-mail de Contato *</label>
            <input type="email" name="email" id="email" class="form-control" value="{{ old('email') }}" required maxlength="150" placeholder="seu.email@instituicao.edu.br" />
        </div>

        <div class="form-group">
            <label for="area">Área de Conhecimento *</label>
            <select name="area" id="area" class="form-control" required>
                <option value="" disabled selected>Selecione a área do artigo...</option>
                @foreach($areas as $area)
                    <option value="{{ $area }}" {{ old('area') == $area ? 'selected' : '' }}>{{ $area }}</option>
                @endforeach
            </select>
        </div>

        <div class="form-group">
            <label for="coauthors">Co-autores (opcional)</label>
            <input type="text" name="coauthors" id="coauthors" class="form-control" value="{{ old('coauthors') }}" maxlength="255" placeholder="Ex: Maria Oliveira, João Silva (separados por vírgula)" />
        </div>

        <div class="form-group">
            <label for="abstract">Resumo / Abstract *</label>
            <textarea name="abstract" id="abstract" class="form-control" rows="8" required minlength="10" maxlength="3000" placeholder="Escreva aqui o resumo do seu artigo científico (mínimo 10 caracteres)...">{{ old('abstract') }}</textarea>
        </div>

        <div class="form-group">
            <label for="file">Arquivo do Artigo * (PDF, DOC ou DOCX - Limite: 10MB)</label>
            <input type="file" name="file" id="file" class="form-control" accept=".pdf,.doc,.docx" required style="padding: 0.6rem;" />
            <p style="font-size: 0.8rem; color: var(--text-muted); margin-top: 0.35rem;">
                <i class="fa-solid fa-circle-info"></i> Certifique-se de que o manuscrito não possui identificação de autores no corpo do arquivo para garantir a avaliação cega por pares.
            </p>
        </div>

        <div class="form-actions">
            <a href="{{ route('home') }}" class="btn btn-secondary">Cancelar</a>
            <button type="submit" class="btn btn-primary" id="btn-submit-article">
                <i class="fa-solid fa-paper-plane"></i> Enviar Submissão
            </button>
        </div>
    </form>
</div>
@endsection

