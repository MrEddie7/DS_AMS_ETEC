<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Relação de Produtos</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h1>Relação de Produtos Cadastrados</h1>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Estoque</th>
                </tr>
            </thead>
            <tbody>
                <?php
                    include_once 'Produtos.php';
                    $p = new Produto();
                    $pro_bd = $p->listar();
                    if ($pro_bd) {
                        foreach($pro_bd as $pro_mostrar) {
                            echo "<tr>";
                            echo "<td>" . htmlspecialchars($pro_mostrar['ID']) . "</td>";
                            echo "<td>" . htmlspecialchars($pro_mostrar['Nome']) . "</td>";
                            echo "<td>" . htmlspecialchars($pro_mostrar['Estoque']) . "</td>";
                            echo "</tr>";
                        }
                    } else {
                        echo "<tr><td colspan='3'>Nenhum produto cadastrado.</td></tr>";
                    }
                ?>
            </tbody>
        </table>
        <div class="actions">
            <a href="cadastrar.php" class="btn">Cadastrar Produto</a>
            <a href="excluir.php" class="btn">Excluir Produto</a>
            <a href="menu.html" class="btn-voltar">Voltar ao Menu</a>
        </div>
    </div>
</body>
</html>