package Model;

import Controler.conexao;
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

    // Getters e Setters
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

    // Métodos de CRUD
   public void cadastrar(frm_sala form, conexao con_bco) {
    try {
        String sql = "INSERT INTO `sala` (`Num_Sala`, `Num_Assent`, `Tipo_Sala`) VALUES (?, ?, ?)";
        // Usando a conexão diretamente
        PreparedStatement ps = con_bco.conexao.prepareStatement(sql);
        ps.setInt(1, this.numSala);  // Número da sala
        ps.setInt(2, this.numAssent);  // Número de assentos
        ps.setString(3, this.tipoSala);  // Tipo de sala
        ps.executeUpdate();
        ps.close();
        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!!", "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        con_bco.ExecutarSQL("SELECT * FROM sala ORDER BY Num_Sala");
        form.cadastrarSala();
    } catch (SQLException errosql) {
        JOptionPane.showMessageDialog(null, "\nErro no cadastro : \n" + errosql, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
    } finally {
        con_bco.desconectar();
    }
}


    public void alterar(frm_sala form, conexao con_bco) {
        try {
            String sql = "UPDATE `sala` SET `Num_Assent` = ?, `Tipo_Sala` = ? WHERE `Num_Sala` = ?";
        PreparedStatement ps = con_bco.conexao.prepareStatement(sql);
            ps.setInt(1, this.numAssent);
            ps.setString(2, this.tipoSala);
            ps.setInt(3, this.numSala);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso!!", "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
            con_bco.ExecutarSQL("SELECT * FROM sala ORDER BY Num_Sala");
            form.cadastrarSala();
        } catch (SQLException errosql) {
            JOptionPane.showMessageDialog(null, "\nErro na alteração : \n" + errosql, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        } finally {
            con_bco.desconectar();
        }
    }

    public void excluir(frm_sala form, conexao con_bco) {
        try {
            String sql = "DELETE FROM `sala` WHERE `Num_Sala` = ?";
        PreparedStatement ps = con_bco.conexao.prepareStatement(sql);
            ps.setInt(1, this.numSala);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!!", "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
            con_bco.ExecutarSQL("SELECT * FROM sala ORDER BY Num_Sala");
            form.cadastrarSala();
        } catch (SQLException errosql) {
            JOptionPane.showMessageDialog(null, "\nErro na exclusão : \n" + errosql, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        } finally {
            con_bco.desconectar();
        }
    }

    public void pesquisar(frm_sala form, conexao con_bco) {
    try {
        String pesquisa = "SELECT * FROM sala WHERE Num_Sala = ?";
        PreparedStatement ps = con_bco.conexao.prepareStatement(pesquisa);  // Usando a conexão diretamente
        ps.setInt(1, this.numSala);  // Passa o número da sala como parâmetro
        ResultSet rs = ps.executeQuery();  // Executa a consulta

        if (rs.next()) {  // Se encontrar um resultado
            this.numAssent = rs.getInt("Num_Assent");  // Recupera o número de assentos
            this.tipoSala = rs.getString("Tipo_Sala");  // Recupera o tipo da sala

            // Preenche os campos de texto do formulário com os dados recuperados
            form.txtNumAssent.setText(String.valueOf(this.numAssent));  // Preenche o campo de número de assentos
            form.txtTipoSala.setText(this.tipoSala);  // Preenche o campo de tipo da sala

            // Chama o método para atualizar a tabela, se necessário
            form.cadastrarSala();  // Preenche a tabela com os dados atualizados

        } else {
            JOptionPane.showMessageDialog(null, "Sala não encontrada.");  // Caso não encontre a sala
        }

        rs.close();  // Fecha o ResultSet
        ps.close();  // Fecha o PreparedStatement
    } catch (SQLException errosql) {
        JOptionPane.showMessageDialog(null, "\nErro na pesquisa : \n" + errosql, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
    } finally {
        con_bco.desconectar();  // Desconecta do banco de dados
    }
}

}
