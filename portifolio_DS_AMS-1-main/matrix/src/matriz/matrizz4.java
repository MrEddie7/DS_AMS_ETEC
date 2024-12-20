/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matrizz;

import java.util.Scanner;

public class matrizz4{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] tabuleiro = new char[3][3];  // Matriz para o tabuleiro
        int linha, coluna;
        boolean jogoEmAndamento = true;
        char jogadorAtual = 'X';  // Começa com o jogador X
        int jogadas = 0;

        // Inicializa o tabuleiro com espaços vazios
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = ' ';
            }
        }

        // Loop principal do jogo
        while (jogoEmAndamento) {
            // Exibe o tabuleiro
            exibirTabuleiro(tabuleiro);

            // Solicita a jogada do jogador
            System.out.println("Jogador " + jogadorAtual + ", faça sua jogada.");
            System.out.print("Escolha a linha (0-2): ");
            linha = scanner.nextInt();
            System.out.print("Escolha a coluna (0-2): ");
            coluna = scanner.nextInt();

            // Verifica se a posição é válida
            if (linha < 0 || linha > 2 || coluna < 0 || coluna > 2 || tabuleiro[linha][coluna] != ' ') {
                System.out.println("Posição inválida, tente novamente.");
                continue;
            }

            // Marca a jogada no tabuleiro
            tabuleiro[linha][coluna] = jogadorAtual;
            jogadas++;

            // Verifica se há um vencedor
            if (verificarVencedor(tabuleiro, jogadorAtual)) {
                exibirTabuleiro(tabuleiro);
                System.out.println("Jogador " + jogadorAtual + " venceu!");
                jogoEmAndamento = false;
            } else if (jogadas == 9) {
                // Verifica se o jogo deu empate
                exibirTabuleiro(tabuleiro);
                System.out.println("Empate! O jogo terminou sem vencedor.");
                jogoEmAndamento = false;
            } else {
                // Troca o jogador
                jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
            }
        }

        scanner.close();
    }

    // Função para exibir o tabuleiro
    public static void exibirTabuleiro(char[][] tabuleiro) {
        System.out.println("\nTabuleiro:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("--+---+--");
        }
    }

    // Função para verificar se há um vencedor
    public static boolean verificarVencedor(char[][] tabuleiro, char jogador) {
        // Verificar linhas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == jogador && tabuleiro[i][1] == jogador && tabuleiro[i][2] == jogador) {
                return true;
            }
        }

        // Verificar colunas
        for (int j = 0; j < 3; j++) {
            if (tabuleiro[0][j] == jogador && tabuleiro[1][j] == jogador && tabuleiro[2][j] == jogador) {
                return true;
            }
        }

        // Verificar diagonal principal
        if (tabuleiro[0][0] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][2] == jogador) {
            return true;
        }

        // Verificar diagonal secundária
        if (tabuleiro[0][2] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][0] == jogador) {
            return true;
        }

        return false;
    }
}

