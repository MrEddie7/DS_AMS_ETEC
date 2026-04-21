import javax.swing.*;
import java.awt.*;

public class Janela extends JFrame {
    public Janela() {
        Container tela = getContentPane();
        tela.setBackground(Color.DARK_GRAY);
        setLayout(null);

        // Rótulos
        JLabel rotulo1 = new JLabel("Cartão de visita");
        JLabel rotulo2 = new JLabel("Desenvolvido por Eduardo Cavalcante 2ºAMS");
        rotulo1.setForeground(Color.WHITE);
        rotulo2.setForeground(Color.WHITE);
        rotulo1.setBounds(150, 20, 200, 30);
        rotulo2.setBounds(100, 450, 300, 30);

        // Carregando e redimensionando a imagem
        ImageIcon iconeOriginal = new ImageIcon("src/img.jpeg");
        Image imagemRedimensionada = iconeOriginal.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
        ImageIcon iconeRedimensionado = new ImageIcon(imagemRedimensionada);
        JLabel imagem = new JLabel(iconeRedimensionado);
        imagem.setBounds(150, 100, 200, 300);

        // Adicionando componentes
        tela.add(rotulo1);
        tela.add(rotulo2);
        tela.add(imagem);

        // Configuração da janela
        setSize(500, 660);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    
    
}