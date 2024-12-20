/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package matrizz;

import java.util.Scanner;
public class Matrizz {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     
     

 {
        Scanner sc = new Scanner(System.in);
        int[][] matriz = new int[5][5];
        int somaImpares = 0;
        int[] somaColunas = new int[5];
        int[] somaLinhas = new int[5];
        
        // Preencher a matriz
        System.out.println("Preencha a matriz 5x5:");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print("Elemento [" + i + "][" + j + "]: ");
                matriz[i][j] = sc.nextInt();
                
                // Somar números ímpares
                if (matriz[i][j] % 2 != 0) {
                    somaImpares += matriz[i][j];
                }

                // Somar as colunas
                somaColunas[j] += matriz[i][j];

                // Somar as linhas
                somaLinhas[i] += matriz[i][j];
            }
        }

        // Resultado das operações
        System.out.println("\nSoma dos números ímpares: " + somaImpares);

        System.out.println("\nSoma de cada coluna:");
        for (int j = 0; j < 5; j++) {
            System.out.println("Coluna " + j + ": " + somaColunas[j]);
        }

        System.out.println("\nSoma de cada linha:");
        for (int i = 0; i < 5; i++) {
            System.out.println("Linha " + i + ": " + somaLinhas[i]);
        }
    }
}
  
        
    }
    

