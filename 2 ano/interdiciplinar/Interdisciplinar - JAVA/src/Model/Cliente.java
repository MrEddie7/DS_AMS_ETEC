package Model;
import Controler.Conexao;
import View.view_cliente;
import javax.swing.JOptionPane;
import java.sql.*;
public class Cliente {
    private int ID_cliente;
    private String Nome;
    private String CPF;
    private String Email;
    private String Telefone;
    public Cliente() {
        this(0, "","","","");
    } 
    public Cliente(int ID_cliente, String Nome, String CPF, String Email, String Telefone) {
    this.ID_cliente = ID_cliente;
    this.Nome = Nome;
    this.CPF = CPF;
    this.Email = Email;
    this.Telefone = Telefone;
}
    public int getID_cliente() {
        return ID_cliente;
    }
    public void setID_cliente(int ID_cliente) {
        this.ID_cliente = ID_cliente;
    }
    public String getNome() {
        return Nome;
    }
    public void setNome(String Nome) {
        this.Nome = Nome;
    }
    public String getCPF() {
        return CPF;
    }
    public void setIdade(String CPF) {
        this.CPF = CPF;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String Email) {
        this.Email = Email;
    }
    public String getTelefone() {
        return Telefone;
    }
    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }
    public void cadastrar(view_cliente form, Controler Conexao){
    try{
            String insert_sql="INSERT INTO `cliente` ( `Nome`, `CPF`, `Email`, `Telefone`) VALUES('"+getID_cliente()+"', '"+getNome()+"', '"+getCPF()+"', '"+getEmail()+"', '"+getTelefone()+"');"; 
            Conexao.statement.executeUpdate(insert_sql);
            JOptionPane.showMessageDialog(null,"Gravação realizada com sucesso!!","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
            Conexao.executaSQL("select * from cliente order by ID_cliente");
            form.preencherTabela();
        }catch(SQLException errosql){
            JOptionPane.showMessageDialog(null,"\nErro na gravação : \n" +errosql,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
        }
        public void alterar(view_cliente form, Controler Conexao){
         try {
             String sql;
                String msg="";
                if(form.txt_id.getText().equals("")){
                    sql="INSERT INTO `cliente` ( `Nome`, `CPF`, `Email`, `Telefone`) VALUES('"+getNome()+"', '"+getCPF()+"', '"+getEmail()+"', '"+getTelefone()+"');"; 
                    msg="Gravação de um novo registro";
                }
                else{
                    sql="UPDATE `cliente` SET  `Nome` = '"+getNome()+"', `CPF` = '"+getCPF()+"', `Email` = '"+getEmail()+"', `Telefone` = '"+getTelefone()+"' WHERE `cliente`.`ID_cliente` = "+getID_cliente();
                }
                Conexao.statement.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,"Alteração realizada com sucesso!!","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
                Conexao.executaSQL("select * from cliente order by ID_cliente");
                form.preencherTabela();
            }catch(SQLException errosql){
                JOptionPane.showMessageDialog(null,"\nErro na gravação : \n" +errosql,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
            }
        } 
         public void excluir(view_cliente form, Controler Conexao){
        String sql="";
        try {
            int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir o registro: ","Confirmar Exclusão", JOptionPane.YES_NO_OPTION,3);
            if(resposta ==0){
                sql = "delete from cliente where ID_cliente = " +getID_cliente();
                int excluir = Conexao.statement.executeUpdate(sql);
                if(excluir==1){
                    JOptionPane.showMessageDialog(null,"Exclusão realizada com sucesso!!","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
                    Conexao.executaSQL("select * from cliente order by ID_cliente");
                    Conexao.resultset.first();
                    form.preencherTabela();
                    form.posicionarRegistro();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Operação cancelada pelo usuário!!","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }catch(SQLException excecao){
            JOptionPane.showMessageDialog(null,"\nErro na exclusão : \n" +excecao,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
        }
        }
          public void pesquisar(view_cliente form, Controler Conexao){
     try{
            String pesquisa = "select * from cliente where Nome like '"+getNome()+"%'";
            Conexao.executaSQL(pesquisa);
            if(Conexao.resultset.first()){
                form.preencherTabela();
            }
            else{
                JOptionPane.showMessageDialog(null,"\n Não existe dados com este paramêtro!!","Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);   
            }
        }catch(SQLException errosql){
            JOptionPane.showMessageDialog(null,"\n Os dados digitados não foram localizados!! :\n "+errosql,"Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE); 
        }    
    }
}