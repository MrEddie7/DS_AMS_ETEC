
// matriz parte C exercício 1
public class MatrizC {
    public static void main(String[] args) {
        // Declaração da matriz de números decimais 3x3
        double[][] matriz = {
            {1.9, 2.5, 10.0},
            {1.0, 7.8, 2.5},
            {3.5, 2.2, 4.7}
        };

        // Exibe a matriz
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}