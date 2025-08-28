<?php

include_once __DIR__ . '/../models/Conexao.php';

$conn = Conectar::getInstance();

function cadastrar($tipo, $dados) {
    global $conn;
    try {
        switch ($tipo) {
            case 'curso':
                $sql = "INSERT INTO cursos (CodCurso, Nome, coddisc1, coddisc2, coddisc3) VALUES (:CodCurso, :Nome, :coddisc1, :coddisc2, :coddisc3)";
                $stmt = $conn->prepare($sql);
                $stmt->bindParam(':CodCurso', $dados['CodCurso']);
                $stmt->bindParam(':Nome', $dados['Nome']);
                $stmt->bindParam(':coddisc1', $dados['coddisc1']);
                $stmt->bindParam(':coddisc2', $dados['coddisc2']);
                $stmt->bindParam(':coddisc3', $dados['coddisc3']);
                break;
            case 'professor':
                $sql = "INSERT INTO professores (CodProfessor, Nome, Endereco, Cidade, CodDisciplina, CodCurso) VALUES (:CodProfessor, :Nome, :Endereco, :Cidade, :CodDisciplina, :CodCurso)";
                $stmt = $conn->prepare($sql);
                $stmt->bindParam(':CodProfessor', $dados['CodProfessor']);
                $stmt->bindParam(':Nome', $dados['Nome']);
                $stmt->bindParam(':Endereco', $dados['Endereco']);
                $stmt->bindParam(':Cidade', $dados['Cidade']);
                $stmt->bindParam(':CodDisciplina', $dados['CodDisciplina']);
                $stmt->bindParam(':CodCurso', $dados['CodCurso']);
                break;
            case 'turma':
                $sql = "INSERT INTO turmas (CodTurma, CodDisciplina, CodCurso, CodProfessor, Ano, Semestre) VALUES (:CodTurma, :CodDisciplina, :CodCurso, :CodProfessor, :Ano, :Semestre)";
                $stmt = $conn->prepare($sql);
                $stmt->bindParam(':CodTurma', $dados['CodTurma']);
                $stmt->bindParam(':CodDisciplina', $dados['CodDisciplina']);
                $stmt->bindParam(':CodCurso', $dados['CodCurso']);
                $stmt->bindParam(':CodProfessor', $dados['CodProfessor']);
                $stmt->bindParam(':Ano', $dados['Ano']);
                $stmt->bindParam(':Semestre', $dados['Semestre']);
                break;
            case 'disciplina':
                $sql = "INSERT INTO disciplinas (CodDisciplina, NomeDisciplina) VALUES (:CodDisciplina, :NomeDisciplina)";
                $stmt = $conn->prepare($sql);
                $stmt->bindParam(':CodDisciplina', $dados['CodDisciplina']);
                $stmt->bindParam(':NomeDisciplina', $dados['NomeDisciplina']);
                break;
            case 'aluno':
                $sql = "INSERT INTO alunos (Matricula, Nome, Endereco, cidade, CodCurso) VALUES (:Matricula, :Nome, :Endereco, :cidade, :CodCurso)";
                $stmt = $conn->prepare($sql);
                $stmt->bindParam(':Matricula', $dados['Matricula']);
                $stmt->bindParam(':Nome', $dados['Nome']);
                $stmt->bindParam(':Endereco', $dados['Endereco']);
                $stmt->bindParam(':cidade', $dados['cidade']);
                $stmt->bindParam(':CodCurso', $dados['CodCurso']);
                break;
            default:
                return "Tipo inválido!";
        }
        if ($stmt->execute()) {
            return "Cadastro realizado com sucesso!";
        } else {
            return "Erro ao cadastrar: " . implode(", ", $stmt->errorInfo());
        }
    } catch (PDOException $e) {
        return "Erro ao cadastrar: " . $e->getMessage();
    }
}


