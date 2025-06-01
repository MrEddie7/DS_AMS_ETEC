<html>
    <head>

<title>Exercicio2</title>

</head>
<body>
<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $preco = $_POST['preco'];
    $desconto = $_POST['desconto'];
    $preco_com_desconto = $preco - ($preco * $desconto / 100);
    echo "<h3>Preço com Desconto: R$ " . number_format($preco_com_desconto, 2, ',', '.') . "</h3>";
    echo "<h3>Preço sem Desconto R$ " . number_format($preco, 2, ',', '.') . "</h3>";
    echo "<h3>Valor com Desconto: R$ " . number_format($desconto, 2, ',', '.') . "</h3>";

}
?>

</body>
</html>