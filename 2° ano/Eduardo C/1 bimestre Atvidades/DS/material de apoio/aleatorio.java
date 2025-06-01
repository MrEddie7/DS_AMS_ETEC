import java.util.Scanner;

public class aleatorio {
    public static void main(String[] args) {
        Scanner receba = new Scanner(System.in);
        
        System.out.print("Digite sua idade: ");
        int idade = receba.nextInt();

        System.out.print("Digite sua nota no vestibular (0 a 1000): ");
        int nota = receba.nextInt();
        
        System.out.print("Você tem uma bolsa de estudos? (sim/não): ");
        String apodreca = receba.next();
        
        boolean bolsa = apodreca.equalsIgnoreCase("sim");
        
        boolean idAceitavel = idade >= 18;
        boolean notaCorte = nota >= 700;
        boolean entremeufio = idAceitavel && (notaCorte || bolsa);
        boolean vaza = !entremeufio;

        System.out.println("Idade aceitável: " + idAceitavel);
        System.out.println("Nota do vestibular aceita: " + notaCorte);
        System.out.println("Possui bolsa de estudos? " + bolsa);
        System.out.println("Possibilitado a entrar na faculdade: " + entremeufio);

        if (vaza) {
            System.out.println("Infelizmente, você não pode entrar na faculdade.");
        }
    }
}