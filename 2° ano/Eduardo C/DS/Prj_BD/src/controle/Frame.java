package controle;

import java.awt.*;
import java.text.*;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.table.DefaultTableModel;

import conexao.Conexao;
import javax.swing.JOptionPane;

public class Frame extends JFrame {
    
    Conexao con_clientes;
    
    JLabel rCod, rNom, rEmail, rTel, rdata;
    JTextField tcod, tnom, temail;
    JFormattedTextField tel, data;
    MaskFormatter mTel, mData;
    
    JTable tblClientes;
    JScrollPane scp_tabela;
    JButton btnSalvar, btnPrimeiro, btnAnterior, btnProximo, btnUltimo, btnNovo, btnGravar, btnAlterar, btnExcluir, btnPesquisar, btnSair;
    JPanel tela;
    JTextField tPesquisar;

    public Frame(){
        setTitle("Conexão Java com MySql");
        setResizable(false);
        getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLUE));
        tela = new JPanel();
        tela.setLayout(null);
        tela.setBackground(new Color(230, 240, 255)); // cor suave de fundo
        setContentPane(tela);

        // Labels e campos
        rCod = new JLabel("Código:");
        rNom = new JLabel("Nome:");
        rdata = new JLabel("Data de Nascimento:");
        rTel = new JLabel("Telefone:");
        rEmail = new JLabel("Email:");

        tcod = new JTextField();
        tnom = new JTextField();
        temail = new JTextField();
        try {
            mTel = new MaskFormatter("(##)#####-####");
            tel = new JFormattedTextField(mTel);
            mData = new MaskFormatter("##/##/####");
            data = new JFormattedTextField(mData);
        } catch (ParseException e) {
            tel = new JFormattedTextField();
            data = new JFormattedTextField();
        }

        // Posicionamento
        rCod.setBounds(50, 30, 100, 25);
        tcod.setBounds(180, 30, 150, 25);
        rNom.setBounds(50, 70, 100, 25);
        tnom.setBounds(180, 70, 300, 25);
        rdata.setBounds(50, 110, 130, 25);
        data.setBounds(180, 110, 150, 25);
        rTel.setBounds(50, 150, 100, 25);
        tel.setBounds(180, 150, 150, 25);
        rEmail.setBounds(50, 190, 100, 25);
        temail.setBounds(180, 190, 300, 25);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(180, 230, 100, 30);

        tela.add(rCod); tela.add(tcod);
        tela.add(rNom); tela.add(tnom);
        tela.add(rdata); tela.add(data);
        tela.add(rTel); tela.add(tel);
        tela.add(rEmail); tela.add(temail);
        tela.add(btnSalvar);

        // config jtable
        tblClientes = new javax.swing.JTable();
        scp_tabela = new javax.swing.JScrollPane();
        tblClientes.setBounds(50, 280, 780, 250);
        scp_tabela.setBounds(50, 280, 780, 250);

        tela.add(scp_tabela);
        tblClientes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0,0,0)));
        tblClientes.setFont(new java.awt.Font("Arial", 1, 12));
        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Codigo", "Nome", "Data de Nascimento", "Telefone", "Email"}
        ) {
            boolean[] canEdit = new boolean[]{false,false,false,false,false};
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return canEdit [columnIndex];
            }
        });
        scp_tabela.setViewportView(tblClientes);
        tblClientes.setAutoCreateRowSorter(true);

        // Conexão
        con_clientes = new Conexao();
        carregarDados();

        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                salvarCliente();
            }
        });
        
        btnPrimeiro = new JButton("Primeiro");
        btnAnterior = new JButton("Anterior");
        btnProximo = new JButton("Próximo");
        btnUltimo = new JButton("Último");
        btnNovo = new JButton("Novo Registro");
        btnGravar = new JButton("Gravar");
        btnAlterar = new JButton("Alterar");
        btnExcluir = new JButton("Excluir");
        btnPesquisar = new JButton("Pesquisar"); 
        btnSair = new JButton("Sair");
        tPesquisar = new JTextField();           
          


       // Navegação
       
        btnPrimeiro.setBounds(50, 550, 110, 30);
        btnAnterior.setBounds(170, 550, 110, 30);
        btnProximo.setBounds(50, 590, 110, 30);
        btnUltimo.setBounds(170, 590, 110, 30);

        // Ações principais
        btnNovo.setBounds(300, 550, 130, 30);
        btnGravar.setBounds(440, 550, 130, 30);
        btnAlterar.setBounds(300, 590, 130, 30);
        btnExcluir.setBounds(440, 590, 130, 30);

        // Botão sair mais abaixo
        btnSair.setBounds(720, 570, 110, 30);

        // Adiciona ao painel
        tela.add(btnPrimeiro); tela.add(btnAnterior);
        tela.add(btnProximo); tela.add(btnUltimo);
        tela.add(btnNovo); tela.add(btnGravar);
        tela.add(btnAlterar); tela.add(btnExcluir);
        tela.add(tPesquisar); tela.add(btnPesquisar);
        tela.add(btnSair);

       

        btnPrimeiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                navegarTabela(0);
            }
        });
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                navegarTabela(tblClientes.getSelectedRow() - 1);
            }
        });
        btnProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                navegarTabela(tblClientes.getSelectedRow() + 1);
            }
        });
        btnUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                navegarTabela(tblClientes.getRowCount() - 1);
            }
        });
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                limparCampos();
            }
        });
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                salvarCliente();
            }
        });
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                alterarCliente();
            }
        });
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                excluirCliente();
            }
        });
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                pesquisarCliente();
            }
        });
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                sair();
            }
        });

        setSize(900, 700);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void carregarDados() {
        try {
            con_clientes.conectar();
            String sql = "SELECT * FROM tbclientes";
            java.sql.Statement stmt = con_clientes.conexao.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery(sql);
            DefaultTableModel model = (DefaultTableModel) tblClientes.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("cod"),
                    rs.getString("nome"),
                    rs.getString("dt_nasc"),
                    rs.getString("telefone"),
                    rs.getString("email")
                });
            }
            rs.close();
            stmt.close();
            con_clientes.desconectar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados: " + ex.getMessage());
        }
    }

    private void salvarCliente() {
        String codigo = tcod.getText();
        String nome = tnom.getText();
        String nascimento = data.getText();
        String telefone = tel.getText();
        String email = temail.getText();
        if (codigo.isEmpty() || nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha os campos obrigatórios.");
            return;
        }
        try {
            con_clientes.conectar();
            String sql = "INSERT INTO tbclientes (cod, nome, dt_nasc, telefone, email) VALUES (?, ?, ?, ?, ?)";
            java.sql.PreparedStatement stmt = con_clientes.conexao.prepareStatement(sql);
            stmt.setString(1, codigo);
            stmt.setString(2, nome);
            stmt.setString(3, nascimento);
            stmt.setString(4, telefone);
            stmt.setString(5, email);
            stmt.executeUpdate();
            stmt.close();
            con_clientes.desconectar();
            carregarDados();
            JOptionPane.showMessageDialog(this, "Cliente salvo com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
        }
    }

    private void navegarTabela(int row) {
        if (row >= 0 && row < tblClientes.getRowCount()) {
            tblClientes.setRowSelectionInterval(row, row);
            tcod.setText(tblClientes.getValueAt(row, 0).toString());
            tnom.setText(tblClientes.getValueAt(row, 1).toString());
            data.setText(tblClientes.getValueAt(row, 2).toString());
            tel.setText(tblClientes.getValueAt(row, 3).toString());
            temail.setText(tblClientes.getValueAt(row, 4).toString());
        }
    }

    private void limparCampos() {
        tcod.setText("");
        tnom.setText("");
        data.setText("");
        tel.setText("");
        temail.setText("");
    }

    private void alterarCliente() {
        String codigo = tcod.getText();
        String nome = tnom.getText();
        String nascimento = data.getText();
        String telefone = tel.getText();
        String email = temail.getText();
        if (codigo.isEmpty() || nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha os campos obrigatórios.");
            return;
        }
        try {
            con_clientes.conectar();
            String sql = "UPDATE tbclientes SET nome=?, dt_nasc=?, telefone=?, email=? WHERE cod=?";
            java.sql.PreparedStatement stmt = con_clientes.conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, nascimento);
            stmt.setString(3, telefone);
            stmt.setString(4, email);
            stmt.setString(5, codigo);
            stmt.executeUpdate();
            stmt.close();
            con_clientes.desconectar();
            carregarDados();
            JOptionPane.showMessageDialog(this, "Cliente alterado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao alterar: " + ex.getMessage());
        }
    }

    private void excluirCliente() {
        String codigo = tcod.getText();
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para excluir.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir este cliente?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                con_clientes.conectar();
                String sql = "DELETE FROM tbclientes WHERE cod=?";
                java.sql.PreparedStatement stmt = con_clientes.conexao.prepareStatement(sql);
                stmt.setString(1, codigo);
                stmt.executeUpdate();
                stmt.close();
                con_clientes.desconectar();
                carregarDados();
                limparCampos();
                JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage());
            }
        }
    }

    private void pesquisarCliente() {
        String pesquisa = tPesquisar.getText();
        try {
            con_clientes.conectar();
            String sql = "SELECT * FROM tbclientes WHERE nome LIKE ? OR cod LIKE ?";
            java.sql.PreparedStatement stmt = con_clientes.conexao.prepareStatement(sql);
            stmt.setString(1, "%" + pesquisa + "%");
            stmt.setString(2, "%" + pesquisa + "%");
            java.sql.ResultSet rs = stmt.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tblClientes.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("cod"),
                    rs.getString("nome"),
                    rs.getString("dt_nasc"),
                    rs.getString("telefone"),
                    rs.getString("email")
                });
            }
            rs.close();
            stmt.close();
            con_clientes.desconectar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro na pesquisa: " + ex.getMessage());
        }
    }

    private void sair() {
        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja sair?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                con_clientes.desconectar();
                JOptionPane.showMessageDialog(this, "Banco de dados fechado. Até logo!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao fechar banco: " + ex.getMessage());
            }
            System.exit(0);
        }
    }
}
