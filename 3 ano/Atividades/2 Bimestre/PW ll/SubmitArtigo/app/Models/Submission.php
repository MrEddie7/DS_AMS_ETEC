<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Submission extends Model
{
    /**
     * Os atributos que podem ser preenchidos em massa.
     *
     * @var array<int, string>
     */
    protected $fillable = [
        'protocol',
        'title',
        'author',
        'email',
        'area',
        'coauthors',
        'abstract',
        'file_path',
        'status',
    ];
}
