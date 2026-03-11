<!DOCTYPE html>
<html lang="{{ str_replace('_', '-', app()->getLocale()) }}">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>{{ config('app.name', 'Laravel') }}</title>

    <!-- include styles and scripts built by Vite -->
    @if (file_exists(public_path('build/manifest.json')) || file_exists(public_path('hot')))
        @vite(['resources/css/app.css', 'resources/js/app.js'])
    @endif
</head>
<body class="bg-gray-100 text-gray-800">
    <nav class="bg-white shadow mb-8">
        <div class="container mx-auto px-4 py-3 flex justify-between items-center">
            <div class="font-semibold text-lg">
                <a href="{{ url('/') }}">{{ config('app.name', 'Laravel') }}</a>
            </div>
            <ul class="flex gap-4">
                <li><a class="hover:text-red-600" href="{{ url('/') }}">Home</a></li>
                <li><a class="hover:text-red-600" href="{{ url('/login') }}">Login</a></li>
                <li><a class="hover:text-red-600" href="{{ url('/cadastro') }}">Cadastro</a></li>
            </ul>
        </div>
    </nav>

    <main class="container mx-auto px-4">
        @yield('content')
    </main>
</body>
</html>