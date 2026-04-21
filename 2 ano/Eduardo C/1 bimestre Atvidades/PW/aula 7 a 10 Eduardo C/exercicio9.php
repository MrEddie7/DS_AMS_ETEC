<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $inicio = $_POST['inicio'];
    $fim = $_POST['fim'];

    $soma = 0;
    for ($i = $inicio; $i <= $fim; $i++) {
        if ($i % 2 != 0) {
            $soma += $i;
        }
    }

    echo "A soma dos números ímpares entre $inicio e $fim é: $soma";
}
?>
