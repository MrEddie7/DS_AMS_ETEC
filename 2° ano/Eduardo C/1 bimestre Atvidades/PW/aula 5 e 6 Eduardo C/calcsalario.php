<html>
    <head>
        <title>Calculo do salario</title>
    </head>
    <body>
        <?php

        $valor= $_POST["txthoras"];
        $horas= $_POST["vsalario"];
        $salario= $valor * $horas;

        echo "de acordo com o informado você trabalha". $salario;



        ?>
    </body>
</html>