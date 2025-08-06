
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
    
    public Frame(){
        
        setTitle("Conexão Java com MySql");
        setResizable(false);
        
        // config jtable
        
        tblClientes = new javax.swing.JTable();
        scp_tabela = new javax.swing.JScrollPane();
        
        tblClientes.setBounds(50, 200, 550, 200);
        scp_tabela.setBounds(50, 200, 550, 200);
        
       tela.add(tblClientes);
       tela.add(scp_tabela);
       
       tblClientes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0,0,0)));
       tblClientes.setFont(new java.awt.Font("Arial", 1, 12));
       
       tblClientes.setModel(new javax.swing.table.DefaultTableModel(
       new Object [][]{
           {null,null,null,null,null},
           {null,null,null,null,null},
           {null,null,null,null,null},
           {null,null,null,null,null}

       },
               new String [] {"Codigo", "Nome", "data de Nascimento", "Telefone", "Email"})
       {boolean[] canEdit = new boolean[]{false,false,false,false,false};
       
       public boolean isCellEditable(int rowIndex, int columnIndex){
                  return canEdit [columnIndex];       
       }
       });
       
       scp_tabela.setViewportView(tblClientes);
       tblClientes.setAutoCreateRowSorter(true);
       
       // fim da jatable
       
       setSize(800, 600);
       setVisible(true);
       setLocationRelativeTo(null);
}

    
    
}
