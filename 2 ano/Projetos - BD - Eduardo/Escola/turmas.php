<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Relação de Turmas</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <h1>Relação de Turmas Cadastradas</h1>
        <table>
            <thead>
                <tr>
                    <th>Código</th>
                    <th>Nome</th>
                    <th>Período</th>
                    <th>Código do Curso</th>
                </tr>
            </thead>
            <tbody>

                <?php
                    include_once 'listTurma.php';
                    $turma = new Turma();
                    $turmas = $turma->listar();
                    if ($turmas) {
                        foreach ($turmas as $tur) {
                            echo "<tr>";
                            echo "<td>" . htmlspecialchars($tur['CodTurma']) . "</td>";
                            echo "<td>" . htmlspecialchars($tur['Nome']) . "</td>";
                            echo "<td>" . htmlspecialchars($tur['Periodo']) . "</td>";
                            echo "<td>" . htmlspecialchars($tur['CodCurso']) . "</td>";
                            echo "</tr>";
                        }
                    } else {
                        echo "<tr><td colspan='4'>Nenhuma turma cadastrada.</td></tr>";
                    }
                ?>
            </tbody>
        </table>
        <a href="menu.html" class="btn-voltar">Voltar</a>
    </div>
</body>
</html>