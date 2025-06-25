public class formulario {
    private String nome;
    private String idade;
    private String sexo;
    private String interesses;
    
    public  formulario() {
      this("","","","");
    }


    // Construtor com parâmetros
    public formulario (String nome, String idade, String sexo, String interesses) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.interesses = interesses;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getIdade() { return idade; }
    public void setIdade(String idade) { this.idade = idade; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getInteresses() { return interesses; }
    public void setInteresses(String interesses) { this.interesses = interesses; }
    
    
}
