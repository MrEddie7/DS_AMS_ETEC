<html>
    <head>
        <title>Questão 3</title>
    </head>
    <body>
        <?php

$km= $_POST["km"];
$litros = $_POST["litros"];

$consumo = $km / $litros;

echo "A média de consumo é de: ". $consumo;

?>
    </body>
</html>