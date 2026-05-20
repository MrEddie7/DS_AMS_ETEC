<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class PageController extends Controller
{
    /**
     * Exibe a página inicial da plataforma.
     */
    public function home()
    {
        // Enviando dados mockados acadêmicos para renderização dinâmica
        $statistics = [
            ['value' => '142', 'label' => 'Edições Publicadas'],
            ['value' => '2.8K+', 'label' => 'Artigos Indexados'],
            ['value' => '94%', 'label' => 'Taxa de Aceitação Inicial'],
            ['value' => '28 dias', 'label' => 'Tempo Médio de Revisão'],
        ];

        $categories = [
            [
                'title' => 'Ciência da Computação',
                'description' => 'IA, Engenharia de Software, Redes e Computação de Alto Desempenho.',
                'icon' => 'fa-laptop-code'
            ],
            [
                'title' => 'Engenharia & Tecnologia',
                'description' => 'Novos materiais, automação, robótica e processos de manufatura inteligentes.',
                'icon' => 'fa-microchip'
            ],
            [
                'title' => 'Ciências da Saúde',
                'description' => 'Pesquisas clínicas, avanços biomédicos, epidemiologia e saúde pública.',
                'icon' => 'fa-briefcase-medical'
            ],
        ];

        return view('home', compact('statistics', 'categories'));
    }

    /**
     * Exibe a página "Sobre a Revista".
     */
    public function about()
    {
        $editorialBoard = [
            ['name' => 'Dr. Alexandre Silva', 'institution' => 'USP - Brasil', 'role' => 'Editor-Chefe'],
            ['name' => 'Dr. Marie Curie', 'institution' => 'Sorbonne - França', 'role' => 'Editora Associada de Física'],
            ['name' => 'Dr. Alan Turing', 'institution' => 'Cambridge - Reino Unido', 'role' => 'Editor Associado de Computação'],
        ];

        return view('about', compact('editorialBoard'));
    }

    /**
     * Exibe a página de Contato.
     */
    public function contact()
    {
        return view('contact');
    }
}
