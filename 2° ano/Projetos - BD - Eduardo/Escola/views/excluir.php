<?php
include_once __DIR__ . '/../controllers/listTurmas.php';
include_once __DIR__ . '/../controllers/listCurso.php';
include_once __DIR__ . '/../controllers/listdisciplina.php';
include_once __DIR__ . '/../controllers/listProfessor.php';
$msg = "";
$tipo = $_GET['tipo'] ?? 'turmas';
$excluir_id = $_GET['excluir_id'] ?? '';
$editar_id = $_GET['editar_id'] ?? '';

// Processar edição se estiver no modo de edição
if ($editar_id && $_SERVER['REQUEST_METHOD'] === 'POST') {
    switch ($tipo) {
        case 'alunos':
            include_once __DIR__ . '/../controllers/listAluno.php';
            $obj = new Aluno();
            $obj->setMatricula($_POST['Matricula']);
            $obj->setNome($_POST['Nome']);
            $obj->setEndereco($_POST['Endereco']);
            $obj->setCidade($_POST['cidade']);
            $obj->setCodCurso($_POST['CodCurso']);
            $msg = $obj->editar() ? "Aluno editado com sucesso!" : "Erro ao editar aluno.";
            break;
            
        case 'cursos':
            $obj = new Curso();
            $obj->setCodCurso($_POST['CodCurso']);
            $obj->setNome($_POST['Nome']);
            $obj->setCodDisc1($_POST['coddisc1']);
            $obj->setCodDisc2($_POST['coddisc2']);
            $obj->setCodDisc3($_POST['coddisc3']);
            $msg = $obj->editar() ? "Curso editado com sucesso!" : "Erro ao editar curso.";
            break;
            
        case 'disciplinas':
            $obj = new Disciplina();
            $obj->setCodDisciplina($_POST['CodDisciplina']);
            $obj->setNomeDisciplina($_POST['NomeDisciplina']);
            $msg = $obj->editar() ? "Disciplina editada com sucesso!" : "Erro ao editar disciplina.";
            break;
            
        case 'professores':
            $obj = new Professor();
            $obj->setCodProfessor($_POST['CodProfessor']);
            $obj->setNome($_POST['Nome']);
            $obj->setEndereco($_POST['Endereco']);
            $obj->setCidade($_POST['Cidade']);
            $obj->setCodDisciplina($_POST['CodDisciplina']);
            $obj->setCodCurso($_POST['CodCurso']);
            $msg = $obj->editar() ? "Professor editado com sucesso!" : "Erro ao editar professor.";
            break;
            
        case 'turmas':
            $obj = new Turma();
            $obj->setCodTurma($_POST['CodTurma']);
            $obj->setNome($_POST['Nome']);
            $obj->setPeriodo($_POST['Periodo']);
            $obj->setCodCurso($_POST['CodCurso']);
            $msg = $obj->editar() ? "Turma editada com sucesso!" : "Erro ao editar turma.";
            break;
    }
    
    header("Location: excluir.php?tipo=$tipo&msg=" . urlencode($msg));
    exit;
}

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
        $title = 'Gerenciar Alunos';
        break;
    case 'cursos':
        include_once __DIR__ . '/../controllers/listCurso.php';
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
        $title = 'Gerenciar Cursos';
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
        $title = 'Gerenciar Disciplinas';
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
        $title = 'Gerenciar Professores';
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
        $title = 'Gerenciar Turmas';
        break;
}

