/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Scanner;
/**
 *
 * @author FATEC ZONA LESTE
 */
public class exemploDois {
    
    
     
    public static void main(String[] args) {
        Scanner ler=new Scanner(System.in);
    
       
        System.out.println("o primeiro valor é: ");
        int a=ler.nextInt();
        
        System.out.println("o segundo valor é:");
        int b = ler.nextInt();
        
        float s=a+b;
        float c=a*b;
        float d=a/b;
        float e=a-b;
        
        System.out.println("o resultado sera para adição: "+s+" para multiplicação: "+c+" para divisão: "+d+" e para subtração: "+e);
        
     }    
    
}
