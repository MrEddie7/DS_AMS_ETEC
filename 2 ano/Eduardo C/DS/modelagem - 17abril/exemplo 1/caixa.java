import javax.swing.*;
public class caixa {
 
    // atributo
    private double saldo; 
    
    // contrutores
    // atributo zerado
    
    public caixa(){
    
        this(0);
    
    }
    // atribotu com parametro
    
    public caixa(double saldo){
    
    this.saldo = saldo;
        
    }
    
    // get saldo
    
    public double getSaldo(){
    
    return saldo;
        
    }
    
    // set saldo
    
    public void setSaldo(double saldo){
    
    this.saldo = saldo;
    
    }
    
    // metodos da classe
    
    // metodo para inserir algo no saldo
    
    public void entrar(){
    
    double valor = Double.parseDouble(JOptionPane.showInputDialog("insira o avalor a ser inserido no seu saldo: "));
    
    this.setSaldo(this.saldo + valor);
        
    }
    
    // metodo de retirar um valor
    
    public void retirar(){
    
        double valor = Double.parseDouble(JOptionPane.showInputDialog("Declare o valor de retirada "));
        
        this.setSaldo(this.saldo - valor);
    
    } 
    
    
    
    
}
