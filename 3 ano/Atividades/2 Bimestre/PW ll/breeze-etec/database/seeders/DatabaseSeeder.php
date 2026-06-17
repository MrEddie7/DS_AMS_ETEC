<?php

namespace Database\Seeders;

use App\Models\Contact;
use App\Models\Course;
use App\Models\Event;
use App\Models\Grade;
use App\Models\User;
use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     */
    public function run(): void
    {
        // Create a test user
        $user = User::factory()->create([
            'name' => 'Test User',
            'email' => 'test@example.com',
        ]);

        // Create courses
        Course::factory(5)->create();

        // Create events
        Event::factory(8)->create();

        // Create grades for the test user
        Grade::factory(10)->create([
            'user_id' => $user->id,
        ]);

        // Create contacts
        Contact::factory(15)->create();
    }
}
