<html>
    <head>

<title>Exercicio6</title>

</head>
<body>
<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $salario_bruto = $_POST['salario_bruto'];

    // 10% de gratificação
    $gratificacao = $salario_bruto * 0.10;
    // 20% de imposto
    $imposto = $salario_bruto * 0.20;

    // Calcular salário líquido
    $salario_liquido = $salario_bruto + $gratificacao - $imposto;

    echo "Salário Líquido: R$ " . number_format($salario_liquido, 2, ',', '.');
}
?>

</body>
</html>