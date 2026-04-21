package atv;

import java.util.Scanner;

public class Acesso {
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        aviso("Aguarde as perguntas e responda com sim ou não, ok?", 5000);
	        System.out.println();
	        
	        System.out.println("O Santos vai ganhar o Brasileirão ano que vem?: (sim ou não) ");
	        boolean santosmaiordobr = convertToBoolean(scanner.next());
	        
	        System.out.println("Marlon para presidente?: (sim ou não): ");
	        boolean mpresi = convertToBoolean(scanner.next());
	        
	        System.out.println("O 2° AMS é a melhor sala? (sim ou não): ");
	        boolean dsams = convertToBoolean(scanner.next());
	        
	        System.out.println("Vamos ganhar o interclasse no fim do ano?: (sim ou não): ");
	        boolean autor = convertToBoolean(scanner.next());
	        
	        System.out.println("O professor Marcelo merece um aumento?: (sim ou não): ");
	        boolean profm = convertToBoolean(scanner.next());
	        
	        boolean acessop = (autor && santosmaiordobr) ||
	                          (mpresi && profm && autor) ||
	                          (dsams && (santosmaiordobr || profm));

	        if(acessop) {
	            System.out.println("Acesso permitido");
	        } else {
	            System.out.println("Acesso negado");
	        }

	        scanner.close();
	       
	        }

		private static boolean convertToBoolean(String input) {
			return input.equalsIgnoreCase("sim");
		}

		public static void aviso(String message, int durationMillis) {
	        System.out.println(message);
	        try {
	            Thread.sleep(durationMillis);
	        } catch (InterruptedException e) {
	            System.err.println("Ocorreu um erro " + e.getMessage());
	}
		}
}
