import javax.swing.*;

public class Principal {
    public static void main(String[] args) {
        Calculadora calc1 = new Calculadora(); // Instancia calculadora
        int op;

        do {
            op = Integer.parseInt(JOptionPane.showInputDialog(
                "Digite: \n1 - Somar" + 
                "\n2 - Subtrair" + 
                "\n3 - Dividir" + 
                "\n4 - Multiplicar" + 
                "\n5 - Sair"));

            switch (op) {
                case 1: 
                    calc1.somar(); // Método soma com entrada via JOptionPane
                    break;

                case 2:
                    double sub1 = Double.parseDouble(JOptionPane.showInputDialog("Digite o primeiro número para subtração:"));
                    double sub2 = Double.parseDouble(JOptionPane.showInputDialog("Digite o segundo número para subtração:"));
                    calc1.subtrair(sub1, sub2);
                   
                    break;

                case 3:
                    double div1 = Double.parseDouble(JOptionPane.showInputDialog("Digite o primeiro número para divisão:"));
                    double div2 = Double.parseDouble(JOptionPane.showInputDialog("Digite o segundo número para divisão:"));
                    calc1.dividir(div1, div2);
                    JOptionPane.showMessageDialog(null, "Resultado da divisão: " + calc1.getR());
                    break;

                case 4:
                    calc1.multiplicar();
                    JOptionPane.showMessageDialog(null, "Resultado da multiplicação: " + calc1.getR());
                    break;

                case 5:
                    JOptionPane.showMessageDialog(null, "Programa finalizado");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida, tente novamente!");
            }
        } while (op != 5);
    }
}

