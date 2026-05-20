<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class SubmissionController extends Controller
{
    /**
     * Exibe o formulário de submissão de artigos.
     */
    public function create()
    {
        $areas = [
            'Ciência da Computação',
            'Engenharia & Tecnologia',
            'Ciências da Saúde',
            'Ciências Humanas e Sociais',
            'Física & Astronomia',
            'Matemática e Estatística'
        ];

        return view('submissions.create', compact('areas'));
    }

    /**
     * Processa a submissão de um artigo científico.
     */
    public function store(Request $request)
    {
        // Validação dos dados do formulário
        $validatedData = $request->validate([
            'title' => 'required|string|min:5|max:255',
            'author' => 'required|string|min:3|max:100',
            'email' => 'required|email|max:150',
            'area' => 'required|string',
            'coauthors' => 'nullable|string|max:255',
            'abstract' => 'required|string|min:10|max:3000',
        ], [
            'title.required' => 'O título do artigo é obrigatório.',
            'title.min' => 'O título deve conter pelo menos 5 caracteres.',
            'author.required' => 'O nome do autor principal é obrigatório.',
            'author.min' => 'O nome do autor deve conter pelo menos 3 caracteres.',
            'email.required' => 'O e-mail de contato é obrigatório.',
            'email.email' => 'Insira um endereço de e-mail válido.',
            'area.required' => 'Selecione a área do conhecimento do seu artigo.',
            'abstract.required' => 'O resumo do artigo é obrigatório.',
            'abstract.min' => 'O resumo deve conter pelo menos 10 caracteres.'
        ]);

        // Simula o processamento dos dados (ex: salvar no banco, disparar email, etc.)
        // Retornamos a view de sucesso repassando os dados validados
        return view('submissions.success', [
            'submission' => $validatedData,
            'protocol' => 'SUB-' . date('Ymd') . '-' . rand(1000, 9999)
        ]);
    }
}
