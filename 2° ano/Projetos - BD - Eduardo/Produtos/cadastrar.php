<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Relação de Produtos</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <h1>Área de Cadastro dos Produtos</h1>
        <form name="cliente" method="post" action="">
            <fieldset>
                <legend><b>Dados do Produto</b></legend>
                <div class="form-group">
                    <label for="txtnome">Nome:</label>
                    <input id="txtnome" name="txtnome" type="text" maxlength="40" placeholder="Nome do Produto">
                </div>
                <div class="form-group">
                    <label for="txtestoq">Estoque:</label>
                    <input id="txtestoq" name="txtestoq" type="number" min="0" placeholder="0">
                </div>
            </fieldset>
            <div class="form-actions">
                <button type="submit" name="btnenviar" class="btn">Cadastrar</button>
                <button type="reset" class="btn-voltar">Limpar</button>
                <a href="menu.html" class="btn-voltar">Voltar</a>
            </div>
        </form>
        <?php
        extract($_POST, EXTR_OVERWRITE);
        if (isset($btnenviar)) {
            include_once 'Produtos.php';
            $pro = new Produto();
            $pro->setNome($txtnome);
            $pro->setEstoque($txtestoq);
            echo "<h3><br><br>" . $pro->salvar() . "</h3>";
        }
        ?>
    </div>
</body>
</html>