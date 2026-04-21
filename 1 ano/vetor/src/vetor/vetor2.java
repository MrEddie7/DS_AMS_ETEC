
package vetor;
import javax.swing.JOptionPane;
import java.util.Arrays;

public class vetor2 {
    
    public static void main(String[] args) {
        
        String[] nomes = new String[15];
       int i;
        
        for (i=0;i<15;i++){
            
            nomes[i]=JOptionPane.showInputDialog("Digite até 15 nomes"+(i+1));
        
        }
        
        for (i=0;i<15;i++){
           
            Arrays.sort(nomes, (a,b)->b.compareTo(a));
           JOptionPane.showInputDialog(null,"eis os nomes"+String.join(",", nomes));
            
        }
        }
    
}
