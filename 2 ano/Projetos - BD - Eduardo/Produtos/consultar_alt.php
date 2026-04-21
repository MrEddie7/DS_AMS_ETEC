<?php
include_once 'Produtos.php';

// Se salvou alteração
if (isset($_POST['alterar'])) {
    $p = new Produto();
    $p->setId($_POST['id']);
    $p->setNome($_POST['nome']);
    $p->setEstoque($_POST['estoque']);

    $msg = $p->alterar2();
    echo "<div class='container'>";
    echo "<p class='menu-container'><strong style='color: green;'>$msg</strong></p>";
    echo "<a href='consultar_alt.php' class='btn-voltar'>Voltar</a>";
    echo "</div>";
    exit();
}
?>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Alterar Produto</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="container">
<?php
// Se clicou para alterar um produto específico
if (isset($_GET['id'])) {
    $p = new Produto();
    $p->setId($_GET['id']);
    $dados = $p->alterar();

    if ($dados) {
        $row = $dados[0];
        ?>
        <h2>Alterar Produto</h2>
        <form method="post" action="consultar_alt.php">
            <div class="form-group">
                <input type="hidden" name="id" value="<?php echo htmlspecialchars($row['ID']); ?>">
            </div>
            <div class="form-group">
                <label for="nome">Nome:</label>
                <input type="text" id="nome" name="nome" value="<?php echo htmlspecialchars($row['Nome']); ?>" required>
            </div>
            <div class="form-group">
                <label for="estoque">Estoque:</label>
                <input type="number" id="estoque" name="estoque" value="<?php echo htmlspecialchars($row['Estoque']); ?>" required>
            </div>
            <button type="submit" name="alterar" class="btn">Salvar Alterações</button>
        </form>
        <div class="form-actions">
            <a href="consultar_alt.php" class="btn-voltar">Voltar</a>
        </div>
        <?php
    } else {
        echo "<p>Produto não encontrado.</p>";
    }
} else {
    // Listar todos os produtos
    $p = new Produto();
    $lista = $p->listar();

    echo "<h2>Lista de Produtos</h2>";
    echo "<table>";
    echo "<tr><th>ID</th><th>Nome</th><th>Estoque</th><th>Ações</th></tr>";

    foreach ($lista as $row) {
        echo "<tr>";
        echo "<td>" . htmlspecialchars($row['ID']) . "</td>";
        echo "<td>" . htmlspecialchars($row['Nome']) . "</td>";
        echo "<td>" . htmlspecialchars($row['Estoque']) . "</td>";
        echo "<td><a href='consultar_alt.php?id=" . urlencode($row['ID']) . "' class='btn'>Alterar</a></td>";
        echo "</tr>";
    }
    echo "</table>";
}
?>
 <a href="menu.html" class="btn-voltar">Voltar ao Menu</a>
</div>
</body>
</html>
