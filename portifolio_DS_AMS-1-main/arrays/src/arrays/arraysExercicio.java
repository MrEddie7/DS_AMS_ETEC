import java.util.Arrays;

public class arraysExercicio {
    public static void main(String[] args) {
        // 1. Array armazenando 10 nomes em ordem crescente
        String[] nomesCrescentes = {
            "Alice", "Bruno", "Carla", "Daniel", "Eduarda", 
            "Felipe", "Gustavo", "Heloísa", "Igor", "Juliana"
        };

        // 2. Array armazenando 20 nomes em ordem decrescente
        String[] nomesDecrescentes = {
            "Victor", "Tânia", "Sérgio", "Quico", "Paula", 
            "Olavo", "Nina", "Marta", "Leonardo", "Juliana",
            "Igor", "Heloísa", "Gustavo", "Felipe", "Eduarda", 
            "Daniel", "Carla", "Bruno", "Alice", "André"
        };

        // 3. Array armazenando 50 números em ordem crescente
        int[] numerosCrescentes = new int[50];
        for (int i = 0; i < 50; i++) {
            numerosCrescentes[i] = i + 1;
        }

        // 4. Array armazenando 50 números em ordem decrescente
        int[] numerosDecrescentes = new int[50];
        for (int i = 0; i < 50; i++) {
            numerosDecrescentes[i] = 50 - i;
        }

        // 5. Array armazenando 100 números pares
        int[] numerosPares = new int[100];
        for (int i = 0; i < 100; i++) {
            numerosPares[i] = i * 2;
        }

        // 6. Array armazenando 100 números ímpares
        int[] numerosImpares = new int[100];
        for (int i = 0; i < 100; i++) {
            numerosImpares[i] = i * 2 + 1;
        }

        // 7. Array armazenando a tabela de classificação do campeonato brasileiro atual (exemplo fictício)
        String[][] classificacaoBrasileirao = {
            {"Botafogo", "68"},
            {"Palmeiras", "65"},
            {"Flamengo", "61"},
            {"São Paulo", "58"},
            {"Corinthians", "57"},
            {"Atlético-MG", "56"},
            {"Internacional", "55"},
            {"Grêmio", "54"},
            {"Santos", "52"},
            {"Athletico-PR", "50"}
        };

        // Exibindo os resultados
        System.out.println("Nomes em ordem crescente: " + Arrays.toString(nomesCrescentes));
        System.out.println("Nomes em ordem decrescente: " + Arrays.toString(nomesDecrescentes));
        System.out.println("Números crescentes: " + Arrays.toString(numerosCrescentes));
        System.out.println("Números decrescentes: " + Arrays.toString(numerosDecrescentes));
        System.out.println("Números pares: " + Arrays.toString(numerosPares));
        System.out.println("Números ímpares: " + Arrays.toString(numerosImpares));
        System.out.println("Classificação do Campeonato Brasileiro: ");
        for (String[] time : classificacaoBrasileirao) {
            System.out.println(time[0] + ": " + time[1] + " pontos");
        }
    }
}

