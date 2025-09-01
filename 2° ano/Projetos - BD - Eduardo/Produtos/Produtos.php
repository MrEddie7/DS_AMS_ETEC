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

    public function listarEstoqueMinimo() {
        $this->conn = new Conectar();
        $sql = $this->conn->prepare("SELECT * FROM produtos WHERE Estoque < 20");
        $sql->execute();
        $result = $sql->fetchAll(PDO::FETCH_ASSOC);
        $this->conn = null;
        return $result;
    }

    public function listarOrdenadoPorNome() {
        $this->conn = new Conectar();
        $sql = $this->conn->prepare("SELECT * FROM produtos ORDER BY Nome");
        $sql->execute();
        $result = $sql->fetchAll(PDO::FETCH_ASSOC);
        $this->conn = null;
        return $result;
    }

    // metodos de salvar produtos

    function salvar ()
    {
        try {
            $this->conn = new Conectar();
            $sql = $this->conn->prepare("insert into produtos values (null,?,?)");
            @$sql->bindParam(1, $this->getNome(), PDO::PARAM_STR);
            @$sql->bindParam(2, $this->getEstoque(), PDO::PARAM_STR);
            if($sql->execute() == 1)
            {
                return "Registro realizado";
            }
            $this->conn = null;


        } catch (PDOException $exc) {
      echo "Erro de conexao" . $exc->getMessage();
        }
    }

    // metodo de alteração
    function alterar()
    {
        try {
            $this->conn = new Conectar();
            $sql = $this->conn->prepare("select * from produtos where id = ?");
            $sql->bindParam(1, $this->getId(), PDO::PARAM_STR);
            $sql->execute();
            $result = $sql->fetchAll();
            $this->conn = null; // Fecha a conexão
            return $result;
        } catch (PDOException $exc) {
            $this->conn = null; // Garante que a conexão seja fechada
            echo "Erro ao listar. " . $exc->getMessage();
        }
    }
    
    function alterar2()
    {
        try {
            $this->conn = new Conectar();
            $sql = $this->conn->prepare("update produtos set nome = ?, estoque = ? where id = ?");
           @$sql->bindParam(1, $this->getNome(), PDO::PARAM_STR);
           @$sql->bindParam(2, $this->getEstoque(), PDO::PARAM_STR);
            @$sql->bindParam(3, $this->getId(), PDO::PARAM_STR);
    
            if ($sql->execute()) {
                $this->conn = null; // Fecha a conexão
                return "Registro realizado com sucesso!";
            }
        } catch (PDOException $exc) {
            $this->conn = null; // Garante que a conexão seja fechada
            echo "Erro ao salvar. " . $exc->getMessage();
        }
    }
 // metodo de consulta
    function consultar()
    {
        try {
            $this->conn = new Conectar();
            $sql = $this->conn->prepare("select * from produtos where nome like ?");
            $nomeBusca = "%" . $this->getNome() . "%";
            $sql->bindParam(1, $nomeBusca, PDO::PARAM_STR);
            $sql->execute();
            $result = $sql->fetchAll(PDO::FETCH_ASSOC);
            $this->conn = null;
            return $result;
        } catch (PDOException $exc) {
            echo "erro ao executar consulta. " . $exc->getMessage();
        }
    }

    // excluir produto
    
    function exclusao()
    {
        try {
            $this->conn = new Conectar();
            $sql = $this->conn->prepare("delete from produtos where id = ?");
            @$sql->bindParam(1, $this->getId(), PDO::PARAM_STR);
            if($sql->execute() == 1){
                return "Excluido com sucesso";
            }
            else {
                return "Erro na exclusão !";
            }

            $this->conn = null;

        } catch (PDOException $exc) {
            echo "Erro ao excluir. " . $exc->getMessage();
        }
    }

}