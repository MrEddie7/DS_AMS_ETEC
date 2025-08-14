<?php

include_once __DIR__ . '/../models/Conexao.php'; 

// Classe Produto
class Aluno
{
    private int $Matricula;
    private string $Nome;
    private int $Endereco;
    private string $cidade;
    private int $CodCurso;
    private ?PDO $conn = null;

    // Getters e Setters
    public function getMatricula(): int
    {
        return $this->Matricula;
    }
    public function setMatricula(int $matricula): void
    {
        $this->Matricula = $matricula;
    }
    public function getNome(): string
    {
        return $this->Nome;
    }
    public function setNome(string $nome): void
    {
        $this->Nome = $nome;
    }
    public function getEndereco(): int
    {
        return $this->Endereco;
    }
    public function setEndereco(int $endereco): void
    {
        $this->Endereco = $endereco;
    }
    public function getCidade(): string
    {
        return $this->cidade;
    }
    public function setCidade(string $cidade): void
    {
        $this->cidade = $cidade;
    }
    public function getCodCurso(): int
    {
        return $this->CodCurso;
    }
    public function setCodCurso(int $codCurso): void
    {
        $this->CodCurso = $codCurso;
    }
    // Método para cadastrar aluno
    public function cadastrar()
    {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = $this->conn->prepare("INSERT INTO alunos (Matricula, Nome, Endereco, cidade, CodCurso) VALUES (:Matricula, :Nome, :Endereco, :cidade, :CodCurso)");
            $sql->bindValue(':Matricula', $this->getMatricula());
            $sql->bindValue(':Nome', $this->getNome());
            $sql->bindValue(':Endereco', $this->getEndereco());
            $sql->bindValue(':cidade', $this->getCidade());
            $sql->bindValue(':CodCurso', $this->getCodCurso());
            if ($sql->execute()) {
                return true;
            } else {
                return false;
            }
        } catch (PDOException $exc) {
            echo "Erro ao cadastrar aluno: " . $exc->getMessage();
        }
    }
    // Método para editar aluno
    public function editar()
    {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = $this->conn->prepare("UPDATE alunos SET Nome = :Nome, Endereco = :Endereco, cidade = :cidade, CodCurso = :CodCurso WHERE Matricula = :Matricula");
            $sql->bindValue(':Matricula', $this->getMatricula());
            $sql->bindValue(':Nome', $this->getNome());
            $sql->bindValue(':Endereco', $this->getEndereco());
            $sql->bindValue(':cidade', $this->getCidade());
            $sql->bindValue(':CodCurso', $this->getCodCurso());
            if ($sql->execute()) {
                return true;
            } else {
                return false;
            }
        } catch (PDOException $exc) {
            echo "Erro ao editar aluno: " . $exc->getMessage();
        }
    }
    // Método para excluir aluno
    public function excluir()
    {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = $this->conn->prepare("DELETE FROM alunos WHERE Matricula = :Matricula");
            $sql->bindValue(':Matricula', $this->getMatricula());
            if ($sql->execute()) {
                return true;
            } else {
                return false;
            }
        } catch (PDOException $exc) {
            echo "Erro ao excluir aluno: " . $exc->getMessage();
        }
    }
    // Método para buscar aluno por matrícula
    public function buscarPorMatricula()
    {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = $this->conn->prepare("SELECT * FROM alunos WHERE Matricula = :Matricula");
            $sql->bindValue(':Matricula', $this->getMatricula());
            $sql->execute();
            $result = $sql->fetch(PDO::FETCH_ASSOC);
            $this->conn = null; // Fechar conexão
            return $result;
        } catch (PDOException $exc) {
            echo "Erro ao buscar aluno: " . $exc->getMessage();
        }
    }
    // Método para buscar aluno por nome
    public function buscarPorNome()
    {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = $this->conn->prepare("SELECT * FROM alunos WHERE Nome LIKE :Nome");
            $sql->bindValue(':Nome', '%' . $this->getNome() . '%');
            $sql->execute();
            $result = $sql->fetchAll(PDO::FETCH_ASSOC);
            $this->conn = null; // Fechar conexão
            return $result;
        } catch (PDOException $exc) {
            echo "Erro ao buscar aluno: " . $exc->getMessage();
        }
    }
    // Método para buscar aluno por cidade
    public function buscarPorCidade()
    {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = $this->conn->prepare("SELECT * FROM alunos WHERE cidade LIKE :cidade");
            $sql->bindValue(':cidade', '%' . $this->getCidade() . '%');
            $sql->execute();
            $result = $sql->fetchAll(PDO::FETCH_ASSOC);
            $this->conn = null; // Fechar conexão
            return $result;
        } catch (PDOException $exc) {
            echo "Erro ao buscar aluno: " . $exc->getMessage();
        }
    }
    // Método para buscar aluno por curso
    public function buscarPorCurso()
    {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = $this->conn->prepare("SELECT * FROM alunos WHERE CodCurso = :CodCurso");
            $sql->bindValue(':CodCurso', $this->getCodCurso());
            $sql->execute();
            $result = $sql->fetchAll(PDO::FETCH_ASSOC);
            $this->conn = null; // Fechar conexão
            return $result;
        } catch (PDOException $exc) {
            echo "Erro ao buscar aluno: " . $exc->getMessage();
        }
    }
    // Método para buscar aluno por endereço
    public function buscarPorEndereco()
    {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = $this->conn->prepare("SELECT * FROM alunos WHERE Endereco LIKE :Endereco");
            $sql->bindValue(':Endereco', '%' . $this->getEndereco() . '%');
            $sql->execute();
            $result = $sql->fetchAll(PDO::FETCH_ASSOC);
            $this->conn = null; // Fechar conexão
            return $result;
        } catch (PDOException $exc) {
            echo "Erro ao buscar aluno: " . $exc->getMessage();
        }
    }
    // Método para listar alunos
    public function listar()
    {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = $this->conn->prepare("SELECT * FROM alunos ORDER BY Matricula");
            $sql->execute();
            $result = $sql->fetchAll(PDO::FETCH_ASSOC);
            $this->conn = null; // Fechar conexão
            return $result;
        } catch (PDOException $exc) {
            echo "Erro ao executar consulta: " . $exc->getMessage();
        }
    }
}