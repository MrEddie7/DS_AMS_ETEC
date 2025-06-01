package objetos;

import javax.swing.JOptionPane;

public class Objetos {

    private String nome;
    private String login;
    private String email;
    private String senha;

    // Construtor padrão
    public Objetos() {
        this("","","","");
    }

    // Construtor com parâmetros
    public Objetos(String nome, String login, String email, String senha) {
        this.nome = nome;
        this.login = login;
        this.email = email;
        this.senha = senha;
    }

    // Métodos de acesso (getters e setters)
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

    // Método de exemplo para verificar a existência (simulado)
    public static void verificarExistencia() {
        JOptionPane.showMessageDialog(null, "Verificação de existência realizada!");
    }

    public static void main(String[] args) {
        // Exemplo de uso
        Usuario usuario = new Usuario("João", "joao123", "joao@email.com", "senha123");
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Login: " + usuario.getLogin());
        System.out.println("Email: " + usuario.getEmail());
        System.out.println("Senha: " + usuario.getSenha());

        // Chama o método verificarExistencia
        Usuario.verificarExistencia();
    }
}

    
    
    
