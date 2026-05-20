@extends('layouts.app')

@section('title', 'Sobre a Revista - SubmitArtigo')

@section('content')
<h1 class="section-title">Sobre a Nossa Revista</h1>

<div class="about-grid">
    <div class="about-text">
        <h2>Nossa Missão</h2>
        <p>A SubmitArtigo nasceu com o compromisso de democratizar e agilizar a publicação científica de alto nível. Promovemos a disseminação de novos conhecimentos gerados por universidades, institutos de tecnologia e centros de desenvolvimento ao redor do globo.</p>
        <p>Garantimos um processo rigoroso de avaliação cega pelos pares (double-blind peer review), preservando a imparcialidade e a integridade de todas as produções submetidas à nossa análise editorial.</p>
        
        <h2 style="margin-top: 2rem;">Indexadores Internacionais</h2>
        <p>Todos os artigos aceitos e publicados em nossa revista são integrados nas principais bases de dados internacionais de indexação, assegurando visibilidade global para a sua pesquisa:</p>
        <ul style="list-style: none; display: flex; gap: 1.5rem; margin-top: 1rem; flex-wrap: wrap;">
            <li style="background-color: var(--card-background); padding: 0.5rem 1rem; border-radius: var(--radius-sm); border: 1px solid var(--border-color); font-weight: 600; color: var(--primary-color);">Scopus</li>
            <li style="background-color: var(--card-background); padding: 0.5rem 1rem; border-radius: var(--radius-sm); border: 1px solid var(--border-color); font-weight: 600; color: var(--primary-color);">Web of Science</li>
            <li style="background-color: var(--card-background); padding: 0.5rem 1rem; border-radius: var(--radius-sm); border: 1px solid var(--border-color); font-weight: 600; color: var(--primary-color);">Google Scholar</li>
            <li style="background-color: var(--card-background); padding: 0.5rem 1rem; border-radius: var(--radius-sm); border: 1px solid var(--border-color); font-weight: 600; color: var(--primary-color);">DOAJ</li>
        </ul>
    </div>
    
    <div style="background-color: var(--card-background); padding: 3rem; border-radius: var(--radius-xl); border: 1px solid var(--border-color); box-shadow: var(--shadow-md);">
        <h3 style="font-family: var(--font-heading); color: var(--primary-dark); font-size: 1.5rem; margin-bottom: 1.5rem; border-bottom: 2px solid var(--secondary-color); padding-bottom: 0.5rem;">Corpo Editorial</h3>
        <p style="color: var(--text-muted); font-size: 0.95rem; margin-bottom: 1.5rem;">Pesquisadores doutores que coordenam e avaliam a política editorial de nossa plataforma:</p>
        
        <div style="display: flex; flex-direction: column; gap: 1.5rem;">
            @foreach($editorialBoard as $member)
                <div style="border-left: 3px solid var(--primary-color); padding-left: 1rem;">
                    <h4 style="font-family: var(--font-heading); font-size: 1.05rem; color: var(--primary-dark); margin-bottom: 0.1rem;">{{ $member['name'] }}</h4>
                    <p style="font-size: 0.85rem; font-weight: 600; color: var(--secondary-color); text-transform: uppercase; margin-bottom: 0.2rem;">{{ $member['role'] }}</p>
                    <p style="font-size: 0.85rem; color: var(--text-muted);">{{ $member['institution'] }}</p>
                </div>
            @endforeach
        </div>
    </div>
</div>
@endsection
