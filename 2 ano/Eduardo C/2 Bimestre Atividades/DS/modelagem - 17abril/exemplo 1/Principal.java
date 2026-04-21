import javax.swing.*;

public class Principal {
    public static void main(String[] args) {
        caixa cx1 = new caixa(); // Instancia caixa
        int op; // Para criar um número para o switch case
        
        do { // Início do loop
            op = Integer.parseInt(JOptionPane.showInputDialog("Digite: \n1 - Entrada" + "\n2 - Retirar" + "\n3 - Status" + "\n4 - Sair"));
            
            // Menu de exibição
            switch (op) {
                case 1: 
                    cx1.entrar(); // Método entrar - modelagem
                    break; // Quebra
                
                case 2:
                    cx1.retirar(); // Método retirar - modelagem
                    break;
                
                case 3:
                    // Mostra o saldo
                    JOptionPane.showMessageDialog(null, "Seu saldo é de: " + cx1.getSaldo());
                    break;
                
                case 4:
                    JOptionPane.showMessageDialog(null, "Programa finalizado com sucesso! Adeus!");
                    break;
                
                default:
                    JOptionPane.showMessageDialog(null, "Número inválido, tente novamente!");
            }
        } while (op != 4); // A condição de parada para o loop
        
    }
}
