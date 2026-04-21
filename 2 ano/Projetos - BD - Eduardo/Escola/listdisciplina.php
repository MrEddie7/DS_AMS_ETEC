<?php

include_once 'Conexao.php';

// Classe Disciplina
class Disciplina
{
    private string $CodDisciplina;
    private string $NomeDisciplina;
    private ?PDO $conn = null;

    // Getters e Setters
    public function getCodDisciplina(): string
    {
        return $this->CodDisciplina;
    }
    public function setCodDisciplina(string $codDisciplina): void
    {
        $this->CodDisciplina = $codDisciplina;
    }
    public function getNomeDisciplina(): string
    {
        return $this->NomeDisciplina;
    }
    public function setNomeDisciplina(string $nomeDisciplina): void
    {
        $this->NomeDisciplina = $nomeDisciplina;
    }

    // Método para cadastrar disciplina
    public function cadastrar()
    {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = "INSERT INTO disciplinas (CodDisciplina, NomeDisciplina) VALUES (:CodDisciplina, :NomeDisciplina)";
            $stmt = $this->conn->prepare($sql);
            $stmt->bindValue(':CodDisciplina', $this->getCodDisciplina());
            $stmt->bindValue(':NomeDisciplina', $this->getNomeDisciplina());
            return $stmt->execute();
        } catch (Exception $e) {
            echo 'Erro ao cadastrar disciplina: ' . $e->getMessage();
        }
    }
    // Método para listar disciplinas
    public function listar()
    {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = "SELECT * FROM disciplinas";
            $stmt = $this->conn->prepare($sql);
            $stmt->execute();
            return $stmt->fetchAll(PDO::FETCH_ASSOC);
        } catch (Exception $e) {
            echo 'Erro ao listar disciplinas: ' . $e->getMessage();
        }
    }
    // Método para excluir disciplina
    public function excluir()
    {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = "DELETE FROM disciplinas WHERE CodDisciplina = :CodDisciplina";
            $stmt = $this->conn->prepare($sql);
            $stmt->bindValue(':CodDisciplina', $this->getCodDisciplina());
            return $stmt->execute();
        } catch (Exception $e) {
            echo 'Erro ao excluir disciplina: ' . $e->getMessage();
        }
    }
    // Método para editar disciplina
    public function editar()
    {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = "UPDATE disciplinas SET NomeDisciplina = :NomeDisciplina WHERE CodDisciplina = :CodDisciplina";
            $stmt = $this->conn->prepare($sql);
            $stmt->bindValue(':CodDisciplina', $this->getCodDisciplina());
            $stmt->bindValue(':NomeDisciplina', $this->getNomeDisciplina());
            return $stmt->execute();
        } catch (Exception $e) {
            echo 'Erro ao editar disciplina: ' . $e->getMessage();
        }
    }
    // Método para buscar disciplina por código
    public function buscarPorCodigo()
    {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = "SELECT * FROM disciplinas WHERE CodDisciplina = :CodDisciplina";
            $stmt = $this->conn->prepare($sql);
            $stmt->bindValue(':CodDisciplina', $this->getCodDisciplina());
            $stmt->execute();
            return $stmt->fetch(PDO::FETCH_ASSOC);
        } catch (Exception $e) {
            echo 'Erro ao buscar disciplina: ' . $e->getMessage();
        }
    }
    // Método para buscar disciplina por nome
    public function buscarPorNome()
    {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = "SELECT * FROM disciplinas WHERE NomeDisciplina LIKE :NomeDisciplina";
            $stmt = $this->conn->prepare($sql);
            $stmt->bindValue(':NomeDisciplina', '%' . $this->getNomeDisciplina() . '%');
            $stmt->execute();
            return $stmt->fetchAll(PDO::FETCH_ASSOC);
        } catch (Exception $e) {
            echo 'Erro ao buscar disciplina: ' . $e->getMessage();
        }
    }
     // Método para listar disciplinas para o curso
    public function listarDisciplinasCurso($codCurso)
    {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = "SELECT * FROM disciplinas WHERE CodCurso = :CodCurso";
            $stmt = $this->conn->prepare($sql);
            $stmt->bindValue(':CodCurso', $codCurso);
            $stmt->execute();
            return $stmt->fetchAll(PDO::FETCH_ASSOC);
        } catch (Exception $e) {
            echo 'Erro ao listar disciplinas do curso: ' . $e->getMessage();
        }
    }
}
    
