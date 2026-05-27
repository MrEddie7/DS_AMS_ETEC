<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Submission;
use Illuminate\Support\Facades\Storage;

class SubmissionController extends Controller
{
    /**
     * Exibe o painel com a listagem de todos os artigos submetidos.
     */
    public function index(Request $request)
    {
        $areas = [
            'Ciência da Computação',
            'Engenharia & Tecnologia',
            'Ciências da Saúde',
            'Ciências Humanas e Sociais',
            'Física & Astronomia',
            'Matemática e Estatística'
        ];

        $statuses = [
            'Pendente',
            'Em Revisão',
            'Aceito',
            'Rejeitado'
        ];

        $query = Submission::query();

        // Filtro por busca de texto (título, autor, protocolo)
        if ($request->filled('search')) {
            $search = $request->input('search');
            $query->where(function ($q) use ($search) {
                $q->where('title', 'like', "%{$search}%")
                  ->orWhere('author', 'like', "%{$search}%")
                  ->orWhere('protocol', 'like', "%{$search}%");
            });
        }

        // Filtro por área científica
        if ($request->filled('area')) {
            $query->where('area', $request->input('area'));
        }

        // Filtro por status de revisão
        if ($request->filled('status')) {
            $query->where('status', $request->input('status'));
        }

        $submissions = $query->orderBy('created_at', 'desc')->get();

        return view('submissions.index', compact('submissions', 'areas', 'statuses'));
    }

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
     * Processa a submissão de um artigo científico no banco e salva o arquivo.
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
            'file' => 'required|file|mimes:pdf,doc,docx|max:10240',
        ], [
            'title.required' => 'O título do artigo é obrigatório.',
            'title.min' => 'O título deve conter pelo menos 5 caracteres.',
            'author.required' => 'O nome do autor principal é obrigatório.',
            'author.min' => 'O nome do autor deve conter pelo menos 3 caracteres.',
            'email.required' => 'O e-mail de contato é obrigatório.',
            'email.email' => 'Insira um endereço de e-mail válido.',
            'area.required' => 'Selecione a área do conhecimento do seu artigo.',
            'abstract.required' => 'O resumo do artigo é obrigatório.',
            'abstract.min' => 'O resumo deve conter pelo menos 10 caracteres.',
            'file.required' => 'O upload do arquivo do artigo é obrigatório.',
            'file.mimes' => 'O arquivo deve estar no formato PDF, DOC ou DOCX.',
            'file.max' => 'O arquivo não pode exceder o tamanho limite de 10 MB.',
        ]);

        // Processa o upload do arquivo de forma segura
        $filePath = '';
        if ($request->hasFile('file') && $request->file('file')->isValid()) {
            $file = $request->file('file');
            // Salva na pasta 'submissions' dentro do disco 'private' (ou 'local')
            $filePath = $file->store('submissions', 'local');
        }

        // Geração do número de protocolo exclusivo
        $protocol = 'SUB-' . date('Ymd') . '-' . rand(1000, 9999);

        // Cria o registro no banco de dados SQLite
        $submission = Submission::create([
            'protocol' => $protocol,
            'title' => $validatedData['title'],
            'author' => $validatedData['author'],
            'email' => $validatedData['email'],
            'area' => $validatedData['area'],
            'coauthors' => $validatedData['coauthors'] ?? null,
            'abstract' => $validatedData['abstract'],
            'file_path' => $filePath,
            'status' => 'Pendente'
        ]);

        // Retorna a view de sucesso com a nova submissão do banco
        return view('submissions.success', compact('submission'));
    }

    /**
     * Exibe os detalhes de uma submissão específica.
     */
    public function show($id)
    {
        $submission = Submission::findOrFail($id);
        
        $statuses = [
            'Pendente',
            'Em Revisão',
            'Aceito',
            'Rejeitado'
        ];

        return view('submissions.show', compact('submission', 'statuses'));
    }

    /**
     * Permite fazer o download do documento submetido.
     */
    public function download($id)
    {
        $submission = Submission::findOrFail($id);
        $filePath = $submission->file_path;

        if (Storage::disk('local')->exists($filePath)) {
            $extension = pathinfo($filePath, PATHINFO_EXTENSION);
            // Nome personalizado para o download do arquivo
            $fileName = str_replace(' ', '_', $submission->title);
            $fileName = substr(preg_replace('/[^A-Za-z0-9\_]/', '', $fileName), 0, 50);
            return Storage::disk('local')->download($filePath, "{$submission->protocol}_{$fileName}.{$extension}");
        }

        return abort(404, 'O arquivo solicitado não foi encontrado no servidor.');
    }

    /**
     * Altera o status da revisão do artigo.
     */
    public function updateStatus($id, Request $request)
    {
        $submission = Submission::findOrFail($id);

        $validated = $request->validate([
            'status' => 'required|string|in:Pendente,Em Revisão,Aceito,Rejeitado'
        ]);

        $submission->update([
            'status' => $validated['status']
        ]);

        return redirect()->route('submission.show', $id)
            ->with('success', "O status do protocolo {$submission->protocol} foi atualizado para '{$validated['status']}' com sucesso!");
    }
}
