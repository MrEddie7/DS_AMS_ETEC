import javax.swing.JOptionPane;

public class e5 {
    public static void main(String[] args) {
        // Receber dois números
        int num1 = Integer.parseInt(JOptionPane.showInputDialog("Digite o primeiro número:"));
        int num2 = Integer.parseInt(JOptionPane.showInputDialog("Digite o segundo número:"));

        // Preparar resultado
        String resultado = "";
        if (num1 == num2) {
            resultado = "Os números são iguais.";
        } else {
            resultado = "Os números são diferentes.\n";
            if (num1 > num2) {
                resultado += "Maior: " + num1 + "\nMenor: " + num2;
            } else {
                resultado += "Maior: " + num2 + "\nMenor: " + num1;
            }
        }

        // Exibir resultado
        JOptionPane.showMessageDialog(null, resultado);
    }
}
