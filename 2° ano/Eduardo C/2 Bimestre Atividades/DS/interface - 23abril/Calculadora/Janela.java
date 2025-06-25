import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Janela extends JFrame {
    private final JTextField texto1;
    private final JTextField texto2;
    private final JLabel resultado;

    public Janela() {
        super("Calculadora");

        Container tela = getContentPane();
        tela.setBackground(Color.DARK_GRAY);
        setLayout(null);

        // Criar calculadora
        Calculadora calc = new Calculadora();

        // Componentes
        JLabel rotulo1 = new JLabel("Primeiro número");
        JLabel rotulo2 = new JLabel("Segundo número");
        resultado = new JLabel("Resultado = ");
        JLabel autor = new JLabel("Desenvolvido por Eduardo Cavalcante 2ºAMS");
        texto1 = new JTextField(10);
        texto2 = new JTextField(10);

        JButton botao1 = new JButton("+");
        JButton botao2 = new JButton("-");
        JButton botao3 = new JButton("*");
        JButton botao4 = new JButton("/");
        JButton botao5 = new JButton("Limpar");
        JButton botao6 = new JButton("Habilitar");
        JButton botao7 = new JButton("Desabilitar");
        JButton botao8 = new JButton("Ocultar");
        JButton botao9 = new JButton("Exibir");
        JButton botao10 = new JButton("Sair");
        
        //cores
        rotulo1.setForeground(Color.white); 
        rotulo2.setForeground(Color.white);
        resultado.setForeground(Color.white);
        autor.setForeground(Color.white);

        // Imagem
       ImageIcon imagemOriginal = new ImageIcon("img.jpg");//puxa a imagem do src caso não tenha src perdão no meu pc tem
       //usa a função que cria uma variavel ImagemRedimensionada usando o getImage com getscaledInstance para redimensionar a imagem via java
       Image imagemRedimensionada = imagemOriginal.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
       //usa o imagem redimencionada para um novo imagemIcon que converte numa JLabel
       ImageIcon imagemIcon = new ImageIcon(imagemRedimensionada);
       JLabel imagemLabel = new JLabel(imagemIcon);

        // Posições
        rotulo1.setBounds(50, 20, 120, 20);
        rotulo2.setBounds(50, 50, 120, 20);
        texto1.setBounds(180, 20, 100, 20);
        texto2.setBounds(180, 50, 100, 20);
        resultado.setBounds(50, 80, 200, 20);
        autor.setBounds(50, 110, 300, 20);
        botao1.setBounds(50, 150, 50, 30);
        botao2.setBounds(110, 150, 50, 30);
        botao3.setBounds(170, 150, 50, 30);
        botao4.setBounds(230, 150, 50, 30);
        botao5.setBounds(50, 190, 100, 30);
        botao6.setBounds(160, 190, 100, 30);
        botao7.setBounds(50, 230, 100, 30);
        botao8.setBounds(160, 230, 100, 30);
        botao9.setBounds(50, 270, 100, 30);
        botao10.setBounds(160, 270, 100, 30);
        imagemLabel.setBounds(320, 10, 200, 300);

        // Adicionar componentes
        tela.add(rotulo1);
        tela.add(rotulo2);
        tela.add(texto1);
        tela.add(texto2);
        tela.add(resultado);
        tela.add(autor);
        tela.add(imagemLabel);
        tela.add(botao1);
        tela.add(botao2);
        tela.add(botao3);
        tela.add(botao4);
        tela.add(botao5);
        tela.add(botao6);
        tela.add(botao7);
        tela.add(botao8);
        tela.add(botao9);
        tela.add(botao10);

        
        
        
        botao2.addActionListener( 
new ActionListener(){ 
public void actionPerformed(ActionEvent e){ 
rotulo1.setEnabled(true); 
rotulo2.setEnabled(true); 
} 
} 
);
botao1.addActionListener( 
    new ActionListener(){ 
        public void actionPerformed(ActionEvent e){ 
            try {
                double n1 = Double.parseDouble(texto1.getText());
                double n2 = Double.parseDouble(texto2.getText());
                calc.somar(n1, n2);  // Usando o método que abre JOptionPane
                resultado.setText("Resultado = " + calc.getR());
            } catch (NumberFormatException ex) {
                showError();
            }            
        }
    }
);

botao2.addActionListener( 
    new ActionListener(){ 
        public void actionPerformed(ActionEvent e){ 
            try {
                double n1 = Double.parseDouble(texto1.getText());
                double n2 = Double.parseDouble(texto2.getText());
                calc.subtrair(n1, n2);
                resultado.setText("Resultado = " + calc.getR());
            } catch (NumberFormatException ex) {
                showError();
            }            
        }
    }
);

botao3.addActionListener( 
    new ActionListener(){ 
        public void actionPerformed(ActionEvent e){ 
            try {
                double n1 = Double.parseDouble(texto1.getText());
                double n2 = Double.parseDouble(texto2.getText());
                double r = calc.multiplicar(n1, n2);
                resultado.setText("Resultado = " + r);
            } catch (NumberFormatException ex) {
                showError();
            }            
        }
    }
);

botao4.addActionListener( 
    new ActionListener(){ 
        public void actionPerformed(ActionEvent e){ 
            try {
                double n1 = Double.parseDouble(texto1.getText());
                double n2 = Double.parseDouble(texto2.getText());
                double r = calc.dividir(n1, n2);
                resultado.setText("Resultado = " + r);
            } catch (NumberFormatException ex) {
                showError();
            }            
        }
    }
);

botao5.addActionListener( 
    new ActionListener(){ 
        public void actionPerformed(ActionEvent e){ 
            texto1.setText("");
            texto2.setText("");
            resultado.setText("");
            texto1.requestFocus();
        }
    }
);

botao6.addActionListener( 
    new ActionListener(){ 
        public void actionPerformed(ActionEvent e){ 
            texto1.setEnabled(true);
            texto2.setEnabled(true);
            rotulo1.setEnabled(true);
            rotulo2.setEnabled(true);
            resultado.setEnabled(true);
        }
    }
);

botao7.addActionListener( 
    new ActionListener(){ 
        public void actionPerformed(ActionEvent e){ 
            texto1.setEnabled(false);
            texto2.setEnabled(false);
            rotulo1.setEnabled(false);
            rotulo2.setEnabled(false);
            resultado.setEnabled(true);
        }
    }
);

botao8.addActionListener( 
    new ActionListener(){ 
        public void actionPerformed(ActionEvent e){ 
            texto1.setVisible(false);
            texto2.setVisible(false);
            rotulo1.setVisible(false);
            rotulo2.setVisible(false);
            resultado.setVisible(false);
        }
    }
);

botao9.addActionListener( 
    new ActionListener(){ 
        public void actionPerformed(ActionEvent e){ 
            texto1.setVisible(true);
            texto2.setVisible(true);
            rotulo1.setVisible(true);
            rotulo2.setVisible(true);
            resultado.setVisible(true);
        }
    }
);

botao10.addActionListener( 
    new ActionListener(){ 
        public void actionPerformed(ActionEvent e){ 
            System.exit(0);
        }
    }
);


        // Atalhos de teclado (Key Bindings)
        InputMap im = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getRootPane().getActionMap();

        im.put(KeyStroke.getKeyStroke("control 1"), "soma");
        am.put("soma", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                botao1.doClick();
            }
        });

        im.put(KeyStroke.getKeyStroke("control 2"), "subtrair");
        am.put("subtrair", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                botao2.doClick();
            }
        });

        im.put(KeyStroke.getKeyStroke("control 3"), "multiplicar");
        am.put("multiplicar", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                botao3.doClick();
            }
        });

        im.put(KeyStroke.getKeyStroke("control 4"), "dividir");
        am.put("dividir", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                botao4.doClick();
            }
        });

        im.put(KeyStroke.getKeyStroke("control L"), "limpar");
        am.put("limpar", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                botao5.doClick();
            }
        });

        im.put(KeyStroke.getKeyStroke("control H"), "habilitar");
        am.put("habilitar", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                botao6.doClick();
            }
        });

        im.put(KeyStroke.getKeyStroke("control D"), "desabilitar");
        am.put("desabilitar", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                botao7.doClick();
            }
        });

        im.put(KeyStroke.getKeyStroke("control O"), "ocultar");
        am.put("ocultar", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                botao8.doClick();
            }
        });

        im.put(KeyStroke.getKeyStroke("control E"), "exibir");
        am.put("exibir", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                botao9.doClick();
            }
        });

        im.put(KeyStroke.getKeyStroke("control Q"), "sair");
        am.put("sair", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                botao10.doClick();
            }
        });

        // Config da janela
        setSize(550, 370);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void showError() {
        JOptionPane.showMessageDialog(this, "Digite apenas números válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    
}




