<html>
    <head>
        <title>
            Dados dos Alunos
        </title>
    </head>
    <body>
        <?php
        $Ttext= $_POST["Text"];
        $Tnum= $_POST["numero"];
        $Trg= $_POST["RG"];
        $cur= $_POST["cbocursos"];
        $txtmod= $_POST["txtmod"];

        echo "segue as informações citadas anteriormente<br>";
        echo "nome do aluno: ".  $Ttext . ".<br>".
        "Numero do aluno: ". $Tnum . ".<br>".
        "RG do aluno: ". $Trg .".<p>";

        echo "informações do curso <br>";
        echo "curso do aluno: ". $cur . "<br>"."Modulo do aluno: ".$txtmod;
        ?>
    </body>
</html>
