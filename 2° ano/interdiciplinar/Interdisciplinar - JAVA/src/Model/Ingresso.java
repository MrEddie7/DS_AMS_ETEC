package Model;
import Controler.Conexao;
import View.view_ingresso;
import java.sql.*;
import javax.swing.JOptionPane;
public class Ingresso {
     private int ID_Ing;
    private int ID_cliente;
    private int Cod_Ses;
    private float Preco_ing;
public Ingresso() {
    this(0, 0, 0, 0);
    }
    public Ingresso(int ID_Ing, int ID_cliente, int Cod_Ses, float Preco_ing) {
        this.ID_Ing = ID_Ing;
        this.ID_cliente = ID_cliente;
        this.Cod_Ses = Cod_Ses;
        this.Preco_ing = Preco_ing;
    }
   public int getID_Ing() {
        return ID_Ing;
    }
    public void setID_Ing(){
        this.ID_Ing = ID_Ing;
    }
    public int getID_cliente() {
        return ID_cliente;
    }
    public void setID_cliente() {
        this.ID_cliente = ID_cliente;
    }
    public int getCod_Ses() {
        return Cod_Ses;
    }
    public void setCod_Ses() {
        this.Cod_Ses = Cod_Ses;
    }
    public float getPreco_ing() {
        return Preco_ing;
    }
    public void setPreco_ing() {
        this.Preco_ing = Preco_ing;
    }
    public void cadastrar(view_ingresso form,Controler Conexao){
    try{
            String insert_sql="INSERT INTO `Ingresso` ( `ID_Ing`, `ID_cliente`, `Cod_Ses`, `Preco_ing`) VALUES('"+getID_Ing()+"', '"+getID_cliente()+"', '"+getCod_Ses()+"', '"+getPreco_ing()+"');"; 
            Conexao.statement.executeUpdate(insert_sql);
            JOptionPane.showMessageDialog(null,"Gravação realizada com sucesso!!","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
            Conexao.executaSQL("select * from Ingresso order by ID_Ing");
            form.preencherTabela();
        }catch(SQLException errosql){
            JOptionPane.showMessageDialog(null,"\nErro na gravação : \n" +errosql,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void alterar(view_ingresso form,Controler Conexao){
         try {
             String sql;
                String msg="";
                if(form.txt_id.getText().equals("")){
                    sql="INSERT INTO `Ingresso` ( `ID_Ing`, `ID_cliente`, `Cod_Ses`, `Preco_ing`) VALUES('"+getID_Ing()+"', '"+getID_cliente()+"', '"+getCod_Ses()+"', '"+getPreco_ing()+"');";
                    msg="Gravação de um novo registro";
                }
                else{
                    sql="UPDATE `Ingresso` SET `ID_Ing` = '"+getID_Ing()+"', `Cliente` = '"+getID_cliente()+"', `Codigo` = '"+getCod_Ses()+"', `Preco` = '"+getPreco_ing();
                }
                Conexao.statement.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,"Alteração realizada com sucesso!!","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
                Conexao.executaSQL("select * from Ingresso order by getID_Ing");
                form.preencherTabela();
            }catch(SQLException errosql){
                JOptionPane.showMessageDialog(null,"\nErro na gravação : \n" +errosql,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
            }
    }  
    public void excluir(view_ingresso form,Controler Conexao){
        String sql="";
        try {
            int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir o registro: ","Confirmar Exclusão", JOptionPane.YES_NO_OPTION,3);
            if(resposta ==0){
                sql = "delete from Ingresso where getID_Ing = " +getID_Ing();
                int excluir = Conexao.statement.executeUpdate(sql);
                if(excluir==1){
                    JOptionPane.showMessageDialog(null,"Exclusão realizada com sucesso!!","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
                    Conexao.executaSQL("select * from Ingresso order by getID_Ing");
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
    public void pesquisar(view_ingresso form,Controler Conexao){
     try{
            String pesquisa = "select * from Ingresso where ID_Ing like '"+getID_Ing()+"%'";
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