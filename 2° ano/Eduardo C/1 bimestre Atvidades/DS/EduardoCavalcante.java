
package eduardocavalcante;
import javax.swing.*;

public class EduardoCavalcante {

 
    public static void main(String[] args) {
        double soma1 = 0;
        double soma2 = 0;
      double valor1 = Double.parseDouble(JOptionPane.showInputDialog("Digite o primeiro preço:"));
      double valor2 = Double.parseDouble(JOptionPane.showInputDialog("Digite o segundo preço:"));
      double valor3 = Double.parseDouble(JOptionPane.showInputDialog("Digite o terceiro preço:"));
      double valor4 = Double.parseDouble(JOptionPane.showInputDialog("Digite o quarto preço:"));
      
     double media = media(valor1,valor2,valor3,valor4);
     double maior = media(valor1,valor2,valor3,valor4);
     double menor = media(valor1,valor2,valor3,valor4);
     
     JOptionPane.showMessageDialog(null,"De acordo com os preços dados este é maior: " + maior+ "este é menor: "+menor+" e esta é a média: "+media);
      
      }
        
         public static double media(double valor1, double valor2, double valor3, double valor4) {
        
             double media = (valor1+ valor2+valor3+valor4)/4;
             return media;
        }
      
        public static double maior(double valor1, double valor2, double valor3, double valor4, double soma1, double soma2) { 
       
         if (valor1 == valor2 && valor3 == valor4 || valor1 == valor3 && valor2 == valor4 || valor1 == valor4 && valor3 == valor2 ){
              JOptionPane.showMessageDialog(null, "valores identicos " ); 
        
      } else if (valor1 > valor2 && valor3 > valor4 || valor1 > valor3 && valor2 > valor4 || valor1 > valor4 && valor3 > valor2){}
         
          
         
         }
        public static double menor(double valor1, double valor2, double valor3, double valor4, double soma1, double soma2) {
        
        if (valor1 < valor2 && valor3 < valor4 || valor1 < valor3 && valor2 < valor4 || valor1 < valor4 && valor3 < valor2){}
        
        }
        
        }
        
        
        
        
        
         
        

              
        
         
        
        
        
        
        


   