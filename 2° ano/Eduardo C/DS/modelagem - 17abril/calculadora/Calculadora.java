import javax.swing.*;

public class Calculadora {

    private double n1;
    private double n2;
    private double r;

    // Construtor padrão
    public Calculadora() {
        this(0, 0, 0);
    }

    // Construtor com parâmetros
    public Calculadora(double n1, double n2, double r) {
        this.n1 = n1;
        this.n2 = n2;
        this.r = r;
    }

    // Getters e Setters
    public double getN1() {
        return n1;
    }

    public void setN1(double n1) {
        this.n1 = n1;
    }

    public double getN2() {
        return n2;
    }

    public void setN2(double n2) {
        this.n2 = n2;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    // Método somar
    public void somar() {
        this.setN1(Double.parseDouble(JOptionPane.showInputDialog("Digite o primeiro número para soma:")));
        this.setN2(Double.parseDouble(JOptionPane.showInputDialog("Digite o segundo número para soma:")));
        this.setR(this.getN1() + this.getN2());
        JOptionPane.showMessageDialog(null, "Resultado da soma: " + this.getR(), "Resultado", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método subtrair
    public void subtrair(double a, double b)
    {
        this.setN1(a);
        this.setN2(b);
        this.setR(this.getN1() - this.getN2());
        JOptionPane.showMessageDialog(null, "Resultado da subtração: " + this.getR(), "Resultado", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método multiplicar
    public double multiplicar() {
        this.setN1(Double.parseDouble(JOptionPane.showInputDialog("Digite o primeiro número para multiplicação:")));
        this.setN2(Double.parseDouble(JOptionPane.showInputDialog("Digite o segundo número para multiplicação:")));
        this.setR(this.getN1() * this.getN2());
        
        return this.getR();
    }

    // Método dividir
    public double dividir(double a, double b)
    {
        this.setN1(a);
        this.setN2(b);
        if (this.getN2() != 0) {
            this.setR(this.getN1() / this.getN2());
        } else {
            JOptionPane.showMessageDialog(null, "Erro: Divisão por zero não permitida!", "Erro", JOptionPane.ERROR_MESSAGE);
            this.setR(0);
        }
        
        return this.getR();
    }
}
