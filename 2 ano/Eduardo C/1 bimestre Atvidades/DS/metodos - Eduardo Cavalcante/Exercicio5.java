package com.mycompany.metodos;

import javax.swing.*;
import java.lang.Math;

public class Exercicio5 {
    public static double hipotenusa(double base, double altura) {
        // Função para calcular a hipotenusa
        return Math.sqrt(base * base + altura * altura);
    }

    public static void main(String[] args) {
        double base = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da base do triângulo:"));
        double altura = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da altura do triângulo:"));
        
        // Cálculo da hipotenusa
        double resultado = hipotenusa(base, altura);
        JOptionPane.showMessageDialog(null, "A hipotenusa do triângulo é: " + resultado);
    }
}
