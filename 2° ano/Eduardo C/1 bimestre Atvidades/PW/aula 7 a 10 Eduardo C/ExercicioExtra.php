<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Recupera qual exercício foi escolhido
    $exercicio = $_POST['exercicio'];

    switch ($exercicio) {
        case 'tabuada':
            $numero = $_POST['numero'];
            echo "<h2>Tabuada de $numero:</h2>";
            for ($i = 1; $i <= 10; $i++) {
                echo "$numero x $i = " . ($numero * $i) . "<br>";
            }
            break;

        case 'desconto':
            $preco = $_POST['preco'];
            $desconto = $_POST['desconto'];
            $preco_com_desconto = $preco - ($preco * $desconto / 100);
            echo "<h2>Preço com Desconto:</h2>";
            echo "Preço original: R$ " . number_format($preco, 2, ',', '.') . "<br>";
            echo "Preço com desconto: R$ " . number_format($preco_com_desconto, 2, ',', '.');
            break;

        case 'aprovacao':
            $nota1 = $_POST['nota1'];
            $nota2 = $_POST['nota2'];
            $nota3 = $_POST['nota3'];
            $nota4 = $_POST['nota4'];
            $media = ($nota1 + $nota2 + $nota3 + $nota4) / 4;
            echo "<h2>Resultado da Aprovação:</h2>";
            echo "Média: " . number_format($media, 2) . "<br>";
            echo $media >= 5 ? "Aprovado!" : "Reprovado!";
            break;

        case 'maior_menor':
            $num1 = $_POST['num1'];
            $num2 = $_POST['num2'];
            $num3 = $_POST['num3'];
            $maior = max($num1, $num2, $num3);
            $menor = min($num1, $num2, $num3);
            echo "<h2>Maior e Menor Número:</h2>";
            echo "Maior número: $maior<br>";
            echo "Menor número: $menor";
            break;

        case 'quadrados':
            $num1 = $_POST['num1'];
            $num2 = $_POST['num2'];
            $num3 = $_POST['num3'];
            $soma_dos_quadrados = pow($num1, 2) + pow($num2, 2) + pow($num3, 2);
            echo "<h2>Soma dos Quadrados:</h2>";
            echo "A soma dos quadrados é: $soma_dos_quadrados";
            break;

        case 'salario':
            $salario_bruto = $_POST['salario_bruto'];
            $gratificacao = $salario_bruto * 0.10;
            $imposto = $salario_bruto * 0.20;
            $salario_liquido = $salario_bruto + $gratificacao - $imposto;
            echo "<h2>Salário Líquido:</h2>";
            echo "Salário líquido: R$ " . number_format($salario_liquido, 2, ',', '.');
            break;

        case 'aprovacao_media':
            $nota1 = $_POST['nota1'];
            $nota2 = $_POST['nota2'];
            $nota3 = $_POST['nota3'];
            $nota4 = $_POST['nota4'];
            $media = ($nota1 + $nota2 + $nota3 + $nota4) / 4;
            echo "<h2>Aprovação com Média:</h2>";
            echo "Média: " . number_format($media, 2) . "<br>";
            if ($media >= 6) {
                echo "Aprovado!";
            } elseif ($media < 3) {
                echo "Retido!";
            } else {
                echo "Exame!";
            }
            break;

        case 'par_impar':
            $numero = $_POST['numero'];
            echo "<h2>Resultado:</h2>";
            if ($numero % 2 == 0) {
                echo "O número $numero é Par.";
            } else {
                echo "O número $numero é Ímpar.";
            }
            break;

        // Exercício 9 - Soma dos Números Ímpares
        case 'soma_impares':
            $inicio = $_POST['inicio'];
            $fim = $_POST['fim'];
            $soma = 0;
            
            for ($i = $inicio; $i <= $fim; $i++) {
                if ($i % 2 != 0) {
                    $soma += $i;
                }
            }
            echo "<h2>Soma dos Números Ímpares entre $inicio e $fim: $soma</h2>";
            break;

        // Exercício 10 - Verificar Par ou Ímpar
        case 'par_impar':
            $numero = $_POST['numero'];
            
            if ($numero % 2 == 0) {
                echo "<h2>O número $numero é Par.</h2>";
            } else {
                echo "<h2>O número $numero é Ímpar.</h2>";
            }
            break;

        // Exercício 11 - Calculadora Básica
        case 'calculadora':
            $num1 = $_POST['num1'];
            $num2 = $_POST['num2'];
            $operador = $_POST['operador'];
            
            switch ($operador) {
                case '+':
                    $resultado = $num1 + $num2;
                    break;
                case '-':
                    $resultado = $num1 - $num2;
                    break;
                case '*':
                    $resultado = $num1 * $num2;
                    break;
                case '/':
                    if ($num2 != 0) {
                        $resultado = $num1 / $num2;
                    } else {
                        $resultado = "Erro: Divisão por zero.";
                    }
                    break;
                default:
                    $resultado = "Operador inválido.";
            }
            
            echo "<h2>Resultado: $resultado</h2>";
            break;

        // Exercício 12 - Exibir Números Ímpares em Ordem Decrescente
        case 'impares_decrescente':
            $inicio = $_POST['inicio'];
            $fim = $_POST['fim'];
            
            // Encontrando o maior valor ímpar entre os dois números
            if ($fim % 2 == 0) {
                $fim--; // Se o valor final for par, fazemos ele virar ímpar
            }
            
            echo "<h2>Números ímpares entre $inicio e $fim (em ordem decrescente):</h2>";
            for ($i = $fim; $i >= $inicio; $i -= 2) {
                echo "<p>$i</p>";
            }
            break;
    }
}
?>

