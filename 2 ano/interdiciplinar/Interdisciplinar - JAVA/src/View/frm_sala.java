package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Controler.conexao;
import Model.mdl_sala;

public class frm_sala extends JFrame {

    private JTextField txtNumSala;
    private JTextField txtNumAssentos;
    public JTextField txtTipoSala;
    public JTextField txtNumAssent;  // Campo para o número de assentos
    private JButton btnSalvar;
    private JButton btnCancelar;

    public frm_sala() {
        setTitle("Cadastro de Sala");
        setSize(350, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Layout
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));  // Alterado para 5 linhas (adicionando o novo campo)
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Campos
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

        // Botões
        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");
        panel.add(btnSalvar);
        panel.add(btnCancelar);

        add(panel);

        // Ação salvar
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarSala();
            }
        });

        // Ação cancelar
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

            // Cria objeto mdl_sala
            mdl_sala sala = new mdl_sala(numSala, numAssentos, tipoSala);

            // Exibe para conferência (aqui você pode salvar no banco)
            JOptionPane.showMessageDialog(this,
                "Sala cadastrada!\n" +
                "Número: " + sala.getNumSala() +
                "\nAssentos: " + sala.getNumAssent() +
                "\nTipo: " + sala.getTipoSala()
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
