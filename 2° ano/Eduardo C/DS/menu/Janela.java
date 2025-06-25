import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Janela extends JFrame {

    private JFrame frameSoma, frameSub, frameMult, frameDiv, frameSobre;

    public Janela() {
        setTitle("Calculadora - Sem JDesktopPane");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        criarMenu();
        criarToolbar();
        criarPopup();
        criarJanelasFixas();

        setVisible(true);
    }

    private void criarJanelasFixas() {
        frameSoma = criarJanelaOperacao("Somar");
        frameSub = criarJanelaOperacao("Subtrair");
        frameMult = criarJanelaOperacao("Multiplicar");
        frameDiv = criarJanelaOperacao("Dividir");
        frameSobre = criarJanelaSobre();
    }

    private void criarMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuOperacoes = new JMenu("Operações");

        JMenuItem somar = new JMenuItem("Somar");
        JMenuItem subtrair = new JMenuItem("Subtrair");
        JMenuItem multiplicar = new JMenuItem("Multiplicar");
        JMenuItem dividir = new JMenuItem("Dividir");

        somar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarJanela(frameSoma);
            }
        });

        subtrair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarJanela(frameSub);
            }
        });

        multiplicar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarJanela(frameMult);
            }
        });

        dividir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarJanela(frameDiv);
            }
        });

        menuOperacoes.add(somar);
        menuOperacoes.add(subtrair);
        menuOperacoes.add(multiplicar);
        menuOperacoes.add(dividir);

        JMenu menuSobre = new JMenu("Sobre");
        JMenuItem sobre = new JMenuItem("Informações");

        sobre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarJanela(frameSobre);
            }
        });

        JMenu menuSair = new JMenu("Sair");
        JMenuItem sair = new JMenuItem("Sair");

        sair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sair();
            }
        });

        menuSobre.add(sobre);
        menuSair.add(sair);

        menuBar.add(menuOperacoes);
        menuBar.add(menuSobre);
        menuBar.add(menuSair);
        setJMenuBar(menuBar);
    }

    private void criarToolbar() {
        JToolBar toolbar = new JToolBar();

        JButton btnSoma = new JButton("Somar");
        JButton btnSub = new JButton("Subtrair");
        JButton btnMult = new JButton("Multiplicar");
        JButton btnDiv = new JButton("Dividir");
        JButton btnSair = new JButton("Sair");

        btnSoma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarJanela(frameSoma);
            }
        });

        btnSub.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarJanela(frameSub);
            }
        });

        btnMult.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarJanela(frameMult);
            }
        });

        btnDiv.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarJanela(frameDiv);
            }
        });

        btnSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sair();
            }
        });

        toolbar.add(btnSoma);
        toolbar.add(btnSub);
        toolbar.add(btnMult);
        toolbar.add(btnDiv);
        toolbar.addSeparator();
        toolbar.add(btnSair);

        add(toolbar, BorderLayout.NORTH);
    }

    private void criarPopup() {
        JPopupMenu popup = new JPopupMenu();

        JMenuItem sobre = new JMenuItem("Sobre");
        JMenuItem sair = new JMenuItem("Sair");

        sobre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarJanela(frameSobre);
            }
        });

        sair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sair();
            }
        });

        popup.add(sobre);
        popup.add(sair);

        // popup no frame principal
        
    }

    private JFrame criarJanelaOperacao(String operacao) {
        JFrame frame = new JFrame(operacao);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(5, 2, 5, 5));
        frame.setLocationRelativeTo(null);

        JTextField txt1 = new JTextField();
        JTextField txt2 = new JTextField();
        JTextField txtResultado = new JTextField();
        txtResultado.setEditable(false);

        JButton btnCalcular = new JButton("Calcular");
        JButton btnLimpar = new JButton("Limpar");

        btnCalcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double n1 = Double.parseDouble(txt1.getText());
                    double n2 = Double.parseDouble(txt2.getText());
                    double resultado = 0;

                    if (operacao.equals("Somar")) {
                        resultado = n1 + n2;
                    } else if (operacao.equals("Subtrair")) {
                        resultado = n1 - n2;
                    } else if (operacao.equals("Multiplicar")) {
                        resultado = n1 * n2;
                    } else if (operacao.equals("Dividir")) {
                        if (n2 == 0) {
                            JOptionPane.showMessageDialog(frame, "Divisão por zero!", "Erro", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        resultado = n1 / n2;
                    }

                    txtResultado.setText(String.valueOf(resultado));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Digite números válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txt1.setText("");
                txt2.setText("");
                txtResultado.setText("");
            }
        });

        frame.add(new JLabel("Número 1:"));
        frame.add(txt1);
        frame.add(new JLabel("Número 2:"));
        frame.add(txt2);
        frame.add(new JLabel("Resultado:"));
        frame.add(txtResultado);
        frame.add(btnCalcular);
        frame.add(btnLimpar);

        return frame;
    }

    private JFrame criarJanelaSobre() {
        JFrame frame = new JFrame("Sobre");
        frame.setSize(300, 150);
        frame.setLocationRelativeTo(null);

        JTextArea area = new JTextArea("Nome: João da Silva\nTurma: 3º ADS\nData de entrega: 12/06/2025");
        area.setEditable(false);
        frame.add(area);

        return frame;
    }

    private void mostrarJanela(JFrame frame) {
        if (!frame.isVisible()) {
            frame.setVisible(true);
        } else {
            frame.toFront();
            frame.requestFocus();
        }
    }

    private void sair() {
        int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente sair?", "Sair", JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

   

    
}
