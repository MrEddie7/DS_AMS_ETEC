<?php

include_once __DIR__ . '/../models/Conexao.php';

class Turma {
    private string $CodTurma;
    private string $Nome;
    private string $Periodo;
    private string $CodCurso;
    private ?PDO $conn = null;

    // Getters e Setters
    public function getCodTurma(): string {
        return $this->CodTurma;
    }
    public function setCodTurma(string $codTurma): void {
        $this->CodTurma = $codTurma;
    }
    public function getNome(): string {
        return $this->Nome;
    }
    public function setNome(string $nome): void {
        $this->Nome = $nome;
    }
    public function getPeriodo(): string {
        return $this->Periodo;
    }
    public function setPeriodo(string $periodo): void {
        $this->Periodo = $periodo;
    }
    public function getCodCurso(): string {
        return $this->CodCurso;
    }
    public function setCodCurso(string $codCurso): void {
        $this->CodCurso = $codCurso;
    }

    // Método para cadastrar turma
    public function cadastrar() {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = "INSERT INTO turmas (CodTurma, Nome, Periodo, CodCurso) VALUES (:CodTurma, :Nome, :Periodo, :CodCurso)";
            $stmt = $this->conn->prepare($sql);
            $stmt->bindValue(':CodTurma', $this->getCodTurma());
            $stmt->bindValue(':Nome', $this->getNome());
            $stmt->bindValue(':Periodo', $this->getPeriodo());
            $stmt->bindValue(':CodCurso', $this->getCodCurso());
            return $stmt->execute();
        } catch (Exception $e) {
            echo 'Erro ao cadastrar turma: ' . $e->getMessage();
        }
    }

    // Método para excluir turma
    public function excluir() {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = "DELETE FROM turmas WHERE CodTurma = :CodTurma";
            $stmt = $this->conn->prepare($sql);
            $stmt->bindValue(':CodTurma', $this->getCodTurma());
            return $stmt->execute();
        } catch (Exception $e) {
            echo 'Erro ao excluir turma: ' . $e->getMessage();
        }
    }

    // Método para editar turma
    public function editar() {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = "UPDATE turmas SET Nome = :Nome, Periodo = :Periodo, CodCurso = :CodCurso WHERE CodTurma = :CodTurma";
            $stmt = $this->conn->prepare($sql);
            $stmt->bindValue(':CodTurma', $this->getCodTurma());
            $stmt->bindValue(':Nome', $this->getNome());
            $stmt->bindValue(':Periodo', $this->getPeriodo());
            $stmt->bindValue(':CodCurso', $this->getCodCurso());
            return $stmt->execute();
        } catch (Exception $e) {
            echo 'Erro ao editar turma: ' . $e->getMessage();
        }
    }

    // Método para buscar turma por código
    public function buscarPorCodigo() {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = "SELECT * FROM turmas WHERE CodTurma = :CodTurma";
            $stmt = $this->conn->prepare($sql);
            $stmt->bindValue(':CodTurma', $this->getCodTurma());
            $stmt->execute();
            return $stmt->fetch(PDO::FETCH_ASSOC);
        } catch (Exception $e) {
            echo 'Erro ao buscar turma: ' . $e->getMessage();
        }
    }

    // Método para listar turmas
    public function listar() {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = "SELECT * FROM turmas";
            $stmt = $this->conn->prepare($sql);
            $stmt->execute();
            return $stmt->fetchAll(PDO::FETCH_ASSOC);
        } catch (Exception $e) {
            echo 'Erro ao listar turmas: ' . $e->getMessage();
        }
    }
}

?>