<html>
    <head>

<title>Exercicio3</title>

</head>
<body>
<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $nota1 = $_POST['nota1'];
    $nota2 = $_POST['nota2'];
    $nota3 = $_POST['nota3'];
    $nota4 = $_POST['nota4'];
    $media = ($nota1 + $nota2 + $nota3 + $nota4) / 4;

    if ($media >= 5) {
        echo "Aprovado com média: " . number_format($media, 2);
    } else {
        echo "Reprovado com média: " . number_format($media, 2);
    }
}
?>

</body>
</html>