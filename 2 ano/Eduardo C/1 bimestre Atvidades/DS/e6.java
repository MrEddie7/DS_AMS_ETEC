import javax.swing.JOptionPane;

public class e6 {
    public static void main(String[] args) {
        // Receber o nome e as quatro notas
        String nome = JOptionPane.showInputDialog("Digite o nome do aluno:");
        double nota1 = Double.parseDouble(JOptionPane.showInputDialog("Digite a primeira nota:"));
        double nota2 = Double.parseDouble(JOptionPane.showInputDialog("Digite a segunda nota:"));
        double nota3 = Double.parseDouble(JOptionPane.showInputDialog("Digite a terceira nota:"));
        double nota4 = Double.parseDouble(JOptionPane.showInputDialog("Digite a quarta nota:"));

        // Calcular a media
        double media = (nota1 + nota2 + nota3 + nota4) / 4;

        // Exibir a media
        String resultado = "A media do aluno " + nome + " é: " + media;
        JOptionPane.showMessageDialog(null, resultado);
    }
}