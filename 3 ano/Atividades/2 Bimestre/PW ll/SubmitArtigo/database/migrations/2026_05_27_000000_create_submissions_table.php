<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('submissions', function (Blueprint $table) {
            $table->id();
            $table->string('protocol')->unique();
            $table->string('title');
            $table->string('author');
            $table->string('email');
            $table->string('area');
            $table->string('coauthors')->nullable();
            $table->text('abstract');
            $table->string('file_path');
            $table->string('status')->default('Pendente'); // Pendente, Em Revisão, Aceito, Rejeitado
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('submissions');
    }
};
