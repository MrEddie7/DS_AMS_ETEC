<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Relação de Produtos</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <div class="container">
        <h1>Relação de Alunos Cadastrados</h1>
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

                include_once __DIR__ . '/../controllers/listAluno.php';
                    $p = new Aluno();
                    $pro_bd = $p->listar();
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
                        echo "<tr><td colspan='3'>Nenhum produto cadastrado.</td></tr>";
                    }


                    
            

                
                ?>
            </tbody>
        </table>
        <a href="menu.html" class="btn-voltar">Voltar</a>
    </div>
</body>
</html>