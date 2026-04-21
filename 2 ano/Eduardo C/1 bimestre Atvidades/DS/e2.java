import javax.swing.JOptionPane;

public class e2 {
    public static void main(String[] args) {
        // Receber o numero
        int num = Integer.parseInt(JOptionPane.showInputDialog("Digite um numero inteiro:"));

        // Preparar a mensagem
        StringBuilder resultado = new StringBuilder();
        for (int i = num; i >= 0; i--) {
            resultado.append(i).append(" ");
        }

        // Exibir o resultado
        JOptionPane.showMessageDialog(null, "Contagem decrescente: " + resultado.toString());
    }
}