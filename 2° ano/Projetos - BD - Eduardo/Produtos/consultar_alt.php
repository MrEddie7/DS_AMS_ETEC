<?php

require_once 'Produtos.php';

$alterar = new Alterar();

// Simulação de alteração (adicione produtos via formulário)
if ($_SERVER['REQUEST_METHOD'] === 'POST' && !empty($_POST['nome'])) {
    $alterar->adicionar($_POST['nome']);
}

// Consulta dos produtos alterados
$produtos = $alterar->listar();
?>

<form method="post">
    Nome do produto: <input type="text" name="nome">
    <input type="submit" value="Alterar Produto">
</form>

<h3>Produtos alterados:</h3>
<ul>
    <?php foreach ($produtos as $p): ?>
        <li><?php echo htmlspecialchars($p); ?></li>
    <?php endforeach; ?>
</ul>

            else {
                return "Erro ao registrar";
            }
            $this->conn = null;
        } catch (PDOException $exc) {
            echo "Erro ao executar consulta: " . $exc->getMessage();
        }
    }
}
            }
        } catch (PDOException $exc) {
            $this->conn = null;
            echo "Erro ao excluir. " . $exc->getMessage();
        }
    }
}
            $this->conn = new Conectar();
            $sql = $this->conn->prepare("select nome from produtos where nome like ?");
            $nomeBusca = "%" . $this->getNome() . "%";
            $sql->bindParam(1, $nomeBusca, PDO::PARAM_STR);
            $sql->execute();
            $result = $sql->fetchAll(PDO::FETCH_COLUMN);
            $this->conn = null;
            return $result;
        } catch (PDOException $exc) {
            echo "Erro ao executar consulta: " . $exc->getMessage();
        }
    } 
    public function adicionar($nome) {
        try {
            $this->conn = new Conectar();
            $sql = $this->conn->prepare("insert into produtos (nome) values (?)");
            $sql->bindParam(1, $nome, PDO::PARAM_STR);
            if ($sql->execute()) {
                $this->conn = null;
                return "Registro realizado com sucesso!";  
