<?php

include_once 'Conexao.php'; 

// Classe Produto
class Produto
{
    private int $id;
    private string $nome;
    private int $estoque;
    private ?PDO $conn = null;

    // Getters e Setters
    public function getId(): int
    {
        return $this->id;
    }

    public function setId(int $iid): void
    {
        $this->id = $iid;
    }

    public function getNome(): string
    {
        return $this->nome;
    }

    public function setNome(string $name): void
    {
        $this->nome = $name;
    }

    public function getEstoque(): int
    {
        return $this->estoque;
    }

    public function setEstoque(int $estoqui): void
    {
        $this->estoque = $estoqui;
    }

    // Método para listar produtos
    public function listar()
    {
        try {
            $this->conn = new Conectar; // Conectar método getConnection()
            $sql = $this->conn->prepare("SELECT * FROM produtos ORDER BY Nome");
            $sql->execute();
            $result = $sql->fetchAll(PDO::FETCH_ASSOC);
            $this->conn = null; // Fechar conexão
            return $result;
        } catch (PDOException $exc) {
            echo "Erro ao executar consulta: " . $exc->getMessage();
        }
    }
}