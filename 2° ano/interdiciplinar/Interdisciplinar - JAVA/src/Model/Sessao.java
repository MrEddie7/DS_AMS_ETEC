package Model;
import Controler.Conexao;
import View.view_sessao;
import java.sql.*;
import javax.swing.JOptionPane;
public class Sessao {
    private int Cod_Ses;
    private int Cod_Filme;
    private int Num_Sala;
    private String horario;
public Sessao() {
    this(0, 0, 0, "");
    }
    public Sessao(int Cod_Sesc, int Cod_Filme, int Num_Sala, String horario) {
        this.Cod_Ses = Cod_Sesc;
        this.Cod_Filme = Cod_Filme;
        this.Num_Sala = Num_Sala;
        this.horario = horario;
    }
    public int getCod_Sesc() {
        return Cod_Ses;
    }
    public void setCod_Sesc(){
        this.Cod_Ses = Cod_Ses;
    }
    public int getCod_Filme() {
        return Cod_Filme;
    }
    public void setCod_Filme() {
        this.Cod_Filme = Cod_Filme;
    }
    public int getNum_Sala() {
        return Num_Sala;
    }
    public void setNum_Sala() {
        this.Num_Sala = Num_Sala;
    }
    public String gethorario(String horario) {
        return horario;
    }
    public void sethorario() {
        this.horario = horario;
    }
    public void cadastrar(view_sessao form,Controler Conexao){
    try{
            String insert_sql="INSERT INTO `Sessao` ( `Cod_Sesc`, `Cod_Filme`, `Num_Sala`, `horario`) VALUES('"+getCod_Sesc()+"', '"+getCod_Filme()+"', '"+getNum_Sala()+"', '"+gethorario()+"');"; 
            Conexao.statement.executeUpdate(insert_sql);
            JOptionPane.showMessageDialog(null,"Gravação realizada com sucesso!!","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
            Conexao.executaSQL("select * from Sessao order by Cod_Sesc");
            form.preencherTabela();
        }catch(SQLException errosql){
            JOptionPane.showMessageDialog(null,"\nErro na gravação : \n" +errosql,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
        }
    }  
    public void alterar(view_sessao form,Controler Conexao){
         try {
             String sql;
                String msg="";
                if(form.txt_id.getText().equals("")){
                    sql="INSERT INTO `Sessao` ( `Cod_Sesc`, `Cod_Filme`, `Num_Sala`, `horario`) VALUES('"+getCod_Sesc()+"', '"+getCod_Filme()+"', '"+getNum_Sala()+"', '"+gethorario()+";
                    msg="Gravação de um novo registro";
                }
                else{
                    sql="UPDATE `Sessao` SET `Cod_Sesc` = '"+getCod_Sesc()+"', `Cod_Filme` = '"+getCod_Filme()+"', `Num_Sala` = '"+getNum_Sala()+"', `horario` = '"+gethorario();
                }
                Conexao.statement.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,"Alteração realizada com sucesso!!","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
                Conexao.executaSQL("select * from Sessao order by Cod_Sesc");
                form.preencherTabela();
            }catch(SQLException errosql){
                JOptionPane.showMessageDialog(null,"\nErro na gravação : \n" +errosql,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
            }
    }  
    public void excluir(view_sessao form,Controler Conexao){
        String sql="";
        try {
            int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir o registro: ","Confirmar Exclusão", JOptionPane.YES_NO_OPTION,3);
            if(resposta ==0){
                sql = "delete from Sessao where Cod_Sesc = " +getCod_Sesc();
                int excluir = con_bco.statement.executeUpdate(sql);
                if(excluir==1){
                    JOptionPane.showMessageDialog(null,"Exclusão realizada com sucesso!!","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
                    Conexao.executaSQL("select * from Sessao order by Cod_Sesc");
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
    public void pesquisar(view_sessao form,Controler Conexao){
     try{
            String pesquisa = "select * from Sessao where Cod_Sesc like '"+getCod_Sesc()+"%'";
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