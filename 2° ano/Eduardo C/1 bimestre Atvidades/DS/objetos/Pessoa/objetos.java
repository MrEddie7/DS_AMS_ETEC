
import javax.swing.JOptionPane;

public class objetos {

    private String nome;
    private String endereco;
    private String email;
    private String salario;
    private String telefone;

    // Construtor padrão
    public objetos() {
        this("", "", "", "", "");
    }

    // Construtor com parâmetros
    public objetos(String nome, String endereco, String email, String salario, String telefone) {
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.salario = salario;
        this.telefone = telefone;
    }

    // Métodos getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public static void verificarExistencia() {
        JOptionPane.showMessageDialog(null, "Identidade Confirmada!");
    }

   
    

    // Função para inserir os dados
    public void inserirDados() {
        // Utilizando JOptionPane para pegar os dados
        this.setNome(JOptionPane.showInputDialog("Informe o nome:"));
        this.setEndereco(JOptionPane.showInputDialog("Informe o endere�o:"));
        this.setEmail(JOptionPane.showInputDialog("Informe o email:"));
        this.setSalario(JOptionPane.showInputDialog("Informe o salario:"));
        this.setTelefone(JOptionPane.showInputDialog("Informe o telefone:"));
    }

    // Função para apresentar os dados
    public void apresentarDados() {
        // Utilizando JOptionPane para mostrar os dados
        JOptionPane.showMessageDialog(null, "Dados da Pessoa" + "\n Nome: " + this.getNome() + "\n Endere�o" + this.getEndereco() + "\n email " + 
                this.getEmail() + "\n Salario " + this.getSalario() + "\n telefone " + this.getTelefone());
    }
}
