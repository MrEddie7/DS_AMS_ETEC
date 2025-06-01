<html>
    <head>

<title>Exercicio5</title>

</head>
<body>
<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $num1 = $_POST['num1'];
    $num2 = $_POST['num2'];
    $num3 = $_POST['num3'];

    $soma_dos_quadrados = pow($num1, 2) + pow($num2, 2) + pow($num3, 2);

    echo "A soma dos quadrados é: $soma_dos_quadrados";
}
?>
</body>
</html>