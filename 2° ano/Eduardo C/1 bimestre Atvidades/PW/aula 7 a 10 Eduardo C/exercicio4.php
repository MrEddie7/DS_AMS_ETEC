<html>
    <head>

<title>Exercicio4</title>

</head>
<body>
<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $a = $_POST['a'];
    $b = $_POST['b'];
    list($a, $b) = array($b, $a);
    echo "Novo valor de A: $a<br>";
    echo "Novo valor de B: $b";
}
?>

</body>
</html>