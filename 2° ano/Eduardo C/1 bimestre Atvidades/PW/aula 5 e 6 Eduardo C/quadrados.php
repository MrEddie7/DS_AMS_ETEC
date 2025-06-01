<html>
    <head>
        <title>Quadrados Calculo</title>
    </head>
    <body>
        <?php
        $n1= $_POST["n1"];
        $n2= $_POST["n2"];
        $soma= $n1 + $n2;
        $quadrado= pow($soma,2);

        echo "o quadrado do numero é: ". $quadrado;

        ?>
    </body>
</html>