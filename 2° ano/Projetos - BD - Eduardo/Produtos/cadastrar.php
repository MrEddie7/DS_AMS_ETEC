<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Relação de Produtos</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <h1>Area de Cdastro dos Produtos</h1>
        <form name="cliente" method="post" action="">
            <fieldset id="a">
                <legend><b>Dados do Produto</b></legend>
                <p>Nome: <input name="txtnome" type="type" size="40" maxlength="40" placeholder="Nome do Produto"></p>
                <p>Estoque: <input name="txtestoq" type="text" size="10" placeholder="0"></p>
            </fieldset>
            <br>
            <fieldset id="b">
                <legend><b>Opções:</b></legend>
                <br>
                <input name="btnenviar" type="submit" value="Cadastrar"> &nbsp;&nbsp;
                <input name="limpar" type="reset" value="Limpar">

            </fieldset>
        </form>
        <?php
        extract($_POST, EXTR_OVERWRITE);
        if (isset($btnenviar)) {
            include_once 'Produtos.php';
            $pro=new Produto();
            $pro->setNome($txtnome);
            $pro->setEstoque($txtestoq);
            echo "<h3><br><br>" . $pro->salvar() . "</hr>";
        }
        ?>
        <br>
        <center>
            <button>
                <a href = "menu.html">Voltar</a>
            </button>
            
</body>
</html>