
package matrizz;

import java.util.Scanner;
public class matrizz2 {
    public static void main(String[] args) {
        
    


        Scanner sc = new Scanner(System.in);
        int[][] matriz = new int[5][5];
        int somaImpares = 0;
        int[] somaColunas = new int[5];
        int[] somaLinhas = new int[5];

        // Preenchendo a matriz e calculando as somas
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println("Digite o valor para a posição [" + i + "][" + j + "]: ");
                matriz[i][j] = sc.nextInt();
                if (matriz[i][j] % 2 != 0) {
                    somaImpares += matriz[i][j];
                }
                somaColunas[j] += matriz[i][j];
                somaLinhas[i] += matriz[i][j];
            }
        }

        // Exibindo resultados
        System.out.println("Soma dos números ímpares: " + somaImpares);
        for (int i = 0; i < 5; i++) {
            System.out.println("Soma da coluna " + (i+1) + ": " + somaColunas[i]);
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("Soma da linha " + (i+1) + ": " + somaLinhas[i]);
        }
    }
}


