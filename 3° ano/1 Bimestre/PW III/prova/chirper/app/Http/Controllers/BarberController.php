<?php

namespace App\Http\Controllers;

use App\Models\Barber;
use Illuminate\Http\Request;
use Illuminate\Validation\Rule;

class BarberController extends Controller
{
    public function index()
    {
        return Barber::orderBy('name')->get();
    }

    public function store(Request $request)
    {
        $data = $request->validate([
            'name' => ['required', 'string', 'max:255'],
            'cpf' => ['required', 'string', 'size:11', 'regex:/^\d{11}$/', 'unique:barbers,cpf'],
        ]);

        return Barber::create($data);
    }

    public function show(Barber $barber)
    {
        return $barber;
    }

    public function update(Request $request, Barber $barber)
    {
        $data = $request->validate([
            'name' => ['sometimes', 'required', 'string', 'max:255'],
            'cpf' => [
                'sometimes',
                'required',
                'string',
                'size:11',
                'regex:/^\d{11}$/',
                Rule::unique('barbers', 'cpf')->ignore($barber->id),
            ],
        ]);

        $barber->update($data);

        return $barber;
    }

    public function destroy(Barber $barber)
    {
        $barber->delete();

        return response()->noContent();
    }
}
