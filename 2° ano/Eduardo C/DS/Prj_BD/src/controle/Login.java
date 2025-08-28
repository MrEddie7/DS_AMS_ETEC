package controle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import conexao.Conexao;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.sql.*;

public class Login extends JFrame {
    
    Conexao con_cliente;
    
    JPasswordField tsen;
    JLabel rusu,rsen,rtit;
    JTextField tusu;
    JButton blogar;
    
    public Login(){
    
    con_cliente = new Conexao();
    con_cliente.conectar();
    
    setTitle("*** Login de Acesso ***");
    Container tela = getContentPane();
    setLayout(null);
    tela.setBackground(new Color(230, 240, 255)); // cor suave de fundo
    rtit = new JLabel("Acesso Ao Sistema", SwingConstants.CENTER);
    rtit.setFont(new Font("Arial", Font.BOLD, 18));
    rtit.setForeground(new Color(0, 70, 140));
    rusu = new JLabel("Usuario: ");
    rusu.setFont(new Font("Arial", Font.PLAIN, 14));
    rusu.setForeground(new Color(40, 40, 40));
    rsen = new JLabel ("Senha: ");
    rsen.setFont(new Font("Arial", Font.PLAIN, 14));
    rsen.setForeground(new Color(40, 40, 40));

    tusu = new JTextField();
    tusu.setBackground(new Color(245, 250, 255));
    tusu.setFont(new Font("Arial", Font.PLAIN, 14));
    tusu.setBorder(BorderFactory.createLineBorder(new Color(180, 200, 230), 1));
    tsen = new JPasswordField();
    tsen.setBackground(new Color(245, 250, 255));
    tsen.setFont(new Font("Arial", Font.PLAIN, 14));
    tsen.setBorder(BorderFactory.createLineBorder(new Color(180, 200, 230), 1));
    blogar = new JButton("Entrar");
    blogar.setBackground(new Color(0, 120, 215));
    blogar.setForeground(Color.WHITE);
    blogar.setFont(new Font("Arial", Font.BOLD, 14));
    blogar.setFocusPainted(false);
    blogar.setBorder(BorderFactory.createLineBorder(new Color(0, 70, 140), 2));
    blogar.setCursor(new Cursor(Cursor.HAND_CURSOR));

    // Posicionamento dos componentes
    rtit.setBounds(30, 15, 250, 35);
    rusu.setBounds(40, 65, 80, 25);
    tusu.setBounds(120, 65, 140, 25);
    rsen.setBounds(40, 105, 80, 25);
    tsen.setBounds(120, 105, 140, 25);
    blogar.setBounds(90, 155, 120, 35);

    tela.add(rtit);
    tela.add(rusu);
    tela.add(tusu);
    tela.add(rsen);
    tela.add(tsen);
    tela.add(blogar);

    setSize(320, 240);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);

    // Listener do botão de login
    blogar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String usuario = tusu.getText();
            String senha = new String(tsen.getPassword());
            try {
                String sql = "SELECT * FROM usuarios WHERE usuario='" + usuario + "' AND senha='" + senha + "'";
                con_cliente.ExecutarSQL(sql);
                if (con_cliente.resultset.next()) {
                    JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
                    dispose(); // Fecha o frame de login
                    Frame principal = new Frame();
                    principal.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao conectar: " + ex.getMessage());
            }
        }
    });
    
    }
    
}
