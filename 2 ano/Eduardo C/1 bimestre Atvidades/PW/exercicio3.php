<html>
    <head>
        <title>teste</title>

    </head >
    <body>
       <?php
        
    // variaveis do arquivo 

       $e = 15;
       $s = 14;
       $var1 = 1; 
       $varX = 9.50; //float
       $varY = 9.50;
       $var2 = $varX + $varY;
       $varstr = "texto aleatorio da variavel varstr";
       $varletra = "a"; 
       $varboll = true;
       

    // exercicio 3, feito aqui, para descomplicar o programador

    echo "<img src='image.png' alt='imagem de php'>";
    print "<p>";

    echo "exemplo de aplicação php";
    print "<p>";

    echo "variavel var1<br>";
    echo "$var1<br>";
    print "<P>";
    
    echo "variavel varX<br>";
    echo "$varX<br>";
    print "<P>";

    
    echo "variavel varY<br>";
    echo "$varY<br>";
    print "<P>";

    
    echo "variavel var2 (soma de variavel)<br>";
    echo "$var2<br>";
    print "<P>";

    
    echo "variavel varstr<br>";
    echo "$varstr<br>";
    print "<P>";

    
    echo "variavel varletra<br>";
    echo "$varletra<br>";
    print "<P>";

    
    echo "variavel varboll<br>";
    echo "$varboll<br>";
    print "<P>";

    // Cria uma image 55 x 30
$im = imagecreatetruecolor(55, 30);
$red = imagecolorallocate($im, 255, 0, 0);
$black = imagecolorallocate($im, 0, 0, 0);

// Define o fundo para transparente
imagecolortransparent($im, $black);

// Desenha um retângulo vermelho
imagefilledrectangle($im, 4, 4, 50, 25, $red);

// Grava a imagem
imagepng($im, './imagecolortransparent.png');

    



        ?>
    </body>
</html>