package pessoas;

import pessoas.enums.TipoUsuario;

public class Bancario  extends Usuario{
	public Bancario(String nome, String email, int senha, String cpf) {
		super(nome, email, senha, cpf, TipoUsuario.BANCARIO);
	}
}