<html>
    <head>
        <title>Questão 1</title>
    </head>
    <body>
        <?php
 $preco = $_POST['Preco'];
 $nome = $_POST['nome'];

 $porce = $preco / 100;
 $calc = $porce * 16;
 $calctotal = $calc + $preco;
 $parcela = $calctotal / 10;

 echo "com o valor informado temos a compra com valor de: " . $calctotal. " com uma parcela de 10 vezes de: " . $parcela;



?>
</body>
</html>

