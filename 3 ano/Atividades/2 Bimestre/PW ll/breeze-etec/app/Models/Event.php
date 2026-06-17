<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Event extends Model
{
    /** @use HasFactory<\Database\Factories\EventFactory> */
    use HasFactory;

    protected $fillable = ['title', 'description', 'date', 'location'];

    protected function casts(): array
    {
        return [
            'date' => 'datetime',
        ];
    }
