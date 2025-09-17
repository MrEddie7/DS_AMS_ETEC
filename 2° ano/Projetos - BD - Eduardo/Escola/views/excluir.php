<?php
include_once __DIR__ . '/../controllers/listTurmas.php';
include_once __DIR__ . '/../controllers/listCurso.php';
include_once __DIR__ . '/../controllers/listdisciplina.php';
include_once __DIR__ . '/../controllers/listProfessor.php';
$msg = "";
$tipo = $_GET['tipo'] ?? 'turmas';
$excluir_id = $_GET['excluir_id'] ?? '';

switch ($tipo) {
    case 'alunos':
        include_once __DIR__ . '/../controllers/listAluno.php';

        $obj = new Aluno();
        $idField = 'Matricula';
        $list = $obj->listar();
        if ($excluir_id) {
            $obj->setMatricula($excluir_id);
            $msg = $obj->excluir() ? "Aluno excluído com sucesso!" : "Erro ao excluir aluno.";
            header("Location: excluir.php?tipo=alunos&msg=" . urlencode($msg));
            exit;
        }
        $cols = ['Matricula', 'Nome', 'Endereco', 'cidade', 'CodCurso'];
        $title = 'Excluir Alunos';
        break;
    case 'cursos':
        $obj = new Curso();
        $idField = 'CodCurso';
        $list = $obj->listar();
        if ($excluir_id) {
            $obj->setCodCurso($excluir_id);
            $msg = $obj->excluir() ? "Curso excluído com sucesso!" : "Erro ao excluir curso.";
            header("Location: excluir.php?tipo=cursos&msg=" . urlencode($msg));
            exit;
        }
        $cols = ['CodCurso', 'Nome', 'coddisc1', 'coddisc2', 'coddisc3'];
        $title = 'Excluir Cursos';
        break;
    case 'disciplinas':
        $obj = new Disciplina();
        $idField = 'CodDisciplina';
        $list = $obj->listar();
        if ($excluir_id) {
            $obj->setCodDisciplina($excluir_id);
            $msg = $obj->excluir() ? "Disciplina excluída com sucesso!" : "Erro ao excluir disciplina.";
            header("Location: excluir.php?tipo=disciplinas&msg=" . urlencode($msg));
            exit;
        }
        $cols = ['CodDisciplina', 'NomeDisciplina'];
        $title = 'Excluir Disciplinas';
        break;
    case 'professores':
        $obj = new Professor();
        $idField = 'CodProfessor';
        $list = $obj->listar();
        if ($excluir_id) {
            $obj->setCodProfessor($excluir_id);
            $msg = $obj->excluir() ? "Professor excluído com sucesso!" : "Erro ao excluir professor.";
            header("Location: excluir.php?tipo=professores&msg=" . urlencode($msg));
            exit;
        }
        $cols = ['CodProfessor', 'Nome', 'Endereco', 'Cidade', 'CodDisciplina', 'CodCurso'];
        $title = 'Excluir Professores';
        break;
    default:
        $obj = new Turma();
        $idField = 'CodTurma';
        $list = $obj->listar();
        if ($excluir_id) {
            $obj->setCodTurma($excluir_id);
            $msg = $obj->excluir() ? "Turma excluída com sucesso!" : "Erro ao excluir turma.";
            header("Location: excluir.php?tipo=turmas&msg=" . urlencode($msg));
            exit;
        }
        $cols = ['CodTurma', 'Nome', 'Periodo', 'CodCurso'];
        $title = 'Excluir Turmas';
        break;
}
if (isset($_GET['msg'])) {
    $msg = $_GET['msg'];
}
$pesquisa_nome = $_GET['pesquisa_nome'] ?? '';
$pesquisa_id = $_GET['pesquisa_id'] ?? '';
$campo_nome = '';
switch ($tipo) {
    case 'alunos':
        $campo_nome = 'Nome';
        break;
    case 'cursos':
        $campo_nome = 'Nome';
        break;
    case 'disciplinas':
        $campo_nome = 'NomeDisciplina';
        break;
    case 'professores':
        $campo_nome = 'Nome';
        break;
    case 'turmas':
    default:
        $campo_nome = 'Nome';
        break;
}
// Filtragem por nome (primeira palavra) ou por ID
if (($pesquisa_nome && $campo_nome) || $pesquisa_id) {
    $list = array_filter($list, function($item) use ($pesquisa_nome, $campo_nome, $pesquisa_id, $idField) {
        $match_nome = false;
        $match_id = false;
        if ($pesquisa_nome && $campo_nome) {
            $primeira_palavra = strtok(trim($item[$campo_nome]), " ");
            $match_nome = stripos($primeira_palavra, $pesquisa_nome) === 0;
        }
        if ($pesquisa_id) {
            $match_id = stripos((string)$item[$idField], $pesquisa_id) === 0;
        }
        // Se ambos preenchidos, retorna se qualquer um bater
        return $match_nome || $match_id;
    });
}
?>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title><?php echo $title; ?></title>
    <link rel="stylesheet" href="../css/style.css">