$msg = "";
$tipoSelecionado = "";
$dadosPreenchidos = [];
if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['tipo'])) {
    $tipoSelecionado = $_POST['tipo'];
    $dadosPreenchidos = $_POST;
    $msg = cadastrar($_POST['tipo'], $_POST);
}
?>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Cadastrar</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <div class="menu-container">
        <h1 style="color:#007BFF">Cadastro</h1>
        <?php if ($msg) echo "<p style='color: ".(strpos($msg,'sucesso')!==false?'#007BFF':'#d32f2f').";font-weight:bold; margin-bottom:18px;'>$msg</p>"; ?>
        <form method="post" autocomplete="off">
            <div class="form-group">
                <label for="tipo">Tipo de Cadastro</label>
                <select name="tipo" id="tipo" onchange="showFields(this.value)" required>
                    <option value="">Selecione</option>
                    <option value="curso" <?php if($tipoSelecionado=="curso") echo "selected"; ?>>Curso</option>
                    <option value="professor" <?php if($tipoSelecionado=="professor") echo "selected"; ?>>Professor</option>
                    <option value="turma" <?php if($tipoSelecionado=="turma") echo "selected"; ?>>Turma</option>
                    <option value="disciplina" <?php if($tipoSelecionado=="disciplina") echo "selected"; ?>>Disciplina</option>
                    <option value="aluno" <?php if($tipoSelecionado=="aluno") echo "selected"; ?>>Aluno</option>
                </select>
            </div>
            <div id="fields-container"></div>
            <button type="submit" style="background-color:#007BFF;color:#fff;">Cadastrar</button>
        </form>
        <a href="../views/menu.html" class="btn-voltar">Voltar ao Menu</a>
    </div>

    
    <script>
        const dados = <?php echo json_encode($dadosPreenchidos); ?>;
        function showFields(tipo) {
            const container = document.getElementById('fields-container');
            let html = '';
            if (tipo === 'curso') {
                html = `
                    <div class='form-group'><label for='CodCurso'>Código do Curso</label><input type='text' name='CodCurso' id='CodCurso' required value='${dados.CodCurso||''}'></div>
                    <div class='form-group'><label for='Nome'>Nome do Curso</label><input type='text' name='Nome' id='Nome' required value='${dados.Nome||''}'></div>
                    <div class='form-group'><label for='coddisc1'>Disciplina 1</label><input type='text' name='coddisc1' id='coddisc1' required value='${dados.coddisc1||''}'></div>
                    <div class='form-group'><label for='coddisc2'>Disciplina 2</label><input type='text' name='coddisc2' id='coddisc2' required value='${dados.coddisc2||''}'></div>
                    <div class='form-group'><label for='coddisc3'>Disciplina 3</label><input type='text' name='coddisc3' id='coddisc3' required value='${dados.coddisc3||''}'></div>
                `;
            } else if (tipo === 'professor') {
                html = `
                    <div class='form-group'><label for='CodProfessor'>Código do Professor</label><input type='text' name='CodProfessor' id='CodProfessor' required value='${dados.CodProfessor||''}'></div>
                    <div class='form-group'><label for='Nome'>Nome</label><input type='text' name='Nome' id='Nome' required value='${dados.Nome||''}'></div>
                    <div class='form-group'><label for='Endereco'>Endereço</label><input type='text' name='Endereco' id='Endereco' required value='${dados.Endereco||''}'></div>
                    <div class='form-group'><label for='Cidade'>Cidade</label><input type='text' name='Cidade' id='Cidade' required value='${dados.Cidade||''}'></div>
                    <div class='form-group'><label for='CodDisciplina'>Código da Disciplina</label><input type='text' name='CodDisciplina' id='CodDisciplina' required value='${dados.CodDisciplina||''}'></div>
                    <div class='form-group'><label for='CodCurso'>Código do Curso</label><input type='text' name='CodCurso' id='CodCurso' required value='${dados.CodCurso||''}'></div>
                `;
            } else if (tipo === 'turma') {
                html = `
                    <div class='form-group'><label for='CodTurma'>Código da Turma</label><input type='text' name='CodTurma' id='CodTurma' required value='${dados.CodTurma||''}'></div>
                    <div class='form-group'><label for='CodDisciplina'>Código da Disciplina</label><input type='text' name='CodDisciplina' id='CodDisciplina' required value='${dados.CodDisciplina||''}'></div>
                    <div class='form-group'><label for='CodCurso'>Código do Curso</label><input type='text' name='CodCurso' id='CodCurso' required value='${dados.CodCurso||''}'></div>
                    <div class='form-group'><label for='CodProfessor'>Código do Professor</label><input type='text' name='CodProfessor' id='CodProfessor' required value='${dados.CodProfessor||''}'></div>
                    <div class='form-group'><label for='Ano'>Ano</label><input type='number' name='Ano' id='Ano' required value='${dados.Ano||''}'></div>
                    <div class='form-group'><label for='Semestre'>Semestre</label><input type='number' name='Semestre' id='Semestre' required value='${dados.Semestre||''}'></div>
                `;
            } else if (tipo === 'disciplina') {
                html = `
                    <div class='form-group'><label for='CodDisciplina'>Código da Disciplina</label><input type='text' name='CodDisciplina' id='CodDisciplina' required value='${dados.CodDisciplina||''}'></div>
                    <div class='form-group'><label for='NomeDisciplina'>Nome da Disciplina</label><input type='text' name='NomeDisciplina' id='NomeDisciplina' required value='${dados.NomeDisciplina||''}'></div>
                `;
            } else if (tipo === 'aluno') {
                html = `
                    <div class='form-group'><label for='Matricula'>Matrícula</label><input type='text' name='Matricula' id='Matricula' required value='${dados.Matricula||''}'></div>
                    <div class='form-group'><label for='Nome'>Nome</label><input type='text' name='Nome' id='Nome' required value='${dados.Nome||''}'></div>
                    <div class='form-group'><label for='Endereco'>Endereço</label><input type='text' name='Endereco' id='Endereco' required value='${dados.Endereco||''}'></div>
                    <div class='form-group'><label for='cidade'>Cidade</label><input type='text' name='cidade' id='cidade' required value='${dados.cidade||''}'></div>
                    <div class='form-group'><label for='CodCurso'>Código do Curso</label><input type='text' name='CodCurso' id='CodCurso' required value='${dados.CodCurso||''}'></div>
                `;
            }
            container.innerHTML = html;
        }
        document.addEventListener('DOMContentLoaded', function() {
            const tipo = document.getElementById('tipo').value;
            if(tipo) showFields(tipo);
            // Limpa campos se cadastro foi realizado com sucesso
            <?php if ($msg && strpos($msg,'sucesso')!==false): ?>
                document.getElementById('tipo').selectedIndex = 0;
                document.getElementById('fields-container').innerHTML = '';
            <?php endif; ?>
        });
    </script>


</body>
</html>