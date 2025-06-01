import java.util.Scanner;
public class media {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("insira o nome do aluno: ");
        String nome = scanner.nextLine();
        
        // notas
        
        System.out.print("insira a primeira nota: ");
        int n1 = scanner.nextInt();
        
         System.out.print("insira a segunda nota: ");
        int n2 = scanner.nextInt();
         
        System.out.print("insira a terceira nota: ");
        int n3 = scanner.nextInt();
        
        System.out.print("insira a quarta nota: ");
        int n4 = scanner.nextInt();
        
        // calcula
        
        int nota = (n1 + n2 + n3 + n4)/4;
        
        // apresenta a nota
        
        System.out.println("O aluno: "+nome+" tem a seguinte nota: "+nota);
        
        scanner.close();
        
        
    }
}
