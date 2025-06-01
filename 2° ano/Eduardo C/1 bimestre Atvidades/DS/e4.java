import javax.swing.JOptionPane;

public class e4 {
    public static void main(String[] args) {
        // Receber dois números
        int numA = Integer.parseInt(JOptionPane.showInputDialog("Digite o valor de NumA:"));
        int numB = Integer.parseInt(JOptionPane.showInputDialog("Digite o valor de NumB:"));

        // Exibir valores antes da troca
        String resultado = "Antes da troca:\nNumA = " + numA + "\nNumB = " + numB;

        // Trocar valores usando uma variavel Troca
        int troca = numA;
        numA = numB;
        numB = troca;

        // Exibir valores p�s a troca
        resultado += "\n\nApós a troca:\nNumA = " + numA + "\nNumB = " + numB;

        // Exibir o resultado
        JOptionPane.showMessageDialog(null, resultado);
    }
}