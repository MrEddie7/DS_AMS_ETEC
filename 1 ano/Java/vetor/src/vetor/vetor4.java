
package vetor;
import javax.swing.JOptionPane;
import java.util.Arrays;

public class vetor4 {
    
    public static void main(String[] args) {
        
         int [] numeros = new int[50];
    
    for (int i = 0; i<50;i++){
        
        String input = JOptionPane.showInputDialog("digite o numero "+(i+1)+":");
        
        numeros[i]= Integer.parseInt(input);
        }
    
    Arrays.sort(numeros);
    for (int i = 0; i<numeros.length/ 2;i++){
    
    int temp = numeros[i];
    numeros[i]= numeros[numeros.length-1 -i];
    }
    JOptionPane.showMessageDialog(null, "Numeros em ordem descrecente:\n"+Arrays.toString(numeros));
    
    }
    }
    

