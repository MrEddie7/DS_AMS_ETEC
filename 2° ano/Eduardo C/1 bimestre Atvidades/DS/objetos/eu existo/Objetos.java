package objetos;

import javax.swing.JOptionPane;

public class Objetos {

    private String nome;
    private String login;
    private String email;
    private String senha;

    // Construtor padr�o
    public Objetos() {
        this("","","","");
    }

    // Construtor com par�metros
    public Objetos(String nome, String login, String email, String senha) {
        this.nome = nome;
        this.login = login;
        this.email = email;
        this.senha = senha;
    }

    // M�todos de acesso (getters e setters)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // M�todo de exemplo para verificar a exist�ncia (simulado)
    public static void verificarExistencia() {
        JOptionPane.showMessageDialog(null, "Verifica��o de exist�ncia realizada!");
    }

    public static void main(String[] args) {
        // Exemplo de uso
        Usuario usuario = new Usuario("Jo�o", "joao123", "joao@email.com", "senha123");
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Login: " + usuario.getLogin());
        System.out.println("Email: " + usuario.getEmail());
        System.out.println("Senha: " + usuario.getSenha());

        // Chama o m�todo verificarExistencia
        Usuario.verificarExistencia();
    }
}

    
    
    
