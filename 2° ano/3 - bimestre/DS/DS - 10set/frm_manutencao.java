package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.MaskFormatter;
import javax.swing.table.DefaultTableModel;
import Control.conexao;
import Model.mdl_carro;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.sql.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Admin
 */
public class frm_manutencao extends javax.swing.JFrame {

    conexao con_bco;
    mdl_carro car;

    /**
     * Creates new form frm_manutencao1
     */
    public frm_manutencao() {
        con_bco = new conexao();
        con_bco.conecta();

        car = new mdl_carro();//ta enviando o formulario inteiro 'this'= esse formulario, para a classe de modelagem do carro

        initComponents();

        con_bco.executaSQL("select * from nicacio order by idCarro");
        preencherTabela();
        posicionarRegistro();

        setLocationRelativeTo(null);
        //tooltips e mnemonic dos butões
        btn_pri.setMnemonic(KeyEvent.VK_I);
        btn_pri.setToolTipText("Seleciona o primeiro registro da tabela");
        btn_ant.setMnemonic(KeyEvent.VK_T);
        btn_ant.setToolTipText("Selecina o registro anterior do atual");
        btn_pro.setMnemonic(KeyEvent.VK_R);
        btn_pro.setToolTipText("Seleciona o próximo registro do atual");
        btn_ult.setMnemonic(KeyEvent.VK_O);
        btn_ult.setToolTipText("Seleciona o ultimo registro da tabela");
        btn_pes.setMnemonic(KeyEvent.VK_P);
        btn_pes.setToolTipText("Pesquisa os dados disponiveis dentro da tabela do banco de dados");
        btn_nov.setMnemonic(KeyEvent.VK_N);
        btn_nov.setToolTipText("Limpa os dados atuais para criação de um novo registro");
        btn_cad.setMnemonic(KeyEvent.VK_C);
        btn_cad.setToolTipText("Cadastra um novo registro na tabela");
        btn_alt.setMnemonic(KeyEvent.VK_A);
        btn_alt.setToolTipText("Altera o registro selecionada no momento");
        btn_exc.setMnemonic(KeyEvent.VK_E);
        btn_exc.setToolTipText("Excluir o registro selecionada no momento");
        btn_sair.setMnemonic(KeyEvent.VK_S);
        btn_sair.setToolTipText("Botão para sair");
        btn_Limpar.setMnemonic(KeyEvent.VK_L);
        btn_sair.setToolTipText("Botão para limpar as caixas de texto");
       
    }

