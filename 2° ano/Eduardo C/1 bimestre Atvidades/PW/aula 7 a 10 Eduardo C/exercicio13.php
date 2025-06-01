<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $temperatura = $_POST['temperatura'];
    $tipo = $_POST['tipo'];

    if ($tipo == "fahrenheit") {
        // Converter Celsius para Fahrenheit
        $resultado = ($temperatura * 9/5) + 32;
        $tipo_resultado = "Fahrenheit";
    } elseif ($tipo == "celsius") {
        // Converter Fahrenheit para Celsius
        $resultado = ($temperatura - 32) * 5/9;
        $tipo_resultado = "Celsius";
    }

    echo "<h1>Resultado da Conversão</h1>";
    echo "<p>Temperatura informada: $temperatura</p>";
    echo "<p>A temperatura convertida é: $resultado $tipo_resultado</p>";
}
?>
