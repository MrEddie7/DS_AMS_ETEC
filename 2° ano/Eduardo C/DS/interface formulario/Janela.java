import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Janela extends JFrame {
    private final JTextField texto1;
    private final JTextField texto2;
    private final JLabel resultado;
    
    public Janela() {

//container da tela
        Container tela = getContentPane();
        tela.setBackground(Color.DARK_GRAY);
        setLayout(null);
        
        // labels e textfield
        
        JLabel Nome = new JLabel("Nome: ");
        JLabel Idade = new JLabel("Idade: ");
        JLabel sexo = new JLabel("Sexo: ");
        JLabel interesses = new JLabel("Interesses: ");
        JLabel estado = new JLabel("Estado Civil: ");
        resultado = new JLabel("Itens selecionados: ");
        texto1 = new JTextField(10);
        texto2 = new JTextField(10);
        JLabel autor = new JLabel("Desenvolvido por Eduardo Cavalcante 2ºAMS");
        
        // botão
        
        JButton limpar = new JButton("Limpar");
        JButton enviar = new JButton("Enviar");
        JButton sair = new JButton("Sair");
        
        //cores
        
        Nome.setForeground(Color.white); 
        Idade.setForeground(Color.white);
        resultado.setForeground(Color.white);
        autor.setForeground(Color.white);
        sexo.setForeground(Color.white); 
        interesses.setForeground(Color.white);
       
        //posições ajustadas
        
        Nome.setBounds(50, 20, 120, 20);
        texto1.setBounds(180, 20, 200, 20);

        Idade.setBounds(50, 50, 120, 20);
        texto2.setBounds(180, 50, 200, 20);

        sexo.setBounds(50, 80, 120, 20);

        interesses.setBounds(50, 110, 120, 20);

        estado.setBounds(50, 140, 120, 20);

        resultado.setBounds(50, 200, 300, 20);

        autor.setBounds(50, 230, 300, 20);

        enviar.setBounds(50, 270, 100, 30);
        limpar.setBounds(160, 270, 100, 30);
        sair.setBounds(270, 270, 100, 30);
        
        // Adicionando RadioButtons para "Sexo"
        JRadioButton feminino = new JRadioButton("Feminino");
        JRadioButton masculino = new JRadioButton("Masculino");

        // Agrupando os RadioButtons
        ButtonGroup grupoSexo = new ButtonGroup();
        grupoSexo.add(feminino);
        grupoSexo.add(masculino);

        // Configurando cores e posições
        feminino.setForeground(Color.white);
        masculino.setForeground(Color.white);
        feminino.setBackground(Color.DARK_GRAY);
        masculino.setBackground(Color.DARK_GRAY);

        feminino.setBounds(180, 80, 100, 20);
        masculino.setBounds(290, 80, 100, 20);

        // Adicionando os RadioButtons ao container
        tela.add(feminino);
        tela.add(masculino);

        // Adicionando CheckBoxes para "Interesses"
        JCheckBox barcos = new JCheckBox("Barcos");
        JCheckBox avioes = new JCheckBox("Aviões");
        JCheckBox automoveis = new JCheckBox("Automóveis");

        // Configurando cores e posições
        barcos.setForeground(Color.white);
        avioes.setForeground(Color.white);
        automoveis.setForeground(Color.white);

        barcos.setBackground(Color.DARK_GRAY);
        avioes.setBackground(Color.DARK_GRAY);
        automoveis.setBackground(Color.DARK_GRAY);

        barcos.setBounds(180, 110, 100, 20);
        avioes.setBounds(290, 110, 100, 20);
        automoveis.setBounds(400, 110, 120, 20);

        

        // Adicionando ActionListener ao botão "Enviar"
        enviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Capturando os valores selecionados
               
            
                String nome = texto1.getText();
                String idade = texto2.getText();
                String sexoSelecionado = feminino.isSelected() ? "Feminino" : masculino.isSelected() ? "Masculino" : "Não informado";

                StringBuilder interessesSelecionados = new StringBuilder();
                if (barcos.isSelected()) interessesSelecionados.append("Barcos ");
                if (avioes.isSelected()) interessesSelecionados.append("Aviões ");
                if (automoveis.isSelected()) interessesSelecionados.append("Automóveis ");

                // setando no formulario
                
                formulario form = new formulario();
                form.setNome(nome);
                form.setIdade(idade);
                form.setSexo(sexoSelecionado);
                form.setInteresses(interessesSelecionados.length() > 0 ? interessesSelecionados.toString() : "Nenhum");

                // Use os getters para montar a mensagem
                String mensagem = "<html>Nome: " + form.getNome() +
                                  "<br>Idade: " + form.getIdade() +
                                  "<br>Sexo: " + form.getSexo() +
                                  "<br>Interesses: " + form.getInteresses() + "</html>";

                // Criando uma nova janela (JDialog) para exibir os resultados
                JDialog resultadoDialog = new JDialog(Janela.this, "Resultados", true);
                resultadoDialog.setSize(300, 200);
                resultadoDialog.setLayout(new BorderLayout());

                // Adicionando o conteúdo à nova janela
                JLabel resultadoLabel = new JLabel(mensagem);
                resultadoLabel.setHorizontalAlignment(SwingConstants.CENTER);
                resultadoDialog.add(resultadoLabel, BorderLayout.CENTER);

                // Botão para fechar a janela
                JButton fechar = new JButton("Fechar");
                fechar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        resultadoDialog.dispose();
                    }
                });
                resultadoDialog.add(fechar, BorderLayout.SOUTH);

                // Centralizando e exibindo a janela
                resultadoDialog.setLocationRelativeTo(Janela.this);
                resultadoDialog.setVisible(true);
            }
        });
 
       sair.addActionListener( 
       new ActionListener(){ 
        public void actionPerformed(ActionEvent e){ 
            System.exit(0);
        }
    }
);;

      limpar.addActionListener( 
      new ActionListener(){ 
        public void actionPerformed(ActionEvent e){ 
           texto1.setText("");
            texto2.setText("");
            resultado.setText("");
            texto1.requestFocus();
        }
    }
);;
        

       
        //tela add
        
        tela.add(sair);
        tela.add(limpar);
        tela.add(texto1);
        tela.add(texto2);
        tela.add(resultado);
        tela.add(autor);
        tela.add(enviar);
        tela.add(sexo);
        tela.add(Nome);
        tela.add(interesses);
        tela.add(Idade);
        tela.add(barcos);
        tela.add(avioes);
        tela.add(automoveis);
        //tela config
        
        setSize(550, 370);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        
        
        
        
        
    }
}