</head>
        if ($pesquisa_id) {
            $match_id = stripos((string)$item[$idField], $pesquisa_id) === 0;
        }
        return $match_nome || $match_id;
    });
}
?>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title><?php echo $title; ?></title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <div class="container">
        <h1><?php echo $title; ?></h1>
        <form method="get" style="margin-bottom:20px;">
            <label for="tipo">Escolha o tipo:</label>
            <select name="tipo" id="tipo" onchange="this.form.submit()">
                <option value="turmas" <?php if($tipo=='turmas') echo 'selected'; ?>>Turmas</option>
                <option value="alunos" <?php if($tipo=='alunos') echo 'selected'; ?>>Alunos</option>
                <option value="cursos" <?php if($tipo=='cursos') echo 'selected'; ?>>Cursos</option>
                <option value="disciplinas" <?php if($tipo=='disciplinas') echo 'selected'; ?>>Disciplinas</option>
                <option value="professores" <?php if($tipo=='professores') echo 'selected'; ?>>Professores</option>
            </select>
        </form>
        <!-- Formulário de pesquisa por nome -->
        <form method="get" class="form-pesquisa" style="margin-bottom:20px;">
            <input type="hidden" name="tipo" value="<?php echo htmlspecialchars($tipo); ?>">
            <label for="pesquisa_nome">
                Pesquisar por Nome<?php echo $tipo == 'disciplinas' ? ' da Disciplina' : ($tipo == 'cursos' ? ' do Curso' : ($tipo == 'professores' ? ' do Professor' : ($tipo == 'turmas' ? ' da Turma' : ' do Aluno'))); ?>:
            </label>
            <input type="text" id="pesquisa_nome" name="pesquisa_nome" value="<?php echo htmlspecialchars($pesquisa_nome); ?>" placeholder="Digite o nome">
            <label for="pesquisa_id" style="margin-left:16px;">
                Pesquisar por ID:
            </label>
            <input type="text" id="pesquisa_id" name="pesquisa_id" value="<?php echo htmlspecialchars($pesquisa_id); ?>" placeholder="Digite o ID">
            <button type="submit" name="acao" value="pesquisar">Pesquisar</button>
        </form>
        <?php if ($msg) echo "<p class='msg'>$msg</p>"; ?>
        <?php if ($pesquisa_nome || $pesquisa_id): ?>
            <p>
                Resultados da pesquisa
                <?php if ($pesquisa_nome): ?> por nome: <strong><?php echo htmlspecialchars($pesquisa_nome); ?></strong><?php endif; ?>
                <?php if ($pesquisa_id): ?> por ID: <strong><?php echo htmlspecialchars($pesquisa_id); ?></strong><?php endif; ?>
            </p>
        <?php endif; ?>
        <table class="prod-table">
            <thead>
                <tr>
                    <?php foreach($cols as $col) echo "<th>".htmlspecialchars($col)."</th>"; ?>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <?php if ($list) {
                    foreach ($list as $item) {
                        echo "<tr>";
                        foreach($cols as $col) {
                            echo "<td>" . htmlspecialchars($item[$col]) . "</td>";
                        }
                        echo '<td><form method="get" style="display:inline;"><input type="hidden" name="tipo" value="'.htmlspecialchars($tipo).'">';
                        echo '<input type="hidden" name="excluir_id" value="'.htmlspecialchars($item[$idField]).'">';
                        echo '<button class="btn-excluir" type="submit" onclick="return confirm(\'Excluir este item?\');">Excluir</button></form></td>';
                        echo "</tr>";
                    }
                } else {
                    echo "<tr><td colspan='".(count($cols)+1)."'>Nenhum item encontrado.</td></tr>";
                }
                ?>
            </tbody>
        </table>
        <div class="actions">
            <a href="menu.html" class="btn-voltar">Voltar ao Menu</a>
        </div>
    </div>
</body>
</html>
