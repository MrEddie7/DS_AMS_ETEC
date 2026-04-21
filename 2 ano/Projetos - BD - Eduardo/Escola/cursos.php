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
                    include_once 'listCurso.php';
                    $curso = new Curso();
                    $cursos = $curso->listar();
                    if ($cursos) {
                        foreach ($cursos as $cur) {
                            echo "<tr>";
                            echo "<td>" . htmlspecialchars($cur['CodCurso']) . "</td>";
                            echo "<td>" . htmlspecialchars($cur['Nome']) . "</td>";
                            echo "<td>" . htmlspecialchars($cur['coddisc1']) . "</td>";
                            echo "<td>" . htmlspecialchars($cur['coddisc2']) . "</td>";
                            echo "<td>" . htmlspecialchars($cur['coddisc3']) . "</td>";
                            echo "</tr>";
                        }
                    } else {
                        echo "<tr><td colspan='5'>Nenhum curso cadastrado.</td></tr>";
                    }
                ?>
            </tbody>
        </table>
        <a href="menu.html" class="btn-voltar">Voltar</a>
    </div>
</body>
</html>
