<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="csrf-token" content="{{ csrf_token() }}">
    <title>@yield('title', 'SubmitArtigo - Plataforma Científica')</title>
    
    <!-- Meta SEO -->
    <meta name="description" content="@yield('meta_description', 'Plataforma oficial para submissão e revisão por pares de artigos científicos da revista científica SubmitArtigo.')">
    <meta name="keywords" content="artigo científico, submissão acadêmica, peer review, ciência, publicação, revista acadêmica">
    <meta name="author" content="SubmitArtigo Team">
    
    <!-- Styles -->
    <link rel="stylesheet" href="{{ asset('css/style.css') }}">
    
    <!-- FontAwesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>

    <header>
        <nav class="nav-container">
            <a href="{{ route('home') }}" class="logo" id="nav-brand-logo">
                <i class="fa-solid fa-graduation-cap"></i> Submit<span>Artigo</span>
            </a>
            <ul class="nav-menu">
                <li>
                    <a href="{{ route('home') }}" 
                       class="nav-link {{ request()->routeIs('home') ? 'active' : '' }}" 
                       id="nav-link-home">
                       Início
                    </a>
                </li>
                <li>
                    <a href="{{ route('about') }}" 
                       class="nav-link {{ request()->routeIs('about') ? 'active' : '' }}" 
                       id="nav-link-about">
                       Sobre a Revista
                    </a>
                </li>
                <li>
                    <a href="{{ route('contact') }}" 
                       class="nav-link {{ request()->routeIs('contact') ? 'active' : '' }}" 
                       id="nav-link-contact">
                       Contato
                    </a>
                </li>
                <li>
                    <a href="{{ route('submission.index') }}" 
                       class="nav-link {{ request()->routeIs('submission.index') || request()->routeIs('submission.show') ? 'active' : '' }}" 
                       id="nav-link-submissions">
                       Painel
                    </a>
                </li>
                <li>
                    <a href="{{ route('submission.create') }}" 
                       class="nav-link btn-nav {{ request()->routeIs('submission.create') ? 'active' : '' }}" 
                       id="nav-link-submit">
                       Submeter Artigo
                    </a>
                </li>
            </ul>
        </nav>
    </header>

    <main>
        @yield('content')
    </main>

    <footer>
        <div class="footer-content">
            <div class="footer-brand">
                <h3>Submit<span>Artigo</span></h3>
                <p>Promovendo a divulgação da ciência e o avanço tecnológico através de publicações científicas de alto impacto e revisão criteriosa por pares.</p>
            </div>
            <div class="footer-links">
                <h4>Navegação</h4>
                <ul>
                    <li><a href="{{ route('home') }}" id="footer-link-home">Início</a></li>
                    <li><a href="{{ route('submission.index') }}" id="footer-link-index">Painel de Submissões</a></li>
                    <li><a href="{{ route('about') }}" id="footer-link-about">Sobre a Revista</a></li>
                    <li><a href="{{ route('contact') }}" id="footer-link-contact">Contato</a></li>
                </ul>
            </div>
            <div class="footer-links">
                <h4>Serviços</h4>
                <ul>
                    <li><a href="{{ route('submission.create') }}" id="footer-link-submit">Submeter Trabalho</a></li>
                    <li><a href="#" id="footer-link-guidelines">Diretrizes para Autores</a></li>
                    <li><a href="#" id="footer-link-ethics">Ética de Publicação</a></li>
                </ul>
            </div>
        </div>
        <div class="footer-bottom">
            <p>&copy; {{ date('Y') }} SubmitArtigo. Todos os direitos reservados. Plataforma Acadêmica de Submissões.</p>
        </div>
    </footer>

</body>
</html>
