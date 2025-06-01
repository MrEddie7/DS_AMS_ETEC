<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Relação de Cursos</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <h1>Relação de Cursos Cadastrados</h1>
        <table>
            <thead>
                <tr>
                    <th>Código</th>
                    <th>Nome</th>
                    <th>Disciplina 1</th>
                    <th>Disciplina 2</th>
                    <th>Disciplina 3</th>
                </tr>
            </thead>
            <tbody>

                <?php
                    include_once 'listProfessor.php';
                    $professor = new Professor();
                    $professores = $professor->listar();
                    if ($professores) {
                        foreach ($professores as $prof) {
                            echo "<tr>";
                            echo "<td>" . htmlspecialchars($prof['CodProfessor']) . "</td>";
                            echo "<td>" . htmlspecialchars($prof['Nome']) . "</td>";
                            echo "<td>" . htmlspecialchars($prof['Endereco']) . "</td>";
                            echo "<td>" . htmlspecialchars($prof['Cidade']) . "</td>";
                            echo "<td>" . htmlspecialchars($prof['CodDisciplina']) . "</td>";
                            echo "<td>" . htmlspecialchars($prof['CodCurso']) . "</td>";
                            echo "</tr>";
                        }
                    } else {
                        echo "<tr><td colspan='5'>Nenhum professor cadastrado.</td></tr>";
                    }
                ?>
            </tbody>
        </table>
        <a href="menu.html" class="btn-voltar">Voltar</a>
    </div>
</body>
</html>