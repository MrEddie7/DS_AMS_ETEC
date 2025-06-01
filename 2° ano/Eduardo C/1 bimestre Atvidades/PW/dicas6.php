<?php
$base = 3;   // Número base
$expoente = -2; // Número expoente

if ($expoente >= 0) {
    $resultado = pow($base, $expoente);
    echo "$base elevado à potência $expoente é: $resultado";
} else {
    $resultado = 1 / pow($base, abs($expoente));
    echo "$base elevado à potência $expoente é: $resultado";
}
?>
