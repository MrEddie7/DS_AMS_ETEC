<html>
    <head>

<title>Exercicio01</title>

</head>
<body>
<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $numero = $_POST['numero'];
    echo "<h3>Tabuada de $numero:</h3>";
    for ($i = 1; $i <= 10; $i++) {
        echo "$numero x $i = " . ($numero * $i) . "<br>";
    }
}
?>


</body>
</html>