<html>
    <head>
        <title>Questão 2</title>
    </head>
    <body>
        <?php

$tamanho= $_POST["tamanho"];

$cobertura = $tamanho / 6;
if ($cobertura >= 18){
    $calc = $cobertura / 18;
    $calc2 = $calc * 80;
    

    echo "Sua pintura sera enorme e Você precisara usar: ". round($cobertura, 2 ) . " e para isso precisara de: ". round($calc, 2). " litros de tinta e gastara: ". round($calc2, 2). " Obrigado pela compra";

} elseif ( $cobertura < 18){

$calc3 = $cobertura / 3.6;
$calc4 = $calc3 * 25;

echo "Sua pintura sera pequena e Você precisara usar: " . round($cobertura, 2) ." e para isso precisara de: " . round($calc3, 2). " litros de tinta e gastara: " . round($calc4, 2). " Obrigado pela compra";


}

?>
    </body>
</html>
