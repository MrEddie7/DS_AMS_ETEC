import Control.conexao;
import View.frm_manutencao;
import java.sql.*;

import javax.swing.JOptionPane;

public class mdl_sala {
    public conexao con_bco;
    public frm_manutencao form;

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
}