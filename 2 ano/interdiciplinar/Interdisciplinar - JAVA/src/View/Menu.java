package View;
import javax.swing.*;
import java.awt.*;
public class Menu extends JFrame {
    public Menu() {
        setTitle("Sistema de Cinema");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        JMenuBar menuBar = new JMenuBar();
        JMenu menuCadastro = new JMenu("Cadastro");
        JMenuItem cadastrarSala = new JMenuItem("Cadastrar Sala");
        cadastrarSala.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Abrir tela de cadastro de Sala");
            new frm_sala().setVisible(true);
        });
        menuCadastro.add(cadastrarSala);
        JMenuItem cadastrarFilme = new JMenuItem("Cadastrar Filme");
        cadastrarFilme.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Abrir tela de cadastro de Filme");
        });
        menuCadastro.add(cadastrarFilme);
        JMenuItem cadastrarUsuario = new JMenuItem("Cadastrar Usuário");
        cadastrarUsuario.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Abrir tela de cadastro de Usuário");
        });
        menuCadastro.add(cadastrarUsuario);
        menuBar.add(menuCadastro);
        setJMenuBar(menuBar);
    }
    private void addExitButton(JToolBar toolBar) {
        JButton exitButton = new JButton("Sair");
        exitButton.addActionListener(e -> System.exit(0));
        toolBar.add(exitButton);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Menu().setVisible(true));
    }
}