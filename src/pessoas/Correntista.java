package pessoas;

import pessoas.enums.TipoUsuario;
public class Correntista extends Usuario {
    public Correntista(String nome, String email, int senha, String cpf){
        super(nome, email, senha, cpf, TipoUsuario.CORRENTISTA);
    }
}
