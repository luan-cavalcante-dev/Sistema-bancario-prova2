package pessoas;

import pessoas.enums.TipoUsuario;

public class Usuario {
	
	// implementando parametros da classe pai usuario
	protected Usuario(String nome, String email, int senha, String cpf, TipoUsuario tipoUsuario) {
		this.nome = nome;
        this.email = email;
		this.senha = senha;
		this.cpf = cpf;
		this.tipoUsuario = tipoUsuario;
	}
    
	private String nome;
	private String email;
    private int senha;
	private String cpf;
    private TipoUsuario tipoUsuario;
	
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

	public int getSenha() {
		return senha;
	}

	public void setSenha(int senha) {
		this.senha = senha;
	}

    public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}
