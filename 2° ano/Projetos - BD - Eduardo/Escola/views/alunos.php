<?php
include_once __DIR__ . '/../controllers/listAluno.php';
$p = new Aluno();
$pro_bd = [];

if ($_SERVER['REQUEST_METHOD'] === 'POST' && !empty($_POST['pesquisa_nome'])) {
    $p->setNome($_POST['pesquisa_nome']);
    $pro_bd = $p->buscarPorNome();
} else {
    $pro_bd = $p->listar();
}
?>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Relação de Alunos</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <div class="container">
        <h1>Relação de Alunos Cadastrados</h1>
        <form method="post" style="margin-bottom:20px;">
            <input type="text" name="pesquisa_nome" placeholder="Pesquisar por nome">
            <button type="submit">Pesquisar</button>
        </form>
        <table>
            <thead>
                <tr>
                    <th>Matricula</th>
                    <th>Nome</th>
                    <th>Endereço</th>
                    <th>Cidade</th>
                    <th>Curso</th>
                </tr>
            </thead>
            <tbody>
                <?php
                    if ($pro_bd) {
                        foreach($pro_bd as $pro_mostrar) {
                            echo "<tr>";
                            echo "<td>" . htmlspecialchars($pro_mostrar['Matricula']) . "</td>";
                            echo "<td>" . htmlspecialchars($pro_mostrar['Nome']) . "</td>";
                            echo "<td>" . htmlspecialchars($pro_mostrar['Endereco']) . "</td>";
                            echo "<td>" . htmlspecialchars($pro_mostrar['cidade']) . "</td>";
                            echo "<td>" . htmlspecialchars($pro_mostrar['CodCurso']) . "</td>";
                            echo "</tr>";
                        }
                    } else {
                        echo "<tr><td colspan='5'>Nenhum aluno encontrado.</td></tr>";
                    }
                ?>
            </tbody>
        </table>
        <a href="menu.html" class="btn-voltar">Voltar</a>
    </div>
</body>
</html>