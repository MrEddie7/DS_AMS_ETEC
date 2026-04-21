import javax.swing.*;
public class Principal {
    
    public static void main(String[] args) {
        // Instância da classe Objetos
        Objetos objeto1 = new Objetos();  // Certifique-se de que a classe 'Objetos' está definida corretamente

        // Preenchendo os dados do objeto utilizando o método receberDados
        objeto1.inserirDados();  // Assumindo que você já tenha implementado o método receberDados na classe Objetos
        
        // Exibindo os dados através do JOptionPane.showMessageDialog
        objeto1.apresentarDados();

        // Usando o objeto para chamar o método verificarExistencia
        objeto1.verificarExistencia();
    }
}
