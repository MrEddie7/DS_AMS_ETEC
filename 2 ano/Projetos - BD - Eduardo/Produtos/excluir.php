<?php

include_once "Produtos.php";

$msg = "";
$nome = $_GET['nome'] ?? '';
$id = $_GET['id'] ?? '';
$excluir_id = $_GET['excluir_id'] ?? '';

$produto = new Produto();

// Excluir produto se solicitado
if ($excluir_id) {
    $produto->setId($excluir_id);
    $msg = $produto->exclusao() ? "Produto excluído com sucesso!" : "Erro ao excluir produto.";
    // Redireciona para evitar exclusão duplicada e atualizar a lista
    $redirect = "excluir.php?msg=" . urlencode($msg);
    if ($nome) $redirect .= "&nome=" . urlencode($nome);
    if ($id) $redirect .= "&id=" . urlencode($id);
    header("Location: $redirect");
    exit;
}

// Mensagem pós exclusão
if (isset($_GET['msg'])) {
    $msg = $_GET['msg'];
}

// Pesquisar produtos
$tipo_listagem = $_GET['tipo_listagem'] ?? 'todos';

if ($nome || $id) {
    $result = $produto->listar();
    // Filtra por primeira palavra do nome ou por início do ID
    $result = array_filter($result, function($row) use ($nome, $id) {
        $match_nome = false;
        $match_id = false;
        if ($nome) {
            $primeira_palavra = strtok(trim($row['Nome']), " ");
            $match_nome = stripos($primeira_palavra, $nome) === 0;
        }
        if ($id) {
            $match_id = stripos((string)$row['ID'], $id) === 0;
        }
        return $match_nome || $match_id;
    });
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
    <div class="container">
        <h1>Produtos</h1>
        <?php if ($msg) echo "<p class='msg'>$msg</p>"; ?>

        <form method="get" class="form-pesquisa">
            <input type="text" name="nome" placeholder="Pesquisar por nome (primeira palavra)" value="<?php echo htmlspecialchars($nome); ?>">
            <input type="text" name="id" placeholder="Pesquisar por ID" value="<?php echo htmlspecialchars($id); ?>">
            <select name="tipo_listagem">
                <option value="todos" <?php if(($_GET['tipo_listagem'] ?? '') == 'todos') echo 'selected'; ?>>Todos</option>
                <option value="estoque_minimo" <?php if(($_GET['tipo_listagem'] ?? '') == 'estoque_minimo') echo 'selected'; ?>>Estoque &lt; 20</option>
                <option value="ordenado_nome" <?php if(($_GET['tipo_listagem'] ?? '') == 'ordenado_nome') echo 'selected'; ?>>Ordenado por Nome</option>
            </select>
            <button type="submit" class="btn">Pesquisar/Listar</button>
        </form>

        <?php if ($result && count($result)): ?>
            <table class="prod-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Estoque</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                <?php foreach($result as $row): ?>
                    <tr>
                        <td><?php echo htmlspecialchars($row['ID']); ?></td>
                        <td><?php echo htmlspecialchars($row['Nome']); ?></td>
                        <td><?php echo htmlspecialchars($row['Estoque']); ?></td>
                        <td>
                            <form method="get" style="display:inline;">
                                <input type="hidden" name="excluir_id" value="<?php echo $row['ID']; ?>">
                                <?php if ($nome): ?>
                                    <input type="hidden" name="nome" value="<?php echo htmlspecialchars($nome); ?>">
                                <?php endif; ?>
                                <?php if ($id): ?>
                                    <input type="hidden" name="id" value="<?php echo htmlspecialchars($id); ?>">
                                <?php endif; ?>
                                <button class="btn-excluir" type="submit" onclick="return confirm('Excluir este produto?');">Excluir</button>
                            </form>
                        </td>
                    </tr>
                <?php endforeach; ?>
                </tbody>
            </table>
        <?php else: ?>
            <p>Nenhum produto encontrado.</p>
        <?php endif; ?>
        <div class="actions">
            <a href="cadastrar.php" class="btn">Cadastrar Produto</a>
            <a href="Listar.php" class="btn">Listar Produtos</a>
            <a href="menu.html" class="btn-voltar">Voltar ao Menu</a>
        </div>
    </div>
</body>
</html>