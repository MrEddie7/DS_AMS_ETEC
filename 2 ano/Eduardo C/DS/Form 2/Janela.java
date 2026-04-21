package formulario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Janela extends JFrame {
    private final JTextField texto1;
    private final JTextField texto2;
    private final JTextField campoRestricoes;
    private final JTextArea campoEndereco;

    public Janela() {
        Container tela = getContentPane();
        tela.setBackground(Color.DARK_GRAY);
        tela.setLayout(null);
       

        // Componentes
        JLabel Nome = new JLabel("Nome: ");
        JLabel Matricula = new JLabel("Matrícula: ");
        JLabel sexo = new JLabel("Sexo: ");
        JLabel dadosCurso = new JLabel("Informe dados do curso: ");
        JLabel turno = new JLabel("Turno: "); // Novo label
        JLabel endereco = new JLabel("Endereço: ");
        JLabel restricoes = new JLabel("Restrições Médicas: ");
        JLabel autor = new JLabel("Desenvolvido por Eduardo Cavalcante 2ºAMS");
        JLabel titulo = new JLabel("Dados Cadastrais");

        texto1 = new JTextField(10);
        texto2 = new JTextField(10);
        campoRestricoes = new JTextField();
        campoEndereco = new JTextArea(5, 20);
        JScrollPane scrollEndereco = new JScrollPane(campoEndereco);
        scrollEndereco.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollEndereco.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        // ComboBox Turno
        String[] opcoesTurno = {"Escolha . . .","Manhã", "Tarde", "Noite"};
        JComboBox<String> comboTurno = new JComboBox<>(opcoesTurno);
        comboTurno.setBackground(Color.WHITE);

        JButton limpar = new JButton("Limpar");
        JButton enviar = new JButton("Enviar");
        JButton sair = new JButton("Sair");

        // Cores dos rótulos
        for (JLabel label : new JLabel[]{Nome, Matricula, sexo, dadosCurso, turno, endereco, restricoes, autor}) {
            label.setForeground(Color.WHITE);
        }

        // Posicionamento dos rótulos e campos
        titulo.setBounds(100, 10, 120, 40);
        Nome.setBounds(50, 70, 120, 20);
        texto1.setBounds(180, 70, 200, 20);

        Matricula.setBounds(50, 100, 120, 20);
        texto2.setBounds(180, 100, 200, 20);

        sexo.setBounds(50, 130, 120, 20);
        
        dadosCurso.setBounds(50, 160, 200, 20);

      
        turno.setBounds(50, 250, 120, 20);
        comboTurno.setBounds(180, 250, 150, 20);

        endereco.setBounds(50, 280, 150, 20);
        scrollEndereco.setBounds(180, 280, 280, 60);

        restricoes.setBounds(50, 350, 150, 20);
        campoRestricoes.setBounds(180, 350, 280, 20);

        autor.setBounds(50, 420, 300, 20);

        enviar.setBounds(50, 460, 100, 30);
        limpar.setBounds(160, 460, 100, 30);
        sair.setBounds(270, 460, 100, 30);

        // Sexo (Radio)
        JRadioButton feminino = new JRadioButton("Feminino");
        JRadioButton masculino = new JRadioButton("Masculino");
        ButtonGroup grupoSexo = new ButtonGroup();
        grupoSexo.add(feminino);
        grupoSexo.add(masculino);
         feminino.setBounds(180, 130, 100, 20);
        masculino.setBounds(290, 130, 100, 20);

        feminino.setForeground(Color.WHITE);
        masculino.setForeground(Color.WHITE);
        feminino.setBackground(Color.DARK_GRAY);
        masculino.setBackground(Color.DARK_GRAY);
        
       
        // Destaque do título
        titulo.setFont(new Font("Serif", Font.BOLD, 26));
        titulo.setForeground(Color.ORANGE);
        titulo.setBounds(180, 10, 300, 40);

        // Reposicionamento dos componentes
        Nome.setBounds(50, 70, 120, 20);
        texto1.setBounds(180, 70, 200, 20);

        Matricula.setBounds(50, 100, 120, 20);
        texto2.setBounds(180, 100, 200, 20);

        sexo.setBounds(50, 130, 120, 20);
        feminino.setBounds(180, 130, 100, 20);
        masculino.setBounds(290, 130, 100, 20);

        dadosCurso.setBounds(50, 160, 200, 20);

      
        turno.setBounds(50, 250, 120, 20);
        comboTurno.setBounds(180, 250, 150, 20);

        endereco.setBounds(50, 280, 150, 20);
        scrollEndereco.setBounds(180, 280, 280, 60);

        restricoes.setBounds(50, 350, 150, 20);
        campoRestricoes.setBounds(180, 350, 280, 20);

        autor.setBounds(50, 420, 300, 20);

        enviar.setBounds(50, 460, 100, 30);
        limpar.setBounds(160, 460, 100, 30);
        sair.setBounds(270, 460, 100, 30);
        // serie
        
        ButtonGroup serie = new ButtonGroup();

JRadioButton ano1 = new JRadioButton("1° ano");
JRadioButton ano2 = new JRadioButton("2° ano");
JRadioButton ano3 = new JRadioButton("3° ano");
        
        
        
       ano1.setBounds(360, 180, 100, 20);
        ano2.setBounds(360, 200, 100, 20);
        ano3.setBounds(360, 220, 100, 20);
       
        ano1.setForeground(Color.WHITE);
        ano1.setBackground(Color.DARK_GRAY);
        
        ano2.setForeground(Color.WHITE);
        ano2.setBackground(Color.DARK_GRAY);
        
        ano3.setForeground(Color.WHITE);
        ano3.setBackground(Color.DARK_GRAY);
       
        serie.add(ano1);
        serie.add(ano2);
        serie.add(ano3);

        // Cursos (Radio em 2 colunas de 3 linhas)
        JRadioButton curso1 = new JRadioButton("Engenharia");
        JRadioButton curso2 = new JRadioButton("Medicina");
        JRadioButton curso3 = new JRadioButton("Direito");
        JRadioButton curso4 = new JRadioButton("Computação");
        JRadioButton curso5 = new JRadioButton("Arquitetura");
        JRadioButton curso6 = new JRadioButton("Administração");

        ButtonGroup grupoCurso = new ButtonGroup();
        for (JRadioButton rb : new JRadioButton[]{curso1, curso2, curso3, curso4, curso5, curso6}) {
            grupoCurso.add(rb);
            rb.setForeground(Color.WHITE);
            rb.setBackground(Color.DARK_GRAY);
        }

        // Posições dos cursos
        curso1.setBounds(50, 180, 150, 20);
        curso2.setBounds(50, 200, 150, 20);
        curso3.setBounds(50, 220, 150, 20);
        curso4.setBounds(200, 180, 150, 20);
        curso5.setBounds(200, 200, 150, 20);
        curso6.setBounds(200, 220, 150, 20);
        
        //Enviar
        
        enviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Capturando os valores selecionados
              
        String nome = texto1.getText();
        String matricula = texto2.getText();
        String sexoSelecionado = feminino.isSelected() ? "Feminino" :
                                  masculino.isSelected() ? "Masculino" : "Não informado";
        
        String anoSelecionado = ano1.isSelected() ? "1° ano" :
                                  ano2.isSelected() ? "2° ano" :
                ano3.isSelected() ? "3° ano" : "Não informado";
        
        

        
        String cursoSelecionado = "Não informado";
        for (JRadioButton rb : new JRadioButton[]{curso1, curso2, curso3, curso4, curso5, curso6}) {
            if (rb.isSelected()) {
                cursoSelecionado = rb.getText();
                break;
            }
        }

        String turnoSelecionado = (String) comboTurno.getSelectedItem();
        String endereco = campoEndereco.getText();
        String restricoes = campoRestricoes.getText();

        // Criar nova janela com os dados
        JDialog resultadoDialog = new JDialog(Janela.this, "Dados do Aluno", true);
        resultadoDialog.setSize(400, 300);
        resultadoDialog.setLayout(new BorderLayout());

        JTextArea resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);
        resultadoArea.setFont(new Font("Arial", Font.PLAIN, 14));
        resultadoArea.setText(
            "Nome: " + nome + "\n" +
            "Matrícula: " + matricula + "\n" +
            "Sexo: " + sexoSelecionado + "\n" +
            "Curso: " + cursoSelecionado + "\n" +
            "Turno: " + turnoSelecionado + "\n" +
            "Serie: "+ anoSelecionado+"\n"+
            "Endereço: " + endereco + "\n" +
            "Restrições Médicas: " + restricoes
        );

        JScrollPane scroll = new JScrollPane(resultadoArea);
        resultadoDialog.add(scroll, BorderLayout.CENTER);

        JButton fechar = new JButton("Fechar");
        fechar.addActionListener(evt -> resultadoDialog.dispose());
        resultadoDialog.add(fechar, BorderLayout.SOUTH);
        texto1.setText("");
            texto2.setText("");
            campoRestricoes.setText("");
            campoEndereco.setText("");
            grupoSexo.clearSelection();
            grupoCurso.clearSelection();
            comboTurno.setSelectedIndex(0); // resetar ComboBox
            texto1.requestFocus();
            serie.clearSelection();

        resultadoDialog.setLocationRelativeTo(Janela.this);
        resultadoDialog.setVisible(true);
    

            }
        });
 

        // Botão Limpar
        limpar.addActionListener(e -> {
            texto1.setText("");
            texto2.setText("");
            campoRestricoes.setText("");
            campoEndereco.setText("");
            grupoSexo.clearSelection();
            grupoCurso.clearSelection();
            comboTurno.setSelectedIndex(0); // resetar ComboBox
            texto1.requestFocus();
        });

        // Botão Sair
        sair.addActionListener(e -> System.exit(0));

        // Adicionando componentes
        for (Component c : new Component[]{
                Nome, texto1, Matricula, texto2, sexo, feminino, masculino,
                dadosCurso, curso1, curso2, curso3, curso4, curso5, curso6,
                turno, comboTurno, endereco, scrollEndereco, restricoes, campoRestricoes,
                autor, enviar, limpar, sair, titulo
        }) {
            tela.add(c);
            tela.add(ano1);
            tela.add(ano2);
            tela.add(ano3);
           
            
            
        }

        setSize(600, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
