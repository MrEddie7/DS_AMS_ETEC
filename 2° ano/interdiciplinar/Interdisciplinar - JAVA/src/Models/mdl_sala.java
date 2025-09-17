import Control.conexao;
import View.frm_sala;
import java.sql.*;

import javax.swing.JOptionPane;

public class mdl_sala {
    public conexao con_bco;
    public frm_sala form;

    private int numSala;         // Num_Sala
    private int numAssent;       // Num_Assent
    private String tipoSala;     // Tipo_Sala

    public mdl_sala() {
        this(0, 0, "");
    }

    public mdl_sala(int numSala, int numAssent, String tipoSala) {
        this.numSala = numSala;
        this.numAssent = numAssent;
        this.tipoSala = tipoSala;
    }

    /**
     * @return the numSala
     */
    public int getNumSala() {
        return numSala;
    }

    /**
     * @param numSala the numSala to set
     */
    public void setNumSala(int numSala) {
        this.numSala = numSala;
    }

    /**
     * @return the numAssent
     */
    public int getNumAssent() {
        return numAssent;
    }

    /**
     * @param numAssent the numAssent to set
     */
    public void setNumAssent(int numAssent) {
        this.numAssent = numAssent;
    }

    /**
     * @return the tipoSala
     */
    public String getTipoSala() {
        return tipoSala;
    }

    /**
     * @param tipoSala the tipoSala to set
     */
    public void setTipoSala(String tipoSala) {
        this.tipoSala = tipoSala;
    }


public void cadastrar(frm_sala form, conexao con_bco) {
        try {
            String sql = "INSERT INTO `sala` (`Num_Sala`, `Num_Assent`, `Tipo_Sala`) VALUES (?, ?, ?)";
            PreparedStatement ps = con_bco.conectar().prepareStatement(sql);
            ps.setInt(1, this.numSala);
            ps.setInt(2, this.numAssent);
            ps.setString(3, this.tipoSala);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!!", "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
            con_bco.executaSQL("SELECT * FROM sala ORDER BY Num_Sala");
            form.preencherTabela();
        } catch (SQLException errosql) {
            JOptionPane.showMessageDialog(null, "\nErro no cadastro : \n" + errosql, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        } finally {
            con_bco.desconectar();
        }
    }


public void alterar(frm_sala form, conexao con_bco) {
        try {
            String sql = "UPDATE `sala` SET `Num_Assent` = ?, `Tipo_Sala` = ? WHERE `Num_Sala` = ?";
            PreparedStatement ps = con_bco.conectar().prepareStatement(sql);
            ps.setInt(1, this.numAssent);
            ps.setString(2, this.tipoSala);
            ps.setInt(3, this.numSala);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso!!", "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
            con_bco.executaSQL("SELECT * FROM sala ORDER BY Num_Sala");
            form.preencherTabela();
        } catch (SQLException errosql) {
            JOptionPane.showMessageDialog(null, "\nErro na alteração : \n" + errosql, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        } finally {
            con_bco.desconectar();
        }
    }

    public void excluir(frm_sala form, conexao con_bco) {
        try {
            String sql = "DELETE FROM `sala` WHERE `Num_Sala` = ?";
            PreparedStatement ps = con_bco.conectar().prepareStatement(sql);
            ps.setInt(1, this.numSala);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!!", "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
            con_bco.executaSQL("SELECT * FROM sala ORDER BY Num_Sala");
            form.preencherTabela();
        } catch (SQLException errosql) {
            JOptionPane.showMessageDialog(null, "\nErro na exclusão : \n" + errosql, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        } finally {
            con_bco.desconectar();
        }
    }

    public void pesquisar(frm_sala form, conexao con_bco) {
        try {
            String pesquisa = "SELECT * FROM sala WHERE Num_Sala = ?";
            PreparedStatement ps = con_bco.conectar().prepareStatement(pesquisa);
            ps.setInt(1, this.numSala);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.numAssent = rs.getInt("Num_Assent");
                this.tipoSala = rs.getString("Tipo_Sala");
                form.txtNumAssent.setText(String.valueOf(this.numAssent));
                form.txtTipoSala.setText(this.tipoSala);
                form.preencherTabela();
            } else {
                JOptionPane.showMessageDialog(null, "Sala não encontrada.");
            }
            rs.close();
            ps.close();
        } catch (SQLException errosql) {
            JOptionPane.showMessageDialog(null, "\nErro na pesquisa : \n" + errosql, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        } finally {
            con_bco.desconectar();
        }
    }
    
}

    