/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vetor;
import javax.swing.JOptionPane;


public class Vetor {

    public static void main(String[] args) {
        
        String[]nomes = new String[16];
        
        int i;
        
        for (i=0;i<15;i++){
        
        nomes[i]=JOptionPane.showInputDialog("Digite até 15 nomes"+(i+1));
        }
        
        for (i=0;i<15;i++);{
    
    System.out.println("nomes:"+String.join(",", nomes));
    }
      
    }
    
}
