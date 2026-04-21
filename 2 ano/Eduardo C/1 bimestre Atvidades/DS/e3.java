public class e3 {
    public static void main(String[] args) {
        int somaImpares = 0;
        int multiplicaPares = 1;

        // Loop entre 0 e 30
        for (int i = 0; i <= 30; i++) {
            if (i % 2 != 0) {
                somaImpares += i;  // Soma dos numeros i­mpares
            } else {
                multiplicaPares *= i;  // Multiplicao dos numeros pares
            }
        }

        // Exibir os resultados
        String resultado = "Soma dos numeros impares entre 0 e 30: " + somaImpares + "\n"
                + "Multiplicao dos numeros pares entre 0 e 30: " + multiplicaPares;
        
        javax.swing.JOptionPane.showMessageDialog(null, resultado);
    }
}