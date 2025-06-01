import java.util.Scanner;

public class exercicio4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o valor inicial: ");
        int inicio = scanner.nextInt();
        System.out.print("Digite o valor final: ");
        int fim = scanner.nextInt();
        
        int soma = 0;
        for (int i = inicio; i <= fim; i++) {
            if (i % 2 != 0) {
                soma += i;
            }
        }
        System.out.println("A soma dos números ímpares entre " + inicio + " e " + fim + " é " + soma);
        scanner.close();
    }
}
