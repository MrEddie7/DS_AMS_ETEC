<?php

namespace App\Http\Controllers;

use App\Models\Appointment;
use Illuminate\Http\Request;

class AppointmentController extends Controller
{
    public function index()
    {
        return Appointment::with(['client', 'barber', 'service.category'])
            ->orderByDesc('scheduled_at')
            ->get();
    }

    public function store(Request $request)
    {
        $data = $request->validate([
            'client_id' => ['required', 'exists:clients,id'],
            'barber_id' => ['required', 'exists:barbers,id'],
            'service_id' => ['required', 'exists:services,id'],
            'scheduled_at' => ['required', 'date'],
            'paid_amount' => ['required', 'numeric', 'min:0'],
        ]);

        return Appointment::create($data);
    }

    public function show(Appointment $appointment)
    {
        return $appointment->load(['client', 'barber', 'service.category']);
    }

    public function update(Request $request, Appointment $appointment)
    {
        $data = $request->validate([
            'client_id' => ['sometimes', 'required', 'exists:clients,id'],
            'barber_id' => ['sometimes', 'required', 'exists:barbers,id'],
            'service_id' => ['sometimes', 'required', 'exists:services,id'],
            'scheduled_at' => ['sometimes', 'required', 'date'],
            'paid_amount' => ['sometimes', 'required', 'numeric', 'min:0'],
        ]);

        $appointment->update($data);

        return $appointment;
    }

    public function destroy(Appointment $appointment)
    {
        $appointment->delete();

        return response()->noContent();
    }
}
