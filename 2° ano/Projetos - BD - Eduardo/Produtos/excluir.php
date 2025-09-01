<?php

include_once "Produtos.php";

$msg = "";
$nome = $_GET['nome'] ?? '';
$excluir_id = $_GET['excluir_id'] ?? '';

$produto = new Produto();

// Excluir produto se solicitado
if ($excluir_id) {
    $produto->setId($excluir_id);
    $msg = $produto->exclusao() ? "Produto excluído com sucesso!" : "Erro ao excluir produto.";
    // Redireciona para evitar exclusão duplicada e atualizar a lista
    header("Location: excluir.php?msg=" . urlencode($msg) . ($nome ? "&nome=" . urlencode($nome) : ""));
    exit;
}

// Mensagem pós exclusão
if (isset($_GET['msg'])) {
    $msg = $_GET['msg'];
}

// Pesquisar produtos
$tipo_listagem = $_GET['tipo_listagem'] ?? 'todos';

if ($nome) {
    $produto->setNome($nome);
    $result = $produto->consultar();
} else {
    if ($tipo_listagem == 'estoque_minimo') {
        $result = $produto->listarEstoqueMinimo(); 
    } elseif ($tipo_listagem == 'ordenado_nome') {
        $result = $produto->listarOrdenadoPorNome(); 
    } else {
        $result = $produto->listar();
    }
}
?>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Produtos - Excluir, Pesquisar e Listar</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <h1>Produtos</h1>
    <?php if ($msg) echo "<p>$msg</p>"; ?>

    <form method="get" style="margin-bottom:20px;">
        <input type="text" name="nome" placeholder="Pesquisar por nome" value="<?php echo htmlspecialchars($nome); ?>">
        <select name="tipo_listagem">
            <option value="todos" <?php if(($_GET['tipo_listagem'] ?? '') == 'todos') echo 'selected'; ?>>Todos</option>
            <option value="estoque_minimo" <?php if(($_GET['tipo_listagem'] ?? '') == 'estoque_minimo') echo 'selected'; ?>>Estoque &lt; 20</option>
            <option value="ordenado_nome" <?php if(($_GET['tipo_listagem'] ?? '') == 'ordenado_nome') echo 'selected'; ?>>Ordenado por Nome</option>
        </select>
        <button type="submit">Pesquisar/Listar</button>
    </form>

    <?php if ($result && count($result)): ?>
        <table class="prod-table">
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Estoque</th>
                <th>Ações</th>
            </tr>
            <?php foreach($result as $row): ?>
                <tr>
                    <?php foreach($row as $val): ?>
                        <td><?php echo htmlspecialchars($val); ?></td>
                    <?php endforeach; ?>
                    <td>
                        <form method="get" style="display:inline;">
                            <input type="hidden" name="excluir_id" value="<?php echo $row['ID']; ?>">
                            <?php if ($nome): ?>
                                <input type="hidden" name="nome" value="<?php echo htmlspecialchars($nome); ?>">
                            <?php endif; ?>
                            <button class="btn-excluir" type="submit" onclick="return confirm('Excluir este produto?');">Excluir</button>
                        </form>
                    </td>
                </tr>
            <?php endforeach; ?>
        </table>
    <?php else: ?>
        <p>Nenhum produto encontrado.</p>
    <?php endif; ?>
</body>
</html>