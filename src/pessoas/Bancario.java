package pessoas;

public class Bancario  extends Usuario{
	public Bancario(String nome, String email, int senha, String cpf) {
		super(nome, email, senha, cpf, TipoUsuario.BANCARIO);
	}
	
	

}
