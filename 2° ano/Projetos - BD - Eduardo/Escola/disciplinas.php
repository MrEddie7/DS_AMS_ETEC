<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Relação de Disciplinas</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <h1>Relação de Disciplinas Cadastradas</h1>
        <table>
            <thead>
                <tr>
                    <th>Código</th>
                    <th>Nome</th>
                </tr>
            </thead>
            <tbody>
                <?php
                    include_once 'listDisciplina.php';
                    $disciplina = new Disciplina();
                    $disciplinas = $disciplina->listar();
                    if ($disciplinas) {
                        foreach ($disciplinas as $disc) {
                            echo "<tr>";
                            echo "<td>" . htmlspecialchars($disc['CodDisciplina']) . "</td>";
                            echo "<td>" . htmlspecialchars($disc['NomeDisciplina']) . "</td>";
                            echo "</tr>";
                        }
                    } else {
                        echo "<tr><td colspan='2'>Nenhuma disciplina cadastrada.</td></tr>";
                    }
                ?>
            </tbody>
        </table>
        <a href="menu.html" class="btn-voltar">Voltar</a>
    </div>
</body>
</html>
