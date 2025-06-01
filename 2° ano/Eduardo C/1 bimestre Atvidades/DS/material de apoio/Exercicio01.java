package com.eteczonaleste.exerciciosswitch;
import javax.swing.*;
public class Exercicio01 {
    public static void main(String[] args) {
        int tot;
        int n1= Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o primeiro número: "));
        int n2= Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o segundo número: "));
        int op = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a operação (1-+, 2- -, 3-x, 4- /):"));
        switch (op){
            case 1:
                tot = n1 + n2;
                JOptionPane.showMessageDialog(null, "O valor da soma é: " + tot);
                break;
            case 2:
                tot = n1 - n2;
                JOptionPane.showMessageDialog(null, "O valor da subtração é: "+ tot);
                break;
            case 3:
                tot = n1 * n2;
                JOptionPane.showMessageDialog(null, "O valor da multiplicação é: "+ tot);
                break;
            case 4:
                tot = n1 / n2;
                JOptionPane.showMessageDialog(null, "O valor da divisão é: "+ tot);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Esse número não consta na lista!", "Argumento inválido", JOptionPane.ERROR_MESSAGE);
        }
    }
  
}
