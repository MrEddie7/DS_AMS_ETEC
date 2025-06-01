<?php
$soma = 0;
$quantidade = 0;

while (true) {
    $numero = (int)readline("Digite um número (0 para sair): ");
    
    if ($numero == 0) {
        break;
    }

    $soma += $numero;
    $quantidade++;
}

if ($quantidade > 0) {
    $media = $soma / $quantidade;
    echo "A média dos números é: $media";
} else {
    echo "Nenhum número foi inserido.";
}
?>

