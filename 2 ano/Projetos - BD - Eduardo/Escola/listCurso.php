<?php

include_once 'Conexao.php';

// Classe Curso
class Curso
{
    private string $CodCurso;
    private string $Nome;
    private string $coddisc1;
    private string $coddisc2;
    private string $coddisc3;
    private ?PDO $conn = null;

    // Getters e Setters
    public function getCodCurso(): string
    {
        return $this->CodCurso;
    }
    public function setCodCurso(string $codCurso): void
    {
        $this->CodCurso = $codCurso;
    }
    public function getNome(): string
    {
        return $this->Nome;
    }
    public function setNome(string $nome): void
    {
        $this->Nome = $nome;
    }
    public function getCodDisc1(): string
    {
        return $this->coddisc1;
    }
    public function setCodDisc1(string $coddisc1): void
    {
        $this->coddisc1 = $coddisc1;
    }
    public function getCodDisc2(): string
    {
        return $this->coddisc2;
    }
    public function setCodDisc2(string $coddisc2): void
    {
        $this->coddisc2 = $coddisc2;
    }
    public function getCodDisc3(): string
    {
        return $this->coddisc3;
    }
    public function setCodDisc3(string $coddisc3): void
    {
        $this->coddisc3 = $coddisc3;
    }

    // Método para cadastrar curso
    public function cadastrar()
    {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = $this->conn->prepare("INSERT INTO cursos (CodCurso, Nome, coddisc1, coddisc2, coddisc3) VALUES (:CodCurso, :Nome, :coddisc1, :coddisc2, :coddisc3)");
            $sql->bindValue(':CodCurso', $this->CodCurso);
            $sql->bindValue(':Nome', $this->Nome);
            $sql->bindValue(':coddisc1', $this->coddisc1);
            $sql->bindValue(':coddisc2', $this->coddisc2);
            $sql->bindValue(':coddisc3', $this->coddisc3);
            $sql->execute();
            $this->conn = null; // Fechar conexão
        } catch (PDOException $exc) {
            echo "Erro ao cadastrar curso: " . $exc->getMessage();
        }
    }
    // Método para excluir curso
    public function excluir()
    {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = $this->conn->prepare("DELETE FROM cursos WHERE CodCurso = :CodCurso");
            $sql->bindValue(':CodCurso', $this->CodCurso);
            if ($sql->execute()) {
                return true;
            } else {
                return false;
            }
        } catch (PDOException $exc) {
            echo "Erro ao excluir curso: " . $exc->getMessage();
        }
    }
    // Método para editar curso
    public function editar()
    {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = $this->conn->prepare("UPDATE cursos SET Nome = :Nome, coddisc1 = :coddisc1, coddisc2 = :coddisc2, coddisc3 = :coddisc3 WHERE CodCurso = :CodCurso");
            $sql->bindValue(':CodCurso', $this->CodCurso);
            $sql->bindValue(':Nome', $this->Nome);
            $sql->bindValue(':coddisc1', $this->coddisc1);
            $sql->bindValue(':coddisc2', $this->coddisc2);
            $sql->bindValue(':coddisc3', $this->coddisc3);
            if ($sql->execute()) {
                return true;
            } else {
                return false;
            }
        } catch (PDOException $exc) {
            echo "Erro ao editar curso: " . $exc->getMessage();
        }
    }
    

    // Método para listar cursos
    public function listar()
    {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = $this->conn->prepare("SELECT * FROM cursos ORDER BY CodCurso");
            $sql->execute();
            $result = $sql->fetchAll(PDO::FETCH_ASSOC);
            $this->conn = null; // Fechar conexão
            return $result;
        } catch (PDOException $exc) {
            echo "Erro ao executar consulta: " . $exc->getMessage();
        }
    }
}
