package com.mycompany.metodos;

import javax.swing.*;

public class Exercicio1 {
    public static double lerNota() {
        // Função para ler uma nota
        String input = JOptionPane.showInputDialog("Digite a nota:");
        return Double.parseDouble(input);
    }

    public static double calcularMedia(double nota1, double nota2) {
        // Função para calcular a média
        return (nota1 + nota2) / 2;
    }

    public static void main(String[] args) {
        String nome = JOptionPane.showInputDialog("Digite o nome do aluno:");
        
        // Leitura das notas
        double nota1 = lerNota();
        double nota2 = lerNota();
        
        // Cálculo da média
        double media = calcularMedia(nota1, nota2);
        
        // Verificação da aprovação
        String status = (media >= 7.0) ? "Aprovado" : "Reprovado";
        
        JOptionPane.showMessageDialog(null,"Aluno:"+ nome + "Média: " + media + "\nStatus: " + status);
    }
}
