package Model;
import Controler.Conexao;
import View.view_sala;
import java.sql.*;
import javax.swing.JOptionPane;
public class Sala {
    public Conexao Conexao;
    public view_sala form;
    private int numSala;       
    private int numAssent;      
    private String tipoSala;     
    public Sala() {
        this(0, 0, "");
    }
    public Sala(int numSala, int numAssent, String tipoSala) {
        this.numSala = numSala;
        this.numAssent = numAssent;
        this.tipoSala = tipoSala;
    }
    public int getNumSala() {
        return numSala;
    }
    public void setNumSala(int numSala) {
        this.numSala = numSala;
    }
    public int getNumAssent() {
        return numAssent;
    }
    public void setNumAssent(int numAssent) {
        this.numAssent = numAssent;
    }
    public String getTipoSala() {
        return tipoSala;
    }
    public void setTipoSala(String tipoSala) {
        this.tipoSala = tipoSala;
    }
   public void cadastrar(view_sala form, Controler Conexao) {
    try {
        String sql = "INSERT INTO `Sala` (`Num_Sala`, `Num_Assent`, `Tipo_Sala`) VALUES (?, ?, ?)";
        PreparedStatement ps = Conexao.conexao.prepareStatement(sql);
        ps.setInt(1, this.numSala);  
        ps.setInt(2, this.numAssent);  
        ps.setString(3, this.tipoSala);  
        ps.executeUpdate();
        ps.close();
        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!!", "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        Conexao.ExecutarSQL("SELECT * FROM Sala ORDER BY Num_Sala");
        form.cadastrarSala();
    } catch (SQLException errosql) {
        JOptionPane.showMessageDialog(null, "\nErro no cadastro : \n" + errosql, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
    } finally {
        Conexao.desconectar();
    }
}
    public void alterar(view_sala form, Controler Conexao) {
        try {
            String sql = "UPDATE `Sala` SET `Num_Assent` = ?, `Tipo_Sala` = ? WHERE `Num_Sala` = ?";
        PreparedStatement ps = Conexao.conexao.prepareStatement(sql);
            ps.setInt(1, this.numAssent);
            ps.setString(2, this.tipoSala);
            ps.setInt(3, this.numSala);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso!!", "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
            Conexao.ExecutarSQL("SELECT * FROM Sala ORDER BY Num_Sala");
            form.cadastrarSala();
        } catch (SQLException errosql) {
            JOptionPane.showMessageDialog(null, "\nErro na alteração : \n" + errosql, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        } finally {
            Conexao.desconectar();
        }
    }
    public void excluir(view_sala form, Controler Conexao) {
        try {
            String sql = "DELETE FROM `Sala` WHERE `Num_Sala` = ?";
        PreparedStatement ps = Conexao.conexao.prepareStatement(sql);
            ps.setInt(1, this.numSala);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!!", "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
            Conexao.ExecutarSQL("SELECT * FROM Sala ORDER BY Num_Sala");
            form.cadastrarSala();
        } catch (SQLException errosql) {
            JOptionPane.showMessageDialog(null, "\nErro na exclusão : \n" + errosql, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        } finally {
            Conexao.desconectar();
        }
    }
    public void pesquisar(view_sala form, Controler Conexao) {
    try {
        String pesquisa = "SELECT * FROM Sala WHERE Num_Sala = ?";
        PreparedStatement ps = Conexao.conexao.prepareStatement(pesquisa);  
        ps.setInt(1, this.numSala);  
        ResultSet rs = ps.executeQuery();  
        if (rs.next()) {  
            this.numAssent = rs.getInt("Num_Assent");  
            this.tipoSala = rs.getString("Tipo_Sala");  
            form.txtNumAssent.setText(String.valueOf(this.numAssent));  
            form.txtTipoSala.setText(this.tipoSala);  
            form.cadastrarSala();  
        } else {
            JOptionPane.showMessageDialog(null, "Sala não encontrada.");  
        }
        rs.close();  
        ps.close();  
    } catch (SQLException errosql) {
        JOptionPane.showMessageDialog(null, "\nErro na pesquisa : \n" + errosql, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
    } finally {
        Conexao.desconectar();  
    }
}
}