import javax.swing.JOptionPane;

public class e1{
    public static void main(String[] args) {
        // Receber os numeros
        int num1 = Integer.parseInt(JOptionPane.showInputDialog("Digite o primeiro numero:"));
        int num2 = Integer.parseInt(JOptionPane.showInputDialog("Digite o segundo numero:"));
        int num3 = Integer.parseInt(JOptionPane.showInputDialog("Digite o terceiro numero:"));

        // Organizar em ordem decrescente
        String resultado;
        if (num1 >= num2 && num1 >= num3) {
            if (num2 >= num3) {
                resultado = num1 + " " + num2 + " " + num3;
            } else {
                resultado = num1 + " " + num3 + " " + num2;
            }
        } else if (num2 >= num1 && num2 >= num3) {
            if (num1 >= num3) {
                resultado = num2 + " " + num1 + " " + num3;
            } else {
                resultado = num2 + " " + num3 + " " + num1;
            }
        } else {
            if (num1 >= num2) {
                resultado = num3 + " " + num1 + " " + num2;
            } else {
                resultado = num3 + " " + num2 + " " + num1;
            }
        }

        // Exibir resultado
        JOptionPane.showMessageDialog(null, "Numeros em ordem decrescente: " + resultado);
    }
}