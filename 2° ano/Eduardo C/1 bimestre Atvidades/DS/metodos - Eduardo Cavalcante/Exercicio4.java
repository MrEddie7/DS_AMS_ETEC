package com.mycompany.metodos;

import javax.swing.*;

public class Exercicio4 {
    public static double calcularOperacao(int opcao, double num1, double num2) {
        switch (opcao) {
            case 1:
                return num1 + num2;
            case 2:
                return num1 - num2;
            case 3:
                return num1 / num2;
            case 4:
                return num1 * num2;
            case 5:
                return num1 % num2;
            case 6:
                return num1 * 2;
            case 7:
                return num1 * num1;
            case 8:
                return num1 * num1 * num1;
            case 9:
                return Math.sqrt(num1);
            default:
                return -1; // Caso a opção seja inválida
        }
    }

    public static void main(String[] args) {
        while (true) {
            String menu = "Menu de operações:\n"
                        + "1 - Soma\n"
                        + "2 - Subtração\n"
                        + "3 - Divisão\n"
                        + "4 - Multiplicação\n"
                        + "5 - Resto da Divisão\n"
                        + "6 - Dobro\n"
                        + "7 - Quadrado\n"
                        + "8 - Cubo\n"
                        + "9 - Raiz Quadrada\n"
                        + "0 - Sair";
            int opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
            
            if (opcao == 0) {
                break;
            }
            
            double num1 = Double.parseDouble(JOptionPane.showInputDialog("Digite o primeiro número:"));
            
            double num2 = 0;
            if (opcao >= 1 && opcao <= 5) {
                num2 = Double.parseDouble(JOptionPane.showInputDialog("Digite o segundo número:"));
            }
            
            double resultado = calcularOperacao(opcao, num1, num2);
            
            if (resultado == -1) {
                JOptionPane.showMessageDialog(null, "Opção inválida!");
            } else {
                JOptionPane.showMessageDialog(null, "Resultado: " + resultado);
            }
        }
    }
}

