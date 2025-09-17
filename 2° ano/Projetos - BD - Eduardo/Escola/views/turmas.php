<?php
include_once __DIR__ . '/../controllers/listTurmas.php';
$msg = "";
$excluir_id = $_GET['excluir_id'] ?? '';
$turma = new Turma();
if ($excluir_id) {
    $turma->setCodTurma($excluir_id);
    $msg = $turma->excluir() ? "Turma excluída com sucesso!" : "Erro ao excluir turma.";
    header("Location: turmas.php?msg=" . urlencode($msg));
    exit;
}
if (isset($_GET['msg'])) {
    $msg = $_GET['msg'];
}
$turmas = $turma->listar();
?>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Relação de Turmas</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <div class="container">
        <h1>Relação de Turmas Cadastradas</h1>
        <?php if ($msg) echo "<p class='msg'>$msg</p>"; ?>
        <table class="prod-table">
            <thead>
                <tr>
                    <th>Código</th>
                    <th>Nome</th>
                    <th>Período</th>
                    <th>Código do Curso</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <?php if ($turmas) {
                    foreach ($turmas as $tur) {
                        echo "<tr>";
                        echo "<td>" . htmlspecialchars($tur['CodTurma']) . "</td>";
                        echo "<td>" . htmlspecialchars($tur['Nome']) . "</td>";
                        echo "<td>" . htmlspecialchars($tur['Periodo']) . "</td>";
                        echo "<td>" . htmlspecialchars($tur['CodCurso']) . "</td>";
                        echo '<td><form method="get" style="display:inline;"><input type="hidden" name="excluir_id" value="'.htmlspecialchars($tur['CodTurma']).'"><button class="btn-excluir" type="submit" onclick="return confirm(\'Excluir esta turma?\');">Excluir</button></form></td>';
                        echo "</tr>";
                    }
                } else {
                    echo "<tr><td colspan='5'>Nenhuma turma cadastrada.</td></tr>";
                }
                ?>
            </tbody>
        </table>
        <div class="actions">
            <a href="menu.html" class="btn-voltar">Voltar</a>
        </div>
    </div>
</body>
</html>