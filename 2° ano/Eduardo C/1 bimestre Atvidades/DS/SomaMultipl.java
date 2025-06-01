import java.util.Scanner;
public class SomaMultipl {
    public static void main(String[] args) {
        
        int somaimpar = 0;
        int multpar = 1;
        
        // loop entre zero e 30
        
        for (int i = 0; i <= 30; i++){
            if(i % 2 !=0){
                somaimpar += i;  // Soma dos numeros i­mpares
            } else {
                multpar *= i;  // Multiplicao dos numeros pares
            }
        }
        // exibe os resultados 
        System.out.println("Soma dos numeros Ã­mpares entre 0 e 30: " + somaimpar);
        System.out.println("Multiplicao dos numeros pares entre 0 e 30: " + multpar);
    }
}
