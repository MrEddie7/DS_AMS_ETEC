<?php

namespace App\Http\Controllers;

use App\Models\Contact;
use App\Models\Course;
use App\Models\Event;
use Illuminate\Http\Request;
use Inertia\Inertia;

class PublicController extends Controller
{
    /**
     * Display the home page.
     */
    public function home()
    {
        return Inertia::render('Welcome');
    }

    /**
     * Display all courses.
     */
    public function cursos()
    {
        $courses = Course::all();

        return Inertia::render('Cursos', [
            'courses' => $courses,
        ]);
    }

    /**
     * Display all events.
     */
    public function eventos()
    {
        $events = Event::orderBy('date', 'desc')->get();

        return Inertia::render('Eventos', [
            'events' => $events,
        ]);
    }

    /**
     * Display the contact form.
     */
    public function formulario()
    {
        return Inertia::render('Formulario');
    }

    /**
     * Submit the contact form.
     */
    public function submitForm(Request $request)
    {
        $validated = $request->validate([
            'name' => ['required', 'string', 'max:255'],
            'email' => ['required', 'email', 'max:255'],
            'message' => ['required', 'string', 'min:10'],
        ]);

        Contact::create($validated);

        return redirect()->route('formulario')->with('success', 'Obrigado por entrar em contato!');
    }
}
