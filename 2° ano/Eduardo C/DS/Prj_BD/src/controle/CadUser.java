/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import conexao.Conexao;

/**
 *
 * @author dti
 */
public class CadUser extends JFrame {
    Conexao con_cliente;
    JTextField tUsuario;
    JPasswordField tSenha;
    JButton bCadastrar;

    public CadUser() {
        con_cliente = new Conexao();
        con_cliente.conectar();

        setTitle("Cadastro de Usuário");
        setSize(320, 240);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        Container tela = getContentPane();
        setLayout(null);
        tela.setBackground(new Color(230, 240, 255)); // cor suave de fundo

        JLabel lTitulo = new JLabel("Cadastro de Usuário", SwingConstants.CENTER);
        lTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lTitulo.setForeground(new Color(0, 70, 140));
        lTitulo.setBounds(30, 15, 250, 35);

        JLabel lUsuario = new JLabel("Usuário:");
        lUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        lUsuario.setForeground(new Color(40, 40, 40));
        lUsuario.setBounds(40, 65, 80, 25);

        tUsuario = new JTextField();
        tUsuario.setBackground(new Color(245, 250, 255));
        tUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        tUsuario.setBorder(BorderFactory.createLineBorder(new Color(180, 200, 230), 1));
        tUsuario.setBounds(120, 65, 140, 25);

        JLabel lSenha = new JLabel("Senha:");
        lSenha.setFont(new Font("Arial", Font.PLAIN, 14));
        lSenha.setForeground(new Color(40, 40, 40));
        lSenha.setBounds(40, 105, 80, 25);

        tSenha = new JPasswordField();
        tSenha.setBackground(new Color(245, 250, 255));
        tSenha.setFont(new Font("Arial", Font.PLAIN, 14));
        tSenha.setBorder(BorderFactory.createLineBorder(new Color(180, 200, 230), 1));
        tSenha.setBounds(120, 105, 140, 25);

        bCadastrar = new JButton("Cadastrar");
        bCadastrar.setBackground(new Color(0, 120, 215));
        bCadastrar.setForeground(Color.WHITE);
        bCadastrar.setFont(new Font("Arial", Font.BOLD, 14));
        bCadastrar.setFocusPainted(false);
        bCadastrar.setBorder(BorderFactory.createLineBorder(new Color(0, 70, 140), 2));
        bCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bCadastrar.setBounds(90, 155, 120, 35);

        tela.add(lTitulo);
        tela.add(lUsuario);
        tela.add(tUsuario);
        tela.add(lSenha);
        tela.add(tSenha);
        tela.add(bCadastrar);

        bCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = tUsuario.getText();
                String senha = new String(tSenha.getPassword());
                if (usuario.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos.");
                    return;
                }
                try {
                    String sql = "INSERT INTO tblusuario (usuario, senha) VALUES (?, ?)";
                    try (java.sql.PreparedStatement pst = con_cliente.conexao.prepareStatement(sql)) {
                        pst.setString(1, usuario);
                        pst.setString(2, senha);
                        pst.executeUpdate();
                    }
                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + ex.getMessage());
                }
            }
        });
    }
}
