@extends('layouts.app')

@section('title', 'Início - SubmitArtigo Plataforma de Submissão')

@section('content')
<section class="hero">
    <div class="hero-text">
        <h1>Divulgue sua Pesquisa para o <span>Mundo Científico</span></h1>
        <p>A SubmitArtigo é uma plataforma moderna voltada para a submissão rápida, revisão criteriosa e publicação de artigos científicos de alta relevância acadêmica. Conectamos pesquisadores e revisores em um ambiente integrado, seguro e transparente.</p>
        <div class="hero-actions">
            <a href="{{ route('submission.create') }}" class="btn btn-primary" id="btn-hero-submit">
                <i class="fa-solid fa-paper-plane"></i> Iniciar Submissão
            </a>
            <a href="{{ route('about') }}" class="btn btn-secondary" id="btn-hero-learn">
                Saber Mais <i class="fa-solid fa-arrow-right"></i>
            </a>
        </div>
    </div>
    <div class="hero-image-placeholder" style="display: flex; justify-content: center; align-items: center; background: linear-gradient(135deg, rgba(30, 58, 138, 0.05), rgba(59, 130, 246, 0.1)); border-radius: var(--radius-xl); padding: 3rem; border: 1px dashed var(--primary-light);">
        <div style="text-align: center; color: var(--primary-color);">
            <i class="fa-solid fa-quote-right" style="font-size: 4rem; opacity: 0.2; margin-bottom: 1rem;"></i>
            <p style="font-style: italic; font-weight: 500; max-width: 300px; margin: 0 auto; color: var(--primary-dark);">"A ciência é a chave para o progresso da humanidade. Compartilhar suas descobertas é o primeiro passo."</p>
        </div>
    </div>
</section>

<section class="stats-grid">
    @foreach($statistics as $stat)
        <div class="stat-card">
            <div class="stat-number">{{ $stat['value'] }}</div>
            <div class="stat-label">{{ $stat['label'] }}</div>
        </div>
    @endforeach
</section>

<section class="categories-section">
    <h2 class="section-title">Nossas Principais Áreas de Publicação</h2>
    <div class="grid-cards">
        @foreach($categories as $category)
            <div class="card">
                <div class="card-icon">
                    <i class="fa-solid {{ $category['icon'] }}"></i>
                </div>
                <h3>{{ $category['title'] }}</h3>
                <p>{{ $category['description'] }}</p>
            </div>
        @endforeach
    </div>
</section>

<section class="workflow-section" style="background-color: var(--card-background); padding: 4rem 3rem; border-radius: var(--radius-xl); border: 1px solid var(--border-color); margin-bottom: 3rem;">
    <h2 class="section-title" style="margin-bottom: 2rem;">Fluxo Editorial de Submissão</h2>
    <div style="display: grid; grid-template-columns: repeat(3, 1fr); gap: 2rem; text-align: center;">
        <div style="padding: 1rem;">
            <div style="width: 50px; height: 50px; border-radius: 50%; background-color: var(--primary-color); color: white; display: flex; align-items: center; justify-content: center; font-weight: 700; font-size: 1.25rem; margin: 0 auto 1rem;">1</div>
            <h4 style="font-family: var(--font-heading); margin-bottom: 0.5rem; color: var(--primary-dark);">Envio do Trabalho</h4>
            <p style="color: var(--text-muted); font-size: 0.9rem;">Preencha o formulário informando o resumo, autores e a área de conhecimento da pesquisa.</p>
        </div>
        <div style="padding: 1rem;">
            <div style="width: 50px; height: 50px; border-radius: 50%; background-color: var(--primary-color); color: white; display: flex; align-items: center; justify-content: center; font-weight: 700; font-size: 1.25rem; margin: 0 auto 1rem;">2</div>
            <h4 style="font-family: var(--font-heading); margin-bottom: 0.5rem; color: var(--primary-dark);">Revisão por Pares</h4>
            <p style="color: var(--text-muted); font-size: 0.9rem;">O comitê editorial e revisores anônimos avaliarão a metodologia e a contribuição científica.</p>
        </div>
        <div style="padding: 1rem;">
            <div style="width: 50px; height: 50px; border-radius: 50%; background-color: var(--primary-color); color: white; display: flex; align-items: center; justify-content: center; font-weight: 700; font-size: 1.25rem; margin: 0 auto 1rem;">3</div>
            <h4 style="font-family: var(--font-heading); margin-bottom: 0.5rem; color: var(--primary-dark);">Publicação & Indexação</h4>
            <p style="color: var(--text-muted); font-size: 0.9rem;">Artigos aceitos serão publicados online, recebendo código DOI e ampla divulgação acadêmica.</p>
        </div>
    </div>
</section>
@endsection
