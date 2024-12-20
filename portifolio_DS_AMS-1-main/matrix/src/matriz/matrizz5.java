// matriz parte A exercício 1 

public class MatrizLetras {
    public static void main(String[] args) {
        // Declaração da matriz de caracteres 4x5
        char[][] matriz = {
            {'a', 'b', 'c', 'd', 'e'},
            {'f', 'g', 'h', 'i', 'j'},
            {'l', 'm', 'n', 'o', 'p'},
            {'q', 'r', 's', 't', 'u'}
        };
        
        // Exibe a matriz
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println(); // Quebra de linha após cada linha da matriz
        }
    }
}