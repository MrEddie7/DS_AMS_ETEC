package Model;

import Control.conexao;
import View.frm_manutencao;
import java.sql.*;

import javax.swing.JOptionPane;


public class mdl_carro {
    public conexao con_bco;
    public frm_manutencao form;
    

    private int idCarro;
    private String fabricante;
    private String modelo;
    private String ano;
    private String placa;
    private String cor;
    private String comentario;

    public mdl_carro( ){//ta puxando o formulario inteiro pra instancia
        this(0,"","","","","","");
       
    }
        
    public mdl_carro(int idCarro, String fabricante, String modelo, String ano, String placa, String cor, String comentario) {
        this.idCarro = idCarro;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
        this.cor = cor;
        this.comentario = comentario;
    }

    /**
     * @return the idCarro
     */
    public int getIdCarro() {
        return idCarro;
    }

    /**
     * @param idCarro the idCarro to set
     */
    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }

    /**
     * @return the fabricante
     */
    public String getFabricante() {
        return fabricante;
    }

    /**
     * @param fabricante the fabricante to set
     */
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the ano
     */
    public String getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(String ano) {
        this.ano = ano;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the cor
     */
    public String getCor() {
        return cor;
    }

    /**
     * @param cor the cor to set
     */
    public void setCor(String cor) {
        this.cor = cor;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
 
   //botoes do crud
    
    public void cadastrar(frm_manutencao form,conexao con_bco){
    try{
            String insert_sql="INSERT INTO `tbl_carro` ( `Fabricante`, `Modelo`, `Ano`, `Placa`, `Cor`, `Comentarios`) VALUES('"+getFabricante()+"', '"+getModelo()+"', '"+getAno()+"', '"+getPlaca()+"', '"+getCor()+"', '"+getComentario()+"');"; 
            con_bco.statement.executeUpdate(insert_sql);
            JOptionPane.showMessageDialog(null,"Gravação realizada com sucesso!!","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);

            con_bco.executaSQL("select * from tbl_carro order by idCarro");
            form.preencherTabela();
        }catch(SQLException errosql){
            JOptionPane.showMessageDialog(null,"\nErro na gravação : \n" +errosql,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void alterar(frm_manutencao form,conexao con_bco){
         try {
             String sql;
                String msg="";
                if(form.txt_id.getText().equals("")){
                    sql="INSERT INTO `tbl_carro` ( `Fabricante`, `Modelo`, `Ano`, `Placa`, `Cor`, `Comentarios`) VALUES('"+getFabricante()+"', '"+getModelo()+"', '"+getAno()+"', '"+getPlaca()+"', '"+getCor()+"', '"+getComentario()+"');";
                    msg="Gravação de um novo registro";
                }
                else{
                    sql="UPDATE `tbl_carro` SET `Fabricante` = '"+getFabricante()+"', `Modelo` = '"+getModelo()+"', `Placa` = '"+getPlaca()+"', `Cor` = '"+getCor()+"', `Comentarios` = '"+getComentario()+"' WHERE `tbl_carro`.`idCarro` = "+getIdCarro();
                }
                con_bco.statement.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,"Alteração realizada com sucesso!!","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);

                con_bco.executaSQL("select * from tbl_carro order by idCarro");
                form.preencherTabela();
            }catch(SQLException errosql){
                JOptionPane.showMessageDialog(null,"\nErro na gravação : \n" +errosql,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
            }
    }
    
    public void excluir(frm_manutencao form,conexao con_bco){
        String sql="";
        try {
            int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir o registro: ","Confirmar Exclusão", JOptionPane.YES_NO_OPTION,3);
            if(resposta ==0){
                sql = "delete from tbl_carro where idCarro = " +getIdCarro();
                int excluir = con_bco.statement.executeUpdate(sql);
                if(excluir==1){
                    JOptionPane.showMessageDialog(null,"Exclusão realizada com sucesso!!","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
                    con_bco.executaSQL("select * from tbl_carro order by idCarro");
                    con_bco.resultset.first();
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
    public void pesquisar(frm_manutencao form,conexao con_bco){
     try{
            String pesquisa = "select * from tbl_carro where Placa like '"+getPlaca()+"%'";
            con_bco.executaSQL(pesquisa);

            if(con_bco.resultset.first()){
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
