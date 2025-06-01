import java.util.Scanner;

public class ComparaNumero {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Digite o primeiro numero: ");
        int n1 = scanner.nextInt();
        
        System.out.print("digite o segundo numero: ");
        int n2 = scanner.nextInt();
        
        //comparar
        
        if (n1 == n2){
            System.out.println("os numeros são iguais");
        } else {
            System.out.println("os numeros são diferentes");
            if (n1 > n2){
                System.out.println("Numero maior: "+ n1);
                System.out.println("Numero menor: "+ n2);
            } else {
                System.out.println("Numero maior: "+ n2);
                System.out.println("Numero menor: "+ n1);
            }
        }
        
        scanner.close(); 
    }
    
}
