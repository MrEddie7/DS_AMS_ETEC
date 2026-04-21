<?php
include_once 'Conexao.php';

$type = $_GET['type'] ?? '';

try {
    $conn = Conectar::getInstance();
    $query = '';

    switch ($type) {
        case 'alunos':
            $query = "SELECT * FROM alunos ORDER BY Matricula";
            break;
        case 'cursos':
            $query = "SELECT * FROM cursos ORDER BY CodCurso";
            break;
        case 'disciplinas':
            $query = "SELECT * FROM disciplinas ORDER BY CodDisciplina";
            break;
        default:
            echo json_encode([]);
            exit;
    }

    $stmt = $conn->prepare($query);
    $stmt->execute();
    $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode($result);
} catch (PDOException $e) {
    echo json_encode(['error' => $e->getMessage()]);
}
?>
