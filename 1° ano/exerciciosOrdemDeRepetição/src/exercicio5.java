import java.util.Scanner;

public class exercicio5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o valor inicial: ");
        int inicio = scanner.nextInt();
        System.out.print("Digite o valor final: ");
        int fim = scanner.nextInt();
        
        for (int i = fim; i >= inicio; i--) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
        scanner.close();
    }
}
