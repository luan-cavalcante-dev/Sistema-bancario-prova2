package pessoas;

public class Usuario {
	protected Usuario() {

		// implementando os paramentos da classe pai usuario;
	}

	public String getNome() {
		return nome;

	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	private String nome;

	private String email;
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	private int senha;

	
	public void setSenha(int senha) {
		this.senha = senha;
	}

	private int cpf;


	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
}
