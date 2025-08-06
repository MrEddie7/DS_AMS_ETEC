package form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

public class Janela extends JFrame {

    public Janela() {
        setTitle("Calculadora Swing");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(Color.LIGHT_GRAY);

        // Barra de ferramentas
        JToolBar toolBar = new JToolBar();
        addToolButton(toolBar, "Somar", "Somar");
        addToolButton(toolBar, "Subtrair", "Subtrair");
        addToolButton(toolBar, "Multiplicar", "Multiplicar");
        addToolButton(toolBar, "Dividir", "Dividir");
        addExitButton(toolBar);
        add(toolBar, BorderLayout.NORTH);

        // Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menuOperacoes = new JMenu("Operações");
        String[] operacoes = {"Somar", "Subtrair", "Multiplicar", "Dividir"};
        for (String op : operacoes) {
            JMenuItem item = new JMenuItem(op);
            final String operacao = op;
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    abrirJanelaOperacao(operacao);
                }
            });
            menuOperacoes.add(item);
        }

        JMenu menuSobre = new JMenu("Sobre");
        JMenuItem itemSobre = new JMenuItem("Sobre");
        itemSobre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarSobre();
            }
        });
        menuSobre.add(itemSobre);

        JMenu menuSair = new JMenu("Sair");
        JMenuItem itemSair = new JMenuItem("Sair");
        itemSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmarSaida();
            }
        });
        menuSair.add(itemSair);

        menuBar.add(menuOperacoes);
        menuBar.add(menuSobre);
        menuBar.add(menuSair);
        setJMenuBar(menuBar);

        // Popup menu
        JPopupMenu popup = new JPopupMenu();
        JMenuItem popupSobre = new JMenuItem("Sobre");
        popupSobre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarSobre();
            }
        });
        popup.add(popupSobre);

        JMenuItem popupSair = new JMenuItem("Sair");
        popupSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmarSaida();
            }
        });
        popup.add(popupSair);

        this.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        setVisible(true);
    }

    private void addToolButton(JToolBar toolBar, String label, final String operacao) {
        JButton button = new JButton(label);
        button.setBackground(new Color(200, 200, 200));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirJanelaOperacao(operacao);
            }
        });
        toolBar.add(button);
    }

    private void addExitButton(JToolBar toolBar) {
        JButton button = new JButton("Sair");
        button.setBackground(new Color(255, 99, 71)); // Vermelho suave
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmarSaida();
            }
        });
        toolBar.add(button);
    }

    private void abrirJanelaOperacao(String operacao) {
        JDialog dialog = new JDialog(this, operacao, true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new GridBagLayout());
        dialog.getContentPane().setBackground(new Color(242, 242, 242)); // Cinza claro

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lbl1 = new JLabel("Valor 1:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        dialog.add(lbl1, gbc);

        JTextField txt1 = new JTextField(10);
        gbc.gridx = 1;
        dialog.add(txt1, gbc);

        JLabel lbl2 = new JLabel("Valor 2:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        dialog.add(lbl2, gbc);

        JTextField txt2 = new JTextField(10);
        gbc.gridx = 1;
        dialog.add(txt2, gbc);

        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.setBackground(new Color(100, 200, 100)); // Verde
        gbc.gridx = 0;
        gbc.gridy = 2;
        dialog.add(btnCalcular, gbc);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBackground(new Color(255, 215, 0)); // Amarelo
        gbc.gridx = 1;
        dialog.add(btnLimpar, gbc);

        JButton btnFechar = new JButton("Fechar");
        btnFechar.setBackground(new Color(255, 102, 102)); // Vermelho claro
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        dialog.add(btnFechar, gbc);

        JLabel lblResultado = new JLabel("Resultado: ");
        gbc.gridy = 4;
        dialog.add(lblResultado, gbc);

        btnCalcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double num1 = Double.parseDouble(txt1.getText());
                    double num2 = Double.parseDouble(txt2.getText());
                    double resultado = 0;

                    if (operacao.equals("Somar")) resultado = num1 + num2;
                    else if (operacao.equals("Subtrair")) resultado = num1 - num2;
                    else if (operacao.equals("Multiplicar")) resultado = num1 * num2;
                    else if (operacao.equals("Dividir")) resultado = num2 != 0 ? num1 / num2 : Double.NaN;

                    lblResultado.setText("Resultado: " + resultado);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Digite números válidos.");
                }
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txt1.setText("");
                txt2.setText("");
                lblResultado.setText("Resultado: ");
            }
        });

        btnFechar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        dialog.setVisible(true);
    }

    private void mostrarSobre() {
        JDialog dialog = new JDialog(this, "Sobre", true);
        dialog.setSize(300, 150);
        dialog.setLayout(new GridLayout(3, 1));
        dialog.setLocationRelativeTo(this);
        dialog.getContentPane().setBackground(new Color(242, 242, 242)); // Cinza claro

        JLabel lblNome = new JLabel("Nome: Eduadura Cavalcante", SwingConstants.CENTER);
        JLabel lblSegundo = new JLabel("2º AMS", SwingConstants.CENTER);
        JLabel lblData = new JLabel("Data: " + LocalDate.now(), SwingConstants.CENTER);

        Font fonte = new Font("SansSerif", Font.BOLD, 14);
        lblNome.setFont(fonte);
        lblSegundo.setFont(fonte);
        lblData.setFont(fonte);

        dialog.add(lblNome);
        dialog.add(lblSegundo);
        dialog.add(lblData);

        dialog.setVisible(true);
    }

    private void confirmarSaida() {
    String[] opcoes = {"Sim", "Não"};
    int resposta = JOptionPane.showOptionDialog(this,
            "Deseja realmente sair?",
            "Confirmação",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opcoes,
            opcoes[0]);

    if (resposta == 0) { // "Sim"
        System.exit(0);
    }
}

    
}
