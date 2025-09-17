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

        // --- Barra de Menu ---
        JMenuBar menuBar = new JMenuBar();

        // Menu Cadastro
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
            // new frm_filme().setVisible(true);
        });
        menuCadastro.add(cadastrarFilme);

        JMenuItem cadastrarUsuario = new JMenuItem("Cadastrar Usuário");
        cadastrarUsuario.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Abrir tela de cadastro de Usuário");
            // new frm_usuario().setVisible(true);
        });
        menuCadastro.add(cadastrarUsuario);

        // Adiciona o menu "Cadastro" na barra
        menuBar.add(menuCadastro);

        // Define a barra de menus no JFrame
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
