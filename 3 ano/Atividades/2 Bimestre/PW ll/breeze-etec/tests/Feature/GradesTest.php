<?php

use App\Models\Grade;
use App\Models\User;

test('guests are redirected to the login page when accessing grades', function () {
    $response = $this->get('/dashboard/grades');
    $response->assertRedirect('/login');
});

test('authenticated users can view their grades dashboard', function () {
    $user = User::factory()->create();
    $this->actingAs($user);

    $response = $this->get('/dashboard/grades');
    $response->assertStatus(200);
    $response->assertInertia(function ($page) {
        $page->component('Grades');
    });
});

test('grades are passed to the grades page', function () {
    $user = User::factory()->create();
    $grades = Grade::factory(3)->create(['user_id' => $user->id]);

    $this->actingAs($user);
    $response = $this->get('/dashboard/grades');

    $response->assertInertia(function ($page) use ($grades) {
        $page->has('grades', 3);
        $page->where('grades.0.id', $grades->first()->id);
    });
});

test('authenticated users can create a new grade', function () {
    $user = User::factory()->create();
    $this->actingAs($user);

    $response = $this->post('/dashboard/grades', [
        'student_name' => 'João Silva',
        'subject' => 'Matemática',
        'score' => 8.5,
    ]);

    $response->assertRedirect('/dashboard/grades');
    $this->assertDatabaseHas('grades', [
        'user_id' => $user->id,
        'student_name' => 'João Silva',
        'subject' => 'Matemática',
        'score' => 8.5,
    ]);
});

test('grade creation requires valid input', function () {
    $user = User::factory()->create();
    $this->actingAs($user);

    // Missing required fields
    $response = $this->post('/dashboard/grades', []);
    $response->assertSessionHasErrors(['student_name', 'subject', 'score']);

    // Invalid score
    $response = $this->post('/dashboard/grades', [
        'student_name' => 'João Silva',
        'subject' => 'Matemática',
        'score' => 15, // Invalid: > 10
    ]);
    $response->assertSessionHasErrors('score');

    // Score below minimum
    $response = $this->post('/dashboard/grades', [
        'student_name' => 'João Silva',
        'subject' => 'Matemática',
        'score' => -1, // Invalid: < 0
    ]);
    $response->assertSessionHasErrors('score');
});

test('users can delete their own grades', function () {
    $user = User::factory()->create();
    $grade = Grade::factory()->create(['user_id' => $user->id]);

    $this->actingAs($user);
    $response = $this->delete("/dashboard/grades/{$grade->id}");

    $response->assertRedirect('/dashboard/grades');
    $this->assertDatabaseMissing('grades', ['id' => $grade->id]);
});

test('users cannot delete grades from other users', function () {
    $user1 = User::factory()->create();
    $user2 = User::factory()->create();
    $grade = Grade::factory()->create(['user_id' => $user1->id]);

    $this->actingAs($user2);
    $response = $this->delete("/dashboard/grades/{$grade->id}");

    $response->assertForbidden();
    $this->assertDatabaseHas('grades', ['id' => $grade->id]);
});

test('unauthenticated users cannot create grades', function () {
    $response = $this->post('/dashboard/grades', [
        'student_name' => 'João Silva',
        'subject' => 'Matemática',
        'score' => 8.5,
    ]);

    $response->assertRedirect('/login');
    $this->assertDatabaseMissing('grades', ['student_name' => 'João Silva']);
});

test('unauthenticated users cannot delete grades', function () {
    $grade = Grade::factory()->create();

    $response = $this->delete("/dashboard/grades/{$grade->id}");
    $response->assertRedirect('/login');
});
