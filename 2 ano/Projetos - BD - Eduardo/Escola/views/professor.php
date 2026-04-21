<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Relação de Cursos</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <div class="container">
        <h1>Relação de Professores Cadastrados</h1>
        <table>
            <thead>
                <tr>
                    <th>Código</th>
                    <th>Nome</th>
                    <th>Endereço</th>
                    <th>Cidade</th>
                    <th>Disciplina</th>
                    <th>Curso</th>
                    
                </tr>
            </thead>
            <tbody>

                <?php

                include_once __DIR__ . '/../controllers/listProfessor.php';
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