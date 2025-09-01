<?php
include_once "../models/Aluno.php"; // ajuste o caminho conforme sua estrutura

$msg = "";
$nome = $_GET['nome'] ?? '';
$excluir_id = $_GET['excluir_id'] ?? '';
$tipo_listagem = $_GET['tipo_listagem'] ?? 'todos';

$aluno = new Aluno();

// Excluir aluno se solicitado
if ($excluir_id) {
    $aluno->setMatricula($excluir_id);
    $msg = $aluno->excluir() ? "Aluno excluído com sucesso!" : "Erro ao excluir aluno.";
    header("Location: alunos.php?msg=" . urlencode($msg) . ($nome ? "&nome=" . urlencode($nome) : "") . "&tipo_listagem=" . urlencode($tipo_listagem));
    exit;
}

// Mensagem pós exclusão
if (isset($_GET['msg'])) {
    $msg = $_GET['msg'];
}

// Listagem
if ($nome) {
    $aluno->setNome($nome);
    $result = $aluno->consultarPorNome();
} else {
    if ($tipo_listagem == 'cidade_sp') {
        $result = $aluno->listarPorCidade('Sao Paulo');
    } else {
        $result = $aluno->listar();
    }
}
?>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Alunos - Excluir, Pesquisar e Listar</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <h1>Alunos</h1>
    <?php if ($msg) echo "<p class='msg'>$msg</p>"; ?>

    <form method="get" style="margin-bottom:20px;">
        <input type="text" name="nome" placeholder="Pesquisar por nome" value="<?php echo htmlspecialchars($nome); ?>">
        <select name="tipo_listagem">
            <option value="todos" <?php if($tipo_listagem == 'todos') echo 'selected'; ?>>Todos</option>
            <option value="cidade_sp" <?php if($tipo_listagem == 'cidade_sp') echo 'selected'; ?>>Somente São Paulo</option>
        </select>
        <button type="submit">Pesquisar/Listar</button>
    </form>

    <?php if ($result && count($result)): ?>
        <table class="prod-table">
            <tr>
                <th>Matrícula</th>
                <th>Nome</th>
                <th>Endereço</th>
                <th>Cidade</th>
                <th>Código do Curso</th>
                <th>Ações</th>
            </tr>
            <?php foreach($result as $row): ?>
                <tr>
                    <td><?php echo htmlspecialchars($row['Matricula']); ?></td>
                    <td><?php echo htmlspecialchars($row['Nome']); ?></td>
                    <td><?php echo htmlspecialchars($row['Endereco']); ?></td>
                    <td><?php echo htmlspecialchars($row['cidade']); ?></td>
                    <td><?php echo htmlspecialchars($row['CodCurso']); ?></td>
                    <td>
                        <form method="get" style="display:inline;">
                            <input type="hidden" name="excluir_id" value="<?php echo $row['Matricula']; ?>">
                            <?php if ($nome): ?>
                                <input type="hidden" name="nome" value="<?php echo htmlspecialchars($nome); ?>">
                            <?php endif; ?>
                            <input type="hidden" name="tipo_listagem" value="<?php echo htmlspecialchars($tipo_listagem); ?>">
                            <button class="btn-excluir" type="submit" onclick="return confirm('Excluir este aluno?');">Excluir</button>
                        </form>
                    </td>
                </tr>
            <?php endforeach; ?>
        </table>
    <?php else: ?>
        <p>Nenhum aluno encontrado.</p>
    <?php endif; ?>
</body>
</html>