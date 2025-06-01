<html>
    <head>
        <title>ação</title>
    </head>
    <body>
        <?php
        $VNome= $_POST["Nome"];
        $VIdade= $_POST["Idade"];

        // Exibe a mensagem corretamente
        echo "Oi, seu nome é ". $VNome ." ."."<br>"."você tem: ". $VIdade . "anos";
        
        ?>
    </body>
</html>
