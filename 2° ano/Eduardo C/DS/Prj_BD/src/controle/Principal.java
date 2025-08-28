
package controle;

import javax.swing.SwingUtilities;

/**
 *
 * Classe de inicialização
 */
public class Principal {
    
     public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Login loginFrame = new Login();
                loginFrame.setVisible(true);
            }
        });
    }
}
