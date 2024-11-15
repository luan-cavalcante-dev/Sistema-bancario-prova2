package pessoas;

public class Usuario {
	protected Usuario() {

		// implementando os paramentos da classe pai usuario;
	}
    
	private String nome;
	private String email;
    private int senha;
    private int cpf;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
    
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(int senha) {
		this.senha = senha;
	}

    public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
}
