<?php

use App\Models\Client;
use Database\Factories\ClientFactory;
use Illuminate\Foundation\Testing\RefreshDatabase;

uses(RefreshDatabase::class);

test('client can be updated', function () {
    $client = ClientFactory::new()->create([
        'name' => 'João Silva',
        'phone' => '11999999999',
        'email' => 'joao@example.com',
    ]);

    $updatedData = [
        'name' => 'João Silva Atualizado',
        'phone' => '11888888888',
        'email' => 'joao.atualizado@example.com',
    ];

    $response = $this->patchJson("/api/clients/{$client->id}", $updatedData);

    $response->assertStatus(200)
             ->assertJson([
                 'id' => $client->id,
                 'name' => 'João Silva Atualizado',
                 'phone' => '11888888888',
                 'email' => 'joao.atualizado@example.com',
             ]);

    $client->refresh();
    expect($client->name)->toBe('João Silva Atualizado');
    expect($client->phone)->toBe('11888888888');
    expect($client->email)->toBe('joao.atualizado@example.com');
});