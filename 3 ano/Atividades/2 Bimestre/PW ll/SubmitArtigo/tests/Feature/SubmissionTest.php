<?php

namespace Tests\Feature;

use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Http\UploadedFile;
use Illuminate\Support\Facades\Storage;
use Tests\TestCase;
use App\Models\Submission;

class SubmissionTest extends TestCase
{
    use RefreshDatabase;

    /**
     * Testa se a página do formulário de submissão carrega corretamente.
     */
    public function test_submission_form_page_loads_successfully(): void
    {
        $response = $this->get(route('submission.create'));

        $response->assertStatus(200);
        $response->assertSee('Submeter Artigo Científico');
    }

    /**
     * Testa se a submissão de um artigo com arquivo é realizada com sucesso.
     */
    public function test_user_can_submit_article_with_file(): void
    {
        Storage::fake('local');

        $file = UploadedFile::fake()->create('artigo_cientifico.pdf', 500, 'application/pdf');

        $data = [
            'title' => 'Os Impactos da Computação Quântica na Criptografia Moderna',
            'author' => 'Dr. Alan Turing',
            'email' => 'turing@cambridge.edu',
            'area' => 'Ciência da Computação',
            'coauthors' => 'Alonzo Church, John von Neumann',
            'abstract' => 'Este trabalho apresenta um estudo aprofundado dos impactos causados pelo avanço da computação quântica nos atuais sistemas criptográficos de chave pública baseados em RSA e curvas elípticas.',
            'file' => $file,
        ];

        $response = $this->post(route('submission.store'), $data);

        $response->assertStatus(200);
        $response->assertSee('Submissão Concluída com Sucesso!');

        // Verifica a inserção no banco de dados
        $this->assertDatabaseHas('submissions', [
            'title' => $data['title'],
            'author' => $data['author'],
            'email' => $data['email'],
            'area' => $data['area'],
            'coauthors' => $data['coauthors'],
            'status' => 'Pendente',
        ]);

        // Verifica o arquivo no storage
        $submission = Submission::first();
        $this->assertNotNull($submission);
        Storage::disk('local')->assertExists($submission->file_path);
    }

    /**
     * Testa a validação do formulário de submissão.
     */
    public function test_submission_validation_fails_for_invalid_data(): void
    {
        $response = $this->post(route('submission.store'), [
            'title' => 'abc', // menor que 5
            'author' => '',
            'email' => 'email-invalido',
            'area' => '',
            'abstract' => 'curto',
        ]);

        $response->assertSessionHasErrors(['title', 'author', 'email', 'area', 'abstract', 'file']);
    }

    /**
     * Testa a listagem das submissões no painel.
     */
    public function test_submissions_are_listed_on_dashboard(): void
    {
        $submission = Submission::create([
            'protocol' => 'SUB-20260527-9999',
            'title' => 'Inteligência Artificial Aplicada a Medicina de Precisão',
            'author' => 'Dra. Marie Curie',
            'email' => 'marie@sorbonne.fr',
            'area' => 'Ciências da Saúde',
            'abstract' => 'Resumo do artigo sobre IA e medicina de precisão.',
            'file_path' => 'submissions/fakefile.pdf',
            'status' => 'Pendente',
        ]);

        $response = $this->get(route('submission.index'));

        $response->assertStatus(200);
        $response->assertSee('Painel de Acompanhamento');
        $response->assertSee($submission->title);
        $response->assertSee($submission->protocol);
        $response->assertSee($submission->author);
    }

    /**
     * Testa a visualização de detalhes e alteração de status.
     */
    public function test_user_can_view_submission_details_and_update_status(): void
    {
        $submission = Submission::create([
            'protocol' => 'SUB-20260527-1111',
            'title' => 'Novos Avanços na Síntese de Grafeno para Semicondutores',
            'author' => 'Dr. Richard Feynman',
            'email' => 'feynman@caltech.edu',
            'area' => 'Engenharia & Tecnologia',
            'abstract' => 'Resumo do trabalho sobre grafeno e semicondutores.',
            'file_path' => 'submissions/fakefile.docx',
            'status' => 'Pendente',
        ]);

        $showResponse = $this->get(route('submission.show', $submission->id));
        $showResponse->assertStatus(200);
        $showResponse->assertSee($submission->title);
        $showResponse->assertSee('Detalhes do Artigo Científico');

        // Atualiza status
        $updateResponse = $this->patch(route('submission.updateStatus', $submission->id), [
            'status' => 'Aceito',
        ]);

        $updateResponse->assertRedirect(route('submission.show', $submission->id));
        
        $this->assertDatabaseHas('submissions', [
            'id' => $submission->id,
            'status' => 'Aceito',
        ]);
    }
}
