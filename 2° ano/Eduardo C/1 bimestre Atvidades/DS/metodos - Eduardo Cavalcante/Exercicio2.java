package com.mycompany.metodos;

import javax.swing.*;

public class Exercicio2 {
    public static double diferenca(double valor1, double valor2) {
        // Função para calcular a diferença entre o maior e o menor valor
        return Math.abs(valor1 - valor2);
    }

    public static void main(String[] args) {
        double valor1 = Double.parseDouble(JOptionPane.showInputDialog("Digite o primeiro valor:"));
        double valor2 = Double.parseDouble(JOptionPane.showInputDialog("Digite o segundo valor:"));
        
        // Exibição da diferença
        JOptionPane.showMessageDialog(null, "A diferença entre os valores é: " + diferenca(valor1, valor2));
    }
}

