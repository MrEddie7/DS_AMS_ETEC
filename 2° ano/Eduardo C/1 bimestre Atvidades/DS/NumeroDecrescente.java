import java.util.Scanner;

public class NumeroDecrescente {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Receber o numero
        System.out.print("Digite um numero inteiro: ");
        int num = scanner.nextInt();

        // Imprimir de forma decrescente 
        for (int i = num; i >= 0; i--) {
            System.out.print(i + " ");
        }

        scanner.close();
    }
}