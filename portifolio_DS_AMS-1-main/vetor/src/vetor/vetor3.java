/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vetor;

import javax.swing.JOptionPane;
import java.util.Arrays;

public class vetor3 {
    
    public static void main(String[] args) {
    
    int [] numeros = new int[50];
    
    for (int i = 0; i<50;i++){
        
        String input = JOptionPane.showInputDialog("digite o numero "+(i+1)+":");
        
        numeros[i]= Integer.parseInt(input);
        }
    
    Arrays.sort(numeros);
    JOptionPane.showMessageDialog(null, "Numeros em ordem crescente:\n"+Arrays.toString(numeros));
    
    }

    
    
}
        
        
    
    
    
    

