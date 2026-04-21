<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $inicio = $_POST['inicio'];
    $fim = $_POST['fim'];

    $impares = [];
    for ($i = $fim; $i >= $inicio; $i--) {
        if ($i % 2 != 0) {
            $impares[] = $i;
        }
    }

    echo "Números ímpares em ordem decrescente entre $inicio e $fim: " . implode(", ", $impares);
}
?>
