import java.util.Scanner;
public class TrocaNumero {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // inserir os dados 
        System.out.print("digite o valor de numA: ");
        int numA = scanner.nextInt();
        
        System.out.print("digite o valor de numB: ");
        int numB = scanner.nextInt();
        
        // exibe antes da troca 
        System.out.println("Antes da troca: ");
        System.out.println("valor de numA: "+ numA);
        System.out.println("valor de numB: "+ numB);
        
        // operação de troca
        
        int troca =  numA;
        numA = numB;
        numB = troca;
        
        // exibe os valore pos troca
        
        System.out.println("valores pos troca: ");
        System.out.println("novo valor de numA: "+ numA);
        System.out.println("novo valor de numB: "+ numB);
        
        scanner.close();
    }
}
