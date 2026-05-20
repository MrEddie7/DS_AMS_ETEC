@extends('layouts.app')

@section('title', 'Contato - SubmitArtigo')

@section('content')
<h1 class="section-title">Entre em Contato</h1>

<div class="contact-layout">
    <div class="contact-info">
        <h2>Canais de Atendimento</h2>
        <p style="color: var(--text-muted); margin-bottom: 2rem;">Tem alguma dúvida sobre o processo de submissão, critérios de avaliação ou status de publicação do seu artigo? Entre em contato conosco através dos seguintes meios:</p>
        
        <div class="contact-method">
            <div class="contact-icon">
                <i class="fa-solid fa-envelope"></i>
            </div>
            <div>
                <h4>E-mail Editorial</h4>
                <p>editorial@submitartigo.org</p>
                <p style="font-size: 0.85rem; color: var(--text-muted);">Tempo de resposta: até 48h úteis</p>
            </div>
        </div>

        <div class="contact-method">
            <div class="contact-icon">
                <i class="fa-solid fa-location-dot"></i>
            </div>
            <div>
                <h4>Endereço Físico</h4>
                <p>Av. das Nações Acadêmicas, 1000 - Bloco C</p>
                <p>Parque Científico e Tecnológico - São Paulo/SP</p>
            </div>
        </div>

        <div class="contact-method">
            <div class="contact-icon">
                <i class="fa-solid fa-phone"></i>
            </div>
            <div>
                <h4>Telefone de Suporte</h4>
                <p>+55 (11) 3456-7890</p>
                <p style="font-size: 0.85rem; color: var(--text-muted);">Atendimento de Segunda a Sexta, das 9h às 17h</p>
            </div>
        </div>
    </div>

    <div style="background-color: var(--card-background); padding: 3rem; border-radius: var(--radius-xl); border: 1px solid var(--border-color); box-shadow: var(--shadow-md);">
        <h3 style="font-family: var(--font-heading); color: var(--primary-dark); font-size: 1.5rem; margin-bottom: 1.5rem;">Fale Conosco</h3>
        <p style="color: var(--text-muted); font-size: 0.95rem; margin-bottom: 2rem;">Envie sua mensagem rápida e nossa equipe de suporte entrará em contato.</p>
        
        <form action="#" method="GET" onsubmit="event.preventDefault(); alert('Mensagem enviada com sucesso! (Apenas simulação)');">
            <div class="form-group">
                <label for="contact-name">Nome Completo</label>
                <input type="text" class="form-control" id="contact-name" placeholder="Seu nome completo" required>
            </div>
            <div class="form-group">
                <label for="contact-email">E-mail</label>
                <input type="email" class="form-control" id="contact-email" placeholder="seu.email@exemplo.com" required>
            </div>
            <div class="form-group">
                <label for="contact-message">Sua Mensagem</label>
                <textarea class="form-control" id="contact-message" placeholder="Como podemos ajudar você hoje?" style="min-height: 120px;" required></textarea>
            </div>
            <button type="submit" class="btn btn-primary" id="btn-submit-contact" style="width: 100%;">
                Enviar Mensagem
            </button>
        </form>
    </div>
</div>
@endsection