// Buscar dados para edição se estiver no modo de edição
$dados_edicao = [];
if ($editar_id) {
    switch ($tipo) {
        case 'alunos':
            include_once __DIR__ . '/../controllers/listAluno.php';
            $obj_edicao = new Aluno();
            $obj_edicao->setMatricula($editar_id);
            $dados_edicao = $obj_edicao->buscarPorMatricula();
            break;
        case 'cursos':
            include_once __DIR__ . '/../controllers/listCurso.php';
            $obj_edicao = new Curso();
            $dados_edicao = $obj_edicao->buscarPorCodigo($editar_id);
            break;
        case 'disciplinas':
            $obj_edicao = new Disciplina();
            $obj_edicao->setCodDisciplina($editar_id);
            $dados_edicao = $obj_edicao->buscarPorCodigo();
            break;
        case 'professores':
            $obj_edicao = new Professor();
            $obj_edicao->setCodProfessor($editar_id);
            $dados_edicao = $obj_edicao->buscarPorCodigo();
            break;
        case 'turmas':
            $obj_edicao = new Turma();
            $obj_edicao->setCodTurma($editar_id);
            $dados_edicao = $obj_edicao->buscarPorCodigo();
            break;
    }
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

    </style>
</head>
<body>
    <div class="container">
        <h1><?php echo $title; ?></h1>
        
        <?php if ($msg): ?>
            <p class="msg" style="padding: 10px; background: <?php echo strpos($msg, 'sucesso') !== false ? '#d4edda' : '#f8d7da'; ?>; color: <?php echo strpos($msg, 'sucesso') !== false ? '#155724' : '#721c24'; ?>; border-radius: 4px; margin-bottom: 20px;">
                <?php echo htmlspecialchars($msg); ?>
            </p>
        <?php endif; ?>

        <!-- Formulário de Edição -->
        <?php if ($editar_id && $dados_edicao): ?>
        <div class="form-edicao">
            <h3>Editando <?php echo ucfirst($tipo); ?></h3>
            <form method="post">
                <input type="hidden" name="tipo" value="<?php echo htmlspecialchars($tipo); ?>">
                <input type="hidden" name="editar_id" value="<?php echo htmlspecialchars($editar_id); ?>">
                <?php switch ($tipo):
                    case 'cursos': ?>
                        <div class="form-group">
                            <label for="CodCurso">Código do Curso</label>
                            <input type="text" name="CodCurso" value="<?php echo htmlspecialchars($dados_edicao['CodCurso']); ?>" readonly>
                        </div>
                        <div class="form-group">
                            <label for="Nome">Nome</label>
                            <input type="text" name="Nome" value="<?php echo htmlspecialchars($dados_edicao['Nome']); ?>" required>
                        </div>
                        <div class="form-group">
                            <label for="coddisc1">Disciplina 1</label>
                            <input type="text" name="coddisc1" value="<?php echo htmlspecialchars($dados_edicao['coddisc1']); ?>" required>
                        </div>
                        <div class="form-group">
                            <label for="coddisc2">Disciplina 2</label>
                            <input type="text" name="coddisc2" value="<?php echo htmlspecialchars($dados_edicao['coddisc2']); ?>" required>
                        </div>
                        <div class="form-group">
                            <label for="coddisc3">Disciplina 3</label>
                            <input type="text" name="coddisc3" value="<?php echo htmlspecialchars($dados_edicao['coddisc3']); ?>" required>
                        </div>
                        <?php break;
                    case 'alunos': ?>
                        <div class="form-group">
                            <label for="Matricula">Matrícula</label>
                            <input type="text" name="Matricula" value="<?php echo htmlspecialchars($dados_edicao['Matricula']); ?>" readonly>
                        </div>
                        <div class="form-group">
                            <label for="Nome">Nome</label>
                            <input type="text" name="Nome" value="<?php echo htmlspecialchars($dados_edicao['Nome']); ?>" required>
                        </div>
                        <div class="form-group">
                            <label for="Endereco">Endereço</label>
                            <input type="text" name="Endereco" value="<?php echo htmlspecialchars($dados_edicao['Endereco']); ?>" required>
                        </div>
                        <div class="form-group">
                            <label for="cidade">Cidade</label>
                            <input type="text" name="cidade" value="<?php echo htmlspecialchars($dados_edicao['cidade']); ?>" required>
                        </div>
                        <div class="form-group">
                            <label for="CodCurso">Código do Curso</label>
                            <input type="text" name="CodCurso" value="<?php echo htmlspecialchars($dados_edicao['CodCurso']); ?>" required>
                        </div>
                        <?php break;
                    case 'disciplinas': ?>
                        <div class="form-group">
                            <label for="CodDisciplina">Código da Disciplina</label>
                            <input type="text" name="CodDisciplina" value="<?php echo htmlspecialchars($dados_edicao['CodDisciplina']); ?>" readonly>
                        </div>
                        <div class="form-group">
                            <label for="NomeDisciplina">Nome da Disciplina</label>
                            <input type="text" name="NomeDisciplina" value="<?php echo htmlspecialchars($dados_edicao['NomeDisciplina']); ?>" required>
                        </div>
                        <?php break;
                    case 'professores': ?>
                        <div class="form-group">
                            <label for="CodProfessor">Código do Professor</label>
                            <input type="text" name="CodProfessor" value="<?php echo htmlspecialchars($dados_edicao['CodProfessor']); ?>" readonly>
                        </div>
                        <div class="form-group">
                            <label for="Nome">Nome</label>
                            <input type="text" name="Nome" value="<?php echo htmlspecialchars($dados_edicao['Nome']); ?>" required>
                        </div>
                        <div class="form-group">
                            <label for="Endereco">Endereço</label>
                            <input type="text" name="Endereco" value="<?php echo htmlspecialchars($dados_edicao['Endereco']); ?>" required>
                        </div>
                        <div class="form-group">
                            <label for="Cidade">Cidade</label>
                            <input type="text" name="Cidade" value="<?php echo htmlspecialchars($dados_edicao['Cidade']); ?>" required>
                        </div>
                        <div class="form-group">
                            <label for="CodDisciplina">Código da Disciplina</label>
                            <input type="text" name="CodDisciplina" value="<?php echo htmlspecialchars($dados_edicao['CodDisciplina']); ?>" required>
                        </div>
                        <div class="form-group">
                            <label for="CodCurso">Código do Curso</label>
                            <input type="text" name="CodCurso" value="<?php echo htmlspecialchars($dados_edicao['CodCurso']); ?>" required>
                        </div>
                        <?php break;
                    case 'turmas': ?>
                        <div class="form-group">
                            <label for="CodTurma">Código da Turma</label>
                            <input type="text" name="CodTurma" value="<?php echo htmlspecialchars($dados_edicao['CodTurma']); ?>" readonly>
                        </div>
                        <div class="form-group">
                            <label for="Nome">Nome</label>
                            <input type="text" name="Nome" value="<?php echo htmlspecialchars($dados_edicao['Nome']); ?>" required>
                        </div>
                        <div class="form-group">
                            <label for="Periodo">Período</label>
                            <input type="text" name="Periodo" value="<?php echo htmlspecialchars($dados_edicao['Periodo']); ?>" required>
                        </div>
                        <div class="form-group">
                            <label for="CodCurso">Código do Curso</label>
                            <input type="text" name="CodCurso" value="<?php echo htmlspecialchars($dados_edicao['CodCurso']); ?>" required>
                        </div>
                        <?php break;
                endswitch; ?>
                <div class="form-group">
                    <button type="submit" class="btn">Salvar Alterações</button>
                    <a href="excluir.php?tipo=<?php echo htmlspecialchars($tipo); ?>" class="btn-voltar">Cancelar</a>
                </div>
            </form>
        </div>
        <?php endif; ?>

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

        <!-- Formulário de pesquisa -->
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
                        echo '<td class="acoes">';
                        echo '<a href="excluir.php?tipo=' . htmlspecialchars($tipo) . '&editar_id=' . htmlspecialchars($item[$idField]) . '" class="btn-editar">Editar</a>';
                        echo '<form method="get" style="display:inline;">';
                        echo '<input type="hidden" name="tipo" value="'.htmlspecialchars($tipo).'">';
                        echo '<input type="hidden" name="excluir_id" value="'.htmlspecialchars($item[$idField]).'">';
                        echo '<button class="btn-excluir" type="submit" onclick="return confirm(\'Excluir este item?\');">Excluir</button>';
                        echo '</form>';
                        echo '</td>';
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