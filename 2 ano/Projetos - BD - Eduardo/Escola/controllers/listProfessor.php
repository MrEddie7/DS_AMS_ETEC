<?php

include_once __DIR__ . '/../models/Conexao.php';
class Professor {
   
    private string $CodProfessor;
    private string $Nome;
    private string $Endereco;
    private string $Cidade;
    private string $CodDisciplina;
    private string $CodCurso;
    private ?PDO $conn = null;

    // Getters e Setters
    public function getCodProfessor(): string {
        return $this->CodProfessor;
    }
    public function setCodProfessor(string $codProfessor): void {
        $this->CodProfessor = $codProfessor;
    }
    public function getNome(): string {
        return $this->Nome;
    }
    public function setNome(string $nome): void {
        $this->Nome = $nome;
    }
    public function getEndereco(): string {
        return $this->Endereco;
    }
    public function setEndereco(string $endereco): void {
        $this->Endereco = $endereco;
    }
    public function getCidade(): string {
        return $this->Cidade;
    }
    public function setCidade(string $cidade): void {
        $this->Cidade = $cidade;
    }
    public function getCodDisciplina(): string {
        return $this->CodDisciplina;
    }
    public function setCodDisciplina(string $codDisciplina): void {
        $this->CodDisciplina = $codDisciplina;
    }
    public function getCodCurso(): string {
        return $this->CodCurso;
    }
    public function setCodCurso(string $codCurso): void {
        $this->CodCurso = $codCurso;
    }
    // Método para cadastrar professor
    public function cadastrar() {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = "INSERT INTO professores (CodProfessor, Nome, Endereco, Cidade, CodDisciplina, CodCurso) VALUES (:CodProfessor, :Nome, :Endereco, :Cidade, :CodDisciplina, :CodCurso)";
            $stmt = $this->conn->prepare($sql);
            $stmt->bindValue(':CodProfessor', $this->getCodProfessor());
            $stmt->bindValue(':Nome', $this->getNome());
            $stmt->bindValue(':Endereco', $this->getEndereco());
            $stmt->bindValue(':Cidade', $this->getCidade());
            $stmt->bindValue(':CodDisciplina', $this->getCodDisciplina());
            $stmt->bindValue(':CodCurso', $this->getCodCurso());
            return $stmt->execute();
        } catch (Exception $e) {
            echo 'Erro ao cadastrar professor: ' . $e->getMessage();
        }
    }
    


// Método para excluir professor
public function excluir() {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = "DELETE FROM professores WHERE CodProfessor = :CodProfessor";
            $stmt = $this->conn->prepare($sql);
            $stmt->bindValue(':CodProfessor', $this->getCodProfessor());
            return $stmt->execute();
        } catch (Exception $e) {
            echo 'Erro ao excluir professor: ' . $e->getMessage();
        }
    }
    public function editar() {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = "UPDATE professores SET Nome = :Nome, Endereco = :Endereco, Cidade = :Cidade, CodDisciplina = :CodDisciplina, CodCurso = :CodCurso WHERE CodProfessor = :CodProfessor";
            $stmt = $this->conn->prepare($sql);
            $stmt->bindValue(':CodProfessor', $this->getCodProfessor());
            $stmt->bindValue(':Nome', $this->getNome());
            $stmt->bindValue(':Endereco', $this->getEndereco());
            $stmt->bindValue(':Cidade', $this->getCidade());
            $stmt->bindValue(':CodDisciplina', $this->getCodDisciplina());
            $stmt->bindValue(':CodCurso', $this->getCodCurso());
            return $stmt->execute();
        } catch (Exception $e) {
            echo 'Erro ao editar professor: ' . $e->getMessage();
        }
    }
    // Método para buscar professor por código
    public function buscarPorCodigo() {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = "SELECT * FROM professores WHERE CodProfessor = :CodProfessor";
            $stmt = $this->conn->prepare($sql);
            $stmt->bindValue(':CodProfessor', $this->getCodProfessor());
            $stmt->execute();
            return $stmt->fetch(PDO::FETCH_ASSOC);
        } catch (Exception $e) {
            echo 'Erro ao buscar professor: ' . $e->getMessage();
        }
    }
    // Método para listar professores
    public function listar() {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = "SELECT * FROM professores";
            $stmt = $this->conn->prepare($sql);
            $stmt->execute();
            return $stmt->fetchAll(PDO::FETCH_ASSOC);
        } catch (Exception $e) {
            echo 'Erro ao listar professores: ' . $e->getMessage();
        }
}
}