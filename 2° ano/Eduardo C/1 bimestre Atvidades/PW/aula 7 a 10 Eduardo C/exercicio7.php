<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $nota1 = $_POST['nota1'];
    $nota2 = $_POST['nota2'];
    $nota3 = $_POST['nota3'];
    $nota4 = $_POST['nota4'];
    
    $media = ($nota1 + $nota2 + $nota3 + $nota4) / 4;
    
    if ($media >= 6) {
        echo "Aprovado com média: " . number_format($media, 2);
    } elseif ($media < 3) {
        echo "Retido com média: " . number_format($media, 2);
    } else {
        echo "Exame com média: " . number_format($media, 2);
    }
}
?>
