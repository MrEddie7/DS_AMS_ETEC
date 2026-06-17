<?php

namespace App\Http\Controllers;

use App\Models\Grade;
use Illuminate\Http\Request;
use Inertia\Inertia;

class GradeController extends Controller
{
    /**
     * Display grades for the authenticated user.
     */
    public function index()
    {
        $grades = auth()->user()->grades()->latest()->get();

        return Inertia::render('Grades', [
            'grades' => $grades,
        ]);
    }

    /**
     * Store a newly created grade.
     */
    public function store(Request $request)
    {
        $validated = $request->validate([
            'student_name' => ['required', 'string', 'max:255'],
            'subject' => ['required', 'string', 'max:255'],
            'score' => ['required', 'numeric', 'min:0', 'max:10'],
        ]);

        auth()->user()->grades()->create($validated);

        return redirect()->route('grades.index')->with('success', 'Nota criada com sucesso!');
    }

    /**
     * Delete a grade.
     */
    public function destroy(Grade $grade)
    {
        $this->authorize('delete', $grade);

        $grade->delete();

        return redirect()->route('grades.index')->with('success', 'Nota deletada com sucesso!');
    }
}
