package com.mycompany.metodos;

import javax.swing.*;

public class Exercicio3 {
    public static double calcularPesoIdeal(String sexo, double altura) {
        // Função para calcular o peso ideal com base no sexo e altura
        if (sexo.equalsIgnoreCase("masculino")) {
            return (72.7 * altura) - 58;
        } else if (sexo.equalsIgnoreCase("feminino")) {
            return (62.1 * altura) - 44.7;
        } else {
            return -1; // Indica erro no sexo informado
        }
    }

    public static void main(String[] args) {
        String sexo = JOptionPane.showInputDialog("Digite o sexo (masculino/feminino):");
        double altura = Double.parseDouble(JOptionPane.showInputDialog("Digite a altura em metros:"));
        
        // Cálculo do peso ideal
        double pesoIdeal = calcularPesoIdeal(sexo, altura);
        
        if (pesoIdeal == -1) {
            JOptionPane.showMessageDialog(null, "Sexo inválido!");
        } else {
            JOptionPane.showMessageDialog(null, "O peso ideal para " + sexo + " de altura " + altura + "m é: " + pesoIdeal + "kg");
        }
    }
}

