package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Controler.Conexao;
import Model.Sala;
public class view_sala extends JFrame {
    private JTextField txtNumSala;
    private JTextField txtNumAssentos;
    public JTextField txtTipoSala;
    public JTextField txtNumAssent;  
    private JButton btnSalvar;
    private JButton btnCancelar;
    public view_sala(int numSala, int numAssentos, String tipoSala) {
        setTitle("Cadastro de Sala");
        setSize(350, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));  
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Número da Sala:"));
        txtNumSala = new JTextField();
        panel.add(txtNumSala);
        panel.add(new JLabel("Número de Assentos:"));
        txtNumAssentos = new JTextField();
        panel.add(txtNumAssentos);
        panel.add(new JLabel("Tipo da Sala:"));
        txtTipoSala = new JTextField();
        panel.add(txtTipoSala);
        panel.add(new JLabel("Número de Assentos:"));
        txtNumAssent = new JTextField();
        panel.add(txtNumAssent);
        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");
        panel.add(btnSalvar);
        panel.add(btnCancelar);
        add(panel);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarSala();
            }
        });
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    public void cadastrarSala() {
        try {
            int numSala = Integer.parseInt(txtNumSala.getText());
            int numAssentos = Integer.parseInt(txtNumAssentos.getText());
            String tipoSala = txtTipoSala.getText();
            view_sala Sala = new view_sala(numSala, numAssentos, tipoSala);
            JOptionPane.showMessageDialog(this,
                "Sala cadastrada!\n" +
                "Número: " + Sala.getNumSala() +
                "\nAssentos: " + Sala.getNumAssent() +
                "\nTipo: " + Sala.getTipoSala()
            );
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Erro: Número inválido.", 
                "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Erro: " + ex.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}