    public void preencherTabela() {

        tbl_carros.getColumnModel().getColumn(0).setPreferredWidth(3);
        tbl_carros.getColumnModel().getColumn(1).setPreferredWidth(80);
        tbl_carros.getColumnModel().getColumn(2).setPreferredWidth(60);
        tbl_carros.getColumnModel().getColumn(3).setPreferredWidth(50);
        tbl_carros.getColumnModel().getColumn(4).setPreferredWidth(60);
        tbl_carros.getColumnModel().getColumn(5).setPreferredWidth(80);
        tbl_carros.getColumnModel().getColumn(6).setPreferredWidth(100);

        DefaultTableModel modelo = (DefaultTableModel) tbl_carros.getModel();
        modelo.setNumRows(0);

        try {
            con_bco.resultset.beforeFirst();
            while (con_bco.resultset.next()) {
                modelo.addRow(new Object[]{
                    con_bco.resultset.getString("idCarro"), con_bco.resultset.getString("Fabricante"), con_bco.resultset.getString("modelo"), con_bco.resultset.getString("ano"), con_bco.resultset.getString("Placa"), con_bco.resultset.getString("Cor"), con_bco.resultset.getString("Comentarios")
                });
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar dados da tabela");
        }
    }

    public void posicionarRegistro() {
        try {
            con_bco.resultset.first();
            mostrar_Dados();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Não foi possível posicionar no primeiro registro: " + erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void mostrar_Dados() {
        try {

            txt_id.setText(con_bco.resultset.getString("idCarro"));
            txt_fab.setText(con_bco.resultset.getString("Fabricante"));
            txt_mod.setText(con_bco.resultset.getString("Modelo"));
            txt_ano.setText(con_bco.resultset.getString("Ano"));
            txt_placa.setText(con_bco.resultset.getString("Placa"));
            txt_cor.setText(con_bco.resultset.getString("Cor"));
            txt_coment.setText(con_bco.resultset.getString("Comentarios"));
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Não localizou dados: " + erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_tela = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_carros = new javax.swing.JTable();
        btn_pri = new javax.swing.JButton();
        btn_ant = new javax.swing.JButton();
        btn_pro = new javax.swing.JButton();
        btn_ult = new javax.swing.JButton();
        lbl_id = new javax.swing.JLabel();
        lbl_fab = new javax.swing.JLabel();
        lbl_mod = new javax.swing.JLabel();
        lbl_ano = new javax.swing.JLabel();
        lbl_placa = new javax.swing.JLabel();
        lbl_cor = new javax.swing.JLabel();
        lbl_coment = new javax.swing.JLabel();
        btn_nov = new javax.swing.JButton();
        btn_cad = new javax.swing.JButton();
        btn_alt = new javax.swing.JButton();
        btn_exc = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txt_coment = new javax.swing.JTextArea();
        txt_id = new javax.swing.JTextField();
        txt_fab = new javax.swing.JTextField();
        txt_mod = new javax.swing.JTextField();
        txt_cor = new javax.swing.JTextField();
        lbl_pes = new javax.swing.JLabel();
        txt_pes = new javax.swing.JTextField();
        btn_pes = new javax.swing.JButton();
        btn_Limpar = new javax.swing.JButton();
        btn_sair = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_ano = new javax.swing.JFormattedTextField();
        txt_placa = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnl_tela.setBackground(new java.awt.Color(51, 51, 51));

        tbl_carros.setBackground(new java.awt.Color(204, 204, 204));
        tbl_carros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "fabricante", "modelo", "ano", "placa", "cor", "comentario"
            }
        ));
        jScrollPane1.setViewportView(tbl_carros);

        btn_pri.setBackground(new java.awt.Color(102, 102, 102));
        btn_pri.setForeground(new java.awt.Color(255, 255, 255));
        btn_pri.setText("Primeiro");
        btn_pri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_priActionPerformed(evt);
            }
        });

        btn_ant.setBackground(new java.awt.Color(102, 102, 102));
        btn_ant.setForeground(new java.awt.Color(255, 255, 255));
        btn_ant.setText("Anterior");
        btn_ant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_antActionPerformed(evt);
            }
        });

        btn_pro.setBackground(new java.awt.Color(102, 102, 102));
        btn_pro.setForeground(new java.awt.Color(255, 255, 255));
        btn_pro.setText("Próximo");
        btn_pro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_proActionPerformed(evt);
            }
        });

        btn_ult.setBackground(new java.awt.Color(102, 102, 102));
        btn_ult.setForeground(new java.awt.Color(255, 255, 255));
        btn_ult.setText("Último");
        btn_ult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ultActionPerformed(evt);
            }
        });

        lbl_id.setForeground(new java.awt.Color(255, 255, 255));
        lbl_id.setText("id:");

        lbl_fab.setForeground(new java.awt.Color(255, 255, 255));
        lbl_fab.setText("fabricante:");

        lbl_mod.setForeground(new java.awt.Color(255, 255, 255));
        lbl_mod.setText("modelo:");

        lbl_ano.setForeground(new java.awt.Color(255, 255, 255));
        lbl_ano.setText("ano:");

        lbl_placa.setForeground(new java.awt.Color(255, 255, 255));
        lbl_placa.setText("placa:");

        lbl_cor.setForeground(new java.awt.Color(255, 255, 255));
        lbl_cor.setText("cor:");

        lbl_coment.setForeground(new java.awt.Color(255, 255, 255));
        lbl_coment.setText("comentario:");

        btn_nov.setBackground(new java.awt.Color(102, 102, 102));
        btn_nov.setForeground(new java.awt.Color(255, 255, 255));
        btn_nov.setText("Novo");
        btn_nov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novActionPerformed(evt);
            }
        });

        btn_cad.setBackground(new java.awt.Color(102, 102, 102));
        btn_cad.setForeground(new java.awt.Color(255, 255, 255));
        btn_cad.setText("Cadastrar");
        btn_cad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cadActionPerformed(evt);
            }
        });

        btn_alt.setBackground(new java.awt.Color(102, 102, 102));
        btn_alt.setForeground(new java.awt.Color(255, 255, 255));
        btn_alt.setText("Alterar");
        btn_alt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_altActionPerformed(evt);
            }
        });

        btn_exc.setBackground(new java.awt.Color(102, 102, 102));
        btn_exc.setForeground(new java.awt.Color(255, 255, 255));
        btn_exc.setText("Excluir");
        btn_exc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excActionPerformed(evt);
            }
        });

        txt_coment.setBackground(new java.awt.Color(153, 153, 153));
        txt_coment.setColumns(20);
        txt_coment.setRows(5);
        txt_coment.setToolTipText("comentarios sobre o carro");
        jScrollPane3.setViewportView(txt_coment);

        txt_id.setBackground(new java.awt.Color(153, 153, 153));
        txt_id.setToolTipText("id de registro do carro");

        txt_fab.setBackground(new java.awt.Color(153, 153, 153));
        txt_fab.setToolTipText("fabricante do carro");

        txt_mod.setBackground(new java.awt.Color(153, 153, 153));
        txt_mod.setToolTipText("modelo do carro");

        txt_cor.setBackground(new java.awt.Color(153, 153, 153));
        txt_cor.setToolTipText("cor do carro");

        lbl_pes.setForeground(new java.awt.Color(255, 255, 255));
        lbl_pes.setText("Pesquisar pela placa do carro:");

        txt_pes.setBackground(new java.awt.Color(153, 153, 153));

        btn_pes.setBackground(new java.awt.Color(102, 102, 102));
        btn_pes.setForeground(new java.awt.Color(255, 255, 255));
        btn_pes.setText("Pesquisar");
        btn_pes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesActionPerformed(evt);
            }
        });

        btn_Limpar.setBackground(new java.awt.Color(102, 102, 102));
        btn_Limpar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Limpar.setText("Limpar");
        btn_Limpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LimparActionPerformed(evt);
            }
        });

        btn_sair.setBackground(new java.awt.Color(102, 102, 102));
        btn_sair.setForeground(new java.awt.Color(255, 255, 255));
        btn_sair.setText("Sair");
        btn_sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sairActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon("carro.jpg")); // NOI18N
        jLabel1.setText("Formulário de Carros");

        txt_ano.setBackground(new java.awt.Color(153, 153, 153));
        try {
            txt_ano.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_ano.setToolTipText("somente o ano, com os 4 caracteres");

        txt_placa.setBackground(new java.awt.Color(153, 153, 153));
        try {
            txt_placa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("AAA-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_placa.setToolTipText("Placa modelo antiga 3 letras de inicio e 4 numeros de final");
        txt_placa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_placaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_telaLayout = new javax.swing.GroupLayout(pnl_tela);
        pnl_tela.setLayout(pnl_telaLayout);
        pnl_telaLayout.setHorizontalGroup(
            pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_telaLayout.createSequentialGroup()
                .addGroup(pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_telaLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_telaLayout.createSequentialGroup()
                                .addComponent(lbl_pes, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_pes, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(btn_pes, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 833, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pnl_telaLayout.createSequentialGroup()
                                    .addGroup(pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnl_telaLayout.createSequentialGroup()
                                            .addGroup(pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lbl_fab)
                                                .addComponent(lbl_id))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txt_fab, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(pnl_telaLayout.createSequentialGroup()
                                            .addGroup(pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lbl_mod)
                                                .addComponent(lbl_ano)
                                                .addComponent(lbl_placa)
                                                .addComponent(lbl_cor))
                                            .addGap(18, 18, 18)
                                            .addGroup(pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(pnl_telaLayout.createSequentialGroup()
                                                        .addComponent(txt_cor, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(52, 52, 52)
                                                        .addComponent(lbl_coment))
                                                    .addGroup(pnl_telaLayout.createSequentialGroup()
                                                        .addComponent(txt_mod)
                                                        .addGap(83, 83, 83)))
                                                .addGroup(pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(txt_placa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                                    .addComponent(txt_ano, javax.swing.GroupLayout.Alignment.LEADING)))))
                                    .addGap(20, 20, 20)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(71, 71, 71)
                                    .addGroup(pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnl_telaLayout.createSequentialGroup()
                                            .addGroup(pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(btn_pri, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(btn_ant, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addComponent(btn_ult, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btn_pro))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(btn_nov, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btn_cad, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                                .addComponent(btn_alt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btn_exc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(pnl_telaLayout.createSequentialGroup()
                                            .addComponent(btn_Limpar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(54, 54, 54)
                                            .addComponent(btn_sair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                    .addGroup(pnl_telaLayout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(jLabel1)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        pnl_telaLayout.setVerticalGroup(
            pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_telaLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_telaLayout.createSequentialGroup()
                        .addGroup(pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_pri)
                            .addGroup(pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbl_id)
                                .addComponent(lbl_coment)
                                .addComponent(btn_nov))
                            .addComponent(txt_id, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn_ant)
                                .addComponent(btn_cad))
                            .addGroup(pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbl_fab)
                                .addComponent(txt_fab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbl_mod)
                                .addComponent(txt_mod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_pro, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_alt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_exc)
                            .addGroup(pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn_ult)
                                .addComponent(lbl_ano))
                            .addComponent(txt_ano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_placa)
                            .addComponent(txt_placa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_cor)
                            .addGroup(pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_cor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_Limpar)
                                .addComponent(btn_sair))))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_telaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_pes)
                        .addComponent(btn_pes))
                    .addComponent(txt_pes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_tela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnl_tela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ultActionPerformed
        // TODO add your handling code here:
        try {
            if (!con_bco.resultset.isLast()) {
                con_bco.resultset.last();
                mostrar_Dados();
            } else {
                JOptionPane.showMessageDialog(null, "Você já está no último registro", "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Não foi possível acessar o último registro");
        }
    }//GEN-LAST:event_btn_ultActionPerformed

    private void btn_priActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_priActionPerformed
        try {
            if (!con_bco.resultset.isFirst()) {
                con_bco.resultset.first();
                mostrar_Dados();
            }
            
            else {
                JOptionPane.showMessageDialog(null, "Você já está no primeiro registro", "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Não foi possível acessar o primeiro registro");
        }
    }//GEN-LAST:event_btn_priActionPerformed

    private void btn_proActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_proActionPerformed
        // TODO add your handling code here:
        try {
            if (!con_bco.resultset.isLast()) {
                con_bco.resultset.next();
                mostrar_Dados();
            }
            
            else {
                JOptionPane.showMessageDialog(null, "Você já está no último registro", "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Não foi possível acessar o proximo registro");
        }
    }//GEN-LAST:event_btn_proActionPerformed

    private void btn_antActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_antActionPerformed
        // TODO add your handling code here:
        try {
            if (!con_bco.resultset.isFirst()) {
                con_bco.resultset.previous();
                mostrar_Dados();
            }
            
            else {
                JOptionPane.showMessageDialog(null, "Você já está no primeiro registro", "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Não foi possível acessar o proximo registro");
        }
    }//GEN-LAST:event_btn_antActionPerformed

    private void btn_novActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novActionPerformed
        // TODO add your handling code here:
        txt_id.setText("");
        txt_fab.setText("");
        txt_mod.setText("");
        txt_ano.setText("");
        txt_placa.setText("");
        txt_cor.setText("");
        txt_coment.setText("");
        txt_id.requestFocus();
        
    }//GEN-LAST:event_btn_novActionPerformed

    private void btn_excActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excActionPerformed
        // TODO add your handling code here:
        try{
            car.setIdCarro(Integer.parseInt(txt_id.getText()));
			
            car.excluir(this, con_bco);
			}
            catch(Exception e){JOptionPane.showMessageDialog(null, "Não foi possivel excluir o registro");
        };
    }//GEN-LAST:event_btn_excActionPerformed
	
    
    private void btn_cadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cadActionPerformed
        // TODO add your handling code here:
        try{
            car.setIdCarro(Integer.parseInt(txt_id.getText()));
			car.setFabricante(txt_fab.getText());
			car.setModelo(txt_mod.getText());
			car.setAno(txt_ano.getText());
			car.setPlaca(txt_placa.getText());
			car.setCor(txt_cor.getText());
			car.setComentario(txt_coment.getText());
			
            car.cadastrar(this, con_bco);
			}
            catch(Exception e){JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar os dados verifique se todos \nos campos estão devidamente preenchidos");
        };
    }//GEN-LAST:event_btn_cadActionPerformed

    private void btn_altActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_altActionPerformed
        // TODO add your handling code here:
        try{
            car.setIdCarro(Integer.parseInt(txt_id.getText()));
			car.setFabricante(txt_fab.getText());
			car.setModelo(txt_mod.getText());
			car.setAno(txt_ano.getText());
			car.setPlaca(txt_placa.getText());
			car.setCor(txt_cor.getText());
			car.setComentario(txt_coment.getText());
			
            car.alterar(this, con_bco);
			}			
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Não foi possivel alterar os dados");
        };
    }//GEN-LAST:event_btn_altActionPerformed

    private void btn_pesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesActionPerformed
        // TODO add your handling code here:
        
        car.setPlaca(""+txt_pes.getText());
		
        car.pesquisar(this, con_bco);
        
    }//GEN-LAST:event_btn_pesActionPerformed

    private void btn_LimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LimparActionPerformed
        // TODO add your handling code here:
        txt_id.setText("");
        txt_fab.setText("");
        txt_mod.setText("");
        txt_ano.setText("");
        txt_placa.setText("");
        txt_cor.setText("");
        txt_coment.setText("");
        txt_pes.setText("");
    }//GEN-LAST:event_btn_LimparActionPerformed

    private void btn_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sairActionPerformed
        // TODO add your handling code here:
         int status = JOptionPane.showConfirmDialog(null,"Deseja realmente fechar o programa?","Mensagem de saida",JOptionPane.YES_NO_OPTION);
            if (status == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        
    }//GEN-LAST:event_btn_sairActionPerformed

    private void txt_placaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_placaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_placaActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Limpar;
    private javax.swing.JButton btn_alt;
    private javax.swing.JButton btn_ant;
    private javax.swing.JButton btn_cad;
    private javax.swing.JButton btn_exc;
    private javax.swing.JButton btn_nov;
    private javax.swing.JButton btn_pes;
    private javax.swing.JButton btn_pri;
    private javax.swing.JButton btn_pro;
    private javax.swing.JButton btn_sair;
    private javax.swing.JButton btn_ult;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_ano;
    private javax.swing.JLabel lbl_coment;
    private javax.swing.JLabel lbl_cor;
    private javax.swing.JLabel lbl_fab;
    private javax.swing.JLabel lbl_id;
    private javax.swing.JLabel lbl_mod;
    private javax.swing.JLabel lbl_pes;
    private javax.swing.JLabel lbl_placa;
    private javax.swing.JPanel pnl_tela;
    private javax.swing.JTable tbl_carros;
    private javax.swing.JFormattedTextField txt_ano;
    public javax.swing.JTextArea txt_coment;
    public javax.swing.JTextField txt_cor;
    public javax.swing.JTextField txt_fab;
    public javax.swing.JTextField txt_id;
    public javax.swing.JTextField txt_mod;
    public javax.swing.JTextField txt_pes;
    private javax.swing.JFormattedTextField txt_placa;
    // End of variables declaration//GEN-END:variables
 
   
     
}
