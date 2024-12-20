/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matrizz;

import java.util.Scanner;

public class matrizz3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[][] matriz = new double[4][4];

        // Preenchendo a matriz
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.println("Digite o valor para a posição [" + i + "][" + j + "]: ");
                matriz[i][j] = sc.nextDouble();
            }
        }

        // Exibindo a diagonal principal
        System.out.println("Valores da diagonal principal:");
        for (int i = 0; i < 4; i++) {
            System.out.print(matriz[i][i] + " ");
        }

        // Exibindo a diagonal secundária
        System.out.println("\nValores da diagonal secundária:");
        for (int i = 0; i < 4; i++) {
            System.out.print(matriz[i][3 - i] + " ");
        }
    }
}
