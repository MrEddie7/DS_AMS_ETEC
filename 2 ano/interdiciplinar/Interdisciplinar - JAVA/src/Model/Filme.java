package Model;
import Controler.Conexao;
import View.view_filme;
import javax.swing.JOptionPane;
import java.sql.*;
public class Filme {
    private int Cod_Filme;
    private String Nome;
    private String Duracao;
    private String Classificacao;
    private String Genero;
    private String Sinopse;
    public Filme() {
        this(0, "","","","","");
    }  
    public Filme(int Cod_Filme, String Nome, String Duracao, String Classificacao, String Genero, String Sinopse) {
    this.Cod_Filme = Cod_Filme;
    this.Nome = Nome;
    this.Duracao = Duracao;
    this.Classificacao = Classificacao;
    this.Genero = Genero;
    this.Sinopse = Sinopse;
}
    public int getCod_Filme() {
        return Cod_Filme;
    }
    public void setCod_Filme(int Cod_Filme) {
        this.Cod_Filme = Cod_Filme;
    }
    public String getNome() {
        return Nome;
    }
    public void setNome(String Nome) {
        this.Nome = Nome;
    }
    public String getDuracao() {
        return Duracao;
    }
    public void setDuracao(String Duracao) {
        this.Duracao = Duracao;
    }
    public String getClassificacao() {
        return Classificacao;
    }
    public void setClassificacao(String Classificacao) {
        this.Classificacao = Classificacao;
    }
    public String getGenero() {
        return Genero;
    }
    public void setGenero(String Genero) {
        this.Genero = Genero;
    }
    public String getSinopse() {
        return Sinopse;
    }
    public void setSinopse(String Sinopse) {
        this.Sinopse = Sinopse;
    }
    public void cadastrar(view_filme form, Controler Conexao){
    try{
            String insert_sql="INSERT INTO `filme` ( `Nome`, `Duracao`, `Classificacao`, `Genero`, 'Sinopse') VALUES('"+getNome()+"', '"+getDuracao()+"', '"+getClassificacao()+"', '"+getGenero()+"', '"+getSinopse()+"');"; 
            Conexao.statement.executeUpdate(insert_sql);
            JOptionPane.showMessageDialog(null,"Gravação realizada com sucesso!!","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
            Conexao.executaSQL("select * from filme order by Cod_Filme");
            form.preencherTabela();
        }catch(SQLException errosql){
            JOptionPane.showMessageDialog(null,"\nErro na gravação : \n" +errosql,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
        }
        public void alterar(view_filme form, Controler Conexao){
         try {
             String sql;
                String msg="";
                if(form.txt_id.getText().equals("")){
                    sql="INSERT INTO `filme` ( `Nome`, `Duracao`, `Classificacao`, `Genero`, 'Sinopse') VALUES('"+getNome()+"', '"+getDuracao()+"', '"+getClassificacao()+"', '"+getGenero()+"', '"+getSinopse()+"');"; 
                    msg="Gravação de um novo registro";
                }
                else{
                    sql="UPDATE `filme` SET  `Nome` = '"+getNome()+"', `Duracao= '"+getDuracao()+"', `Classificacao` = '"+getClassificacao()+"', `Genero` = '"+getGenero()+"', `Sinopse` = '"+getSinopse()+"' WHERE `filme`.`Cod_Filme` = "+getCod_Filme();
                }
                Conexao.statement.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,"Alteração realizada com sucesso!!","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
                Conexao.executaSQL("select * from cliente order by ID_cliente");
                form.preencherTabela();
            }catch(SQLException errosql){
                JOptionPane.showMessageDialog(null,"\nErro na gravação : \n" +errosql,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
            }
        } 
          public void excluir(view_filme form, Controler Conexao){
        String sql="";
        try {
            int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir o registro: ","Confirmar Exclusão", JOptionPane.YES_NO_OPTION,3);
            if(resposta ==0){
                sql = "delete from filme where Cod_Filme = " +getCod_Filme();
                int excluir = Conexao.statement.executeUpdate(sql);
                if(excluir==1){
                    JOptionPane.showMessageDialog(null,"Exclusão realizada com sucesso!!","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
                    Conexao.executaSQL("select * from filme order by Cod_Filme");
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
          public void pesquisar(view_filme form, Controler Conexao){
     try{
            String pesquisa = "select * from filme where Nome like '"+getNome()+"%'";
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