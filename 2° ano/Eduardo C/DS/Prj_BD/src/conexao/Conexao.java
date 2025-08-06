package conexao;

import javax.swing.*;
import java.sql.*;


public class Conexao {
    
    final private String driver = "com.mysql.cj.jdbc.Driver";
    final private String url = "jdbc:mysql://localhost/clientes";
    final private String usuario = "root";
    final private String senha = ""; //465877 db casa
    private Connection conexao;
    public Statement statement;
    public ResultSet resultset;
    
    
    public boolean conectar() {
        
       boolean result = true;
       try{
       Class.forName(driver);
       conexao = DriverManager.getConnection(url,usuario,senha);
       JOptionPane.showInternalMessageDialog(null, "conexao confirmada",  "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
       }catch (ClassNotFoundException Driver){
       JOptionPane.showMessageDialog(null, "Driver não encontrado"+Driver, "Mensagem do Sistema", JOptionPane.INFORMATION_MESSAGE);
       result = false;
       }
       catch (SQLException Fonte){
       
        JOptionPane.showMessageDialog(null, "Fonte não encontrada"+Fonte, "Mensagem do Sistema", JOptionPane.INFORMATION_MESSAGE);
        result = false;
       
       }
       return result;
       
    }
    
    public void desconectar(){
    
        try{
        conexao.close();
         JOptionPane.showInternalMessageDialog(null, "conexao Fechada",  "Mensagem do Sistema", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException fecha){
        JOptionPane.showInternalMessageDialog(null, "Erro ao fechar Conexão",  "Mensagem do Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
       }
    
    public void ExecutarSQL (String sql){
    try{
        statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        resultset = statement.executeQuery(sql);
        
    
    }catch (SQLException exececao) {
    
         JOptionPane.showInternalMessageDialog(null, "Erro no comando SQL! ",  "Mensagem do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }
    
    }
    
    
    
    
}
