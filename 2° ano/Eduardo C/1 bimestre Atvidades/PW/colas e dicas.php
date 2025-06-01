<?php

// Função para calcular a média dos preços
function calcularMedia($precos) {
    return array_sum($precos) / count($precos);
}

// Função para encontrar o maior preço
function maiorPreco($precos) {
    return max($precos);
}

// Função para encontrar o menor preço
function menorPreco($precos) {
    return min($precos);
}

// Recebendo os preços do usuário
$precos = [];
echo "Digite 4 preços:\n";
for ($i = 0; $i < 4; $i++) {
    $precos[$i] = (float) readline("Preço " . ($i + 1) . ": ");
}

// Calculando os valores
$media = calcularMedia($precos);
$maior = maiorPreco($precos);
$menor = menorPreco($precos);

// Exibindo os resultados
echo "\nResultados:\n";
echo "Média dos preços: " . number_format($media, 2) . "\n";
echo "Maior preço: " . number_format($maior, 2) . "\n";
echo "Menor preço: " . number_format($menor, 2) . "\n";

?